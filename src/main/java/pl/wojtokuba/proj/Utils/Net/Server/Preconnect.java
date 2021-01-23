package pl.wojtokuba.proj.Utils.Net.Server;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.ByteConverter;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import sun.nio.ch.Net;
import sun.security.krb5.Config;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class Preconnect {

    public static final int CHUNK_SIZE = 512*1024; // 512 kB of chunk size

    public static void readCommunication(byte com, InputStream input, OutputStream output) throws NetworkException {

        switch (com){
            case Communicates.EHLO:
                System.out.println("TYP EHLO");
                runEHLO(input, output);
                break;
            case Communicates.FILE:
                System.out.println("TYP FILE");
                runFILE(input, output);
                break;
            case Communicates.PULL:
                System.out.println("TYP PULL");
                runPULL(input, output);
                break;
        }
    }

    public static void runEHLO(InputStream input, OutputStream output) throws NetworkException {
        try {
            ConfigStorage configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
            output.write(Communicates.EHLO);
            output.write(Byte.parseByte(configStorage.get(ConfigStorage.SettingValues.CLIENT_NUMBER)));
        } catch (Exception e){
            e.getStackTrace();
            throw new NetworkException();
        }
    }

    public static void runFILE(InputStream input, OutputStream output) throws NetworkException {
        try {
            SharedFilesStorage sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);

            ByteBuffer bb = ByteBuffer.allocate(2);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            bb.put((byte)input.read());
            bb.put((byte)input.read());
            short strLen = bb.getShort(0);


            //create byte buffer with all bytes size;
            byte[] buffer = new byte[strLen];
            for(int i = 0; i < strLen; i++){
                buffer[i] = (byte) input.read();
            }

            String filename = ByteConverter.byteArrayToString(buffer);
            output.write(Communicates.FILE);
            File sharedFile = sharedFilesStorage.findOneByName(filename);

            //if file exists send that file is available to download
            if(sharedFile != null){
                output.write((byte)(1));
                output.write(sharedFile.getChecksum().substring(0,5).getBytes());

                bb = ByteBuffer.allocate(4);
                bb.putInt((int) (sharedFile.getSize()*1024*1024/CHUNK_SIZE));
                output.write(bb.array()); // returns amount of chunks
            } else {
                output.write(new byte[10]);
            }

        } catch (Exception e){
            e.getStackTrace();
            throw new NetworkException();
        }
    }


    public static void runPULL(InputStream input, OutputStream output) throws NetworkException {
        try {
            SharedFilesStorage sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);
            int chunkNumber = ByteConverter.readIntFromBuffer(input);

            //create byte buffer with file hash
            byte[] buffer = new byte[5];
            for(int i = 0; i < 5; i++){
                buffer[i] = (byte) input.read();
            }
            String hashShort = ByteConverter.byteArrayToString(buffer);
            output.write(Communicates.PULL);
            File sharedFile = sharedFilesStorage.findOneByShortHash(hashShort);

            //if file exists send that file is available to download
            if(sharedFile != null){
                output.write(ByteConverter.createByteArrayFromInt(chunkNumber)); // returns number of chunk
                output.write(buffer); //write checksum

            } else {
                output.write(new byte[11]);
            }

        } catch (Exception e){
            e.getStackTrace();
            throw new NetworkException();
        }
    }

}
