package pl.wojtokuba.proj.Utils.Net.Client;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.Net.TCPClient;

import java.io.*;

public class HelloService extends BaseClient{

    public static int sendHelloAndDiscoverHost(byte userId, String hostname, int port) throws NetworkException, IOException {
        InputStream bufOut = sendMessage(hostname, port, Communicates.EHLO, new byte[]{userId});
        int clientId = bufOut.read();

        if(clientId == userId){
            throw new NetworkException("Nie można dodać podanego klienta do sieci ponieważ ma taki sam ID jak ten klient.");
        }
        return clientId;
    }
}
