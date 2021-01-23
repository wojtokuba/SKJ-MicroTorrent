package pl.wojtokuba.proj.Utils.Net.Client;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.Net.TCPClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BaseClient {

    protected static InputStream sendMessage(String hostname, int port, byte packetType, byte[] buffer) throws NetworkException {
        TCPClient tcpCli = new TCPClient(hostname, port);
        byte[] newbuf = new byte[buffer.length+1];
        newbuf[0] = packetType;

        for (int i = 1; i < buffer.length+1; i++){
            newbuf[i] = buffer[i-1];
        }
        BufferedReader bufRead = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(newbuf)));
        InputStream bufOut;
        try {
            bufOut = tcpCli.sendAndGetResponse(bufRead);
        } catch (Exception e){
            throw new NetworkException("Podany host nie odpowiedział.");
        }
        try {
            if(bufOut.read() != packetType){
                throw new NetworkException("Otrzymany pakiet jest nieprawidłowy.");
            }
        } catch (Exception e){
            throw new NetworkException(e.getMessage());
        }
        return bufOut;
    }
}
