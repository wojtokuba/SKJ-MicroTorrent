package pl.wojtokuba.proj.Utils.Net.Client;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.Net.TCPClient;

import java.io.*;

public class HelloService {

    public static int sendHelloAndDiscoverHost(byte userId, String hostname, int port) throws NetworkException, IOException {
        TCPClient tcpCli = new TCPClient(hostname, port);
        byte[] buffer = new byte[2];
        buffer[0] = Communicates.EHLO;
        buffer[1] = userId;

        BufferedReader bufRead = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer)));
        InputStream bufOut;
        try {
            bufOut = tcpCli.sendAndGetResponse(bufRead);
        } catch (Exception e){
            throw new NetworkException("Podany host nie odpowiedział.");
        }
        if(bufOut.read() != Communicates.EHLO){
            throw new NetworkException("Otrzymany pakiet jest nieprawidłowy.");
        }
        int clientId = bufOut.read();

        if(clientId == userId){
            throw new NetworkException("Nie można dodać podanego klienta do sieci ponieważ ma taki sam ID jak ten klient.");
        }
        return clientId;
    }
}
