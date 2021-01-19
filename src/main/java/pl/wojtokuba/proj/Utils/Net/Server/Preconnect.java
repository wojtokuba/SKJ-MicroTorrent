package pl.wojtokuba.proj.Utils.Net.Server;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import sun.nio.ch.Net;
import sun.security.krb5.Config;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Preconnect {

    public static void readCommunication(byte com, InputStream input, OutputStream output) throws NetworkException {

        switch (com){
            case Communicates.EHLO:
                runEHLO(input, output);
                break;
            case Communicates.FILE:
                System.out.println("TYP FILE");
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

}
