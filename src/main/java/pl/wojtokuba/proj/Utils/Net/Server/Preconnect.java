package pl.wojtokuba.proj.Utils.Net.Server;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import sun.nio.ch.Net;
import sun.security.krb5.Config;

import java.io.InputStream;
import java.io.OutputStream;

public class Preconnect {

    public static void readCommunication(byte com, InputStream input, OutputStream output) throws NetworkException {

        switch (com){
            case Communicates.EHLO:
                System.out.println("TYP EHLO");
                runEHLO(input, output);
                break;
            case Communicates.FILE:
                System.out.println("TYP FILE");
                break;
        }
    }

    public static void runEHLO(InputStream input, OutputStream output) throws NetworkException {
        ConfigStorage configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        try {
            int ch1 = input.read();
            int ch2 = input.read();
            if ((ch1 | ch2) < 0)
                throw new NetworkException();
            int userId = (ch1 << 8) + (ch2 << 0);
            System.out.println(userId);
            output.write(Communicates.EHLO);
            output.write(Communicates.EHLO);
        } catch (Exception e){
            throw new NetworkException();
        }
    }
}
