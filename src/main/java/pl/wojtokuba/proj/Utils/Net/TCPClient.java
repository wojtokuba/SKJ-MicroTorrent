package pl.wojtokuba.proj.Utils.Net;

import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Utils.LoggerUtil;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPClient {
    private String hostname;
    private int port;
    private Socket socket = null;
    public TCPClient(String hostname, int port) throws NetworkException {
        this.hostname = hostname;
        this.port = port;
    }

    public BufferedReader sendAndGetResponse(BufferedReader input) throws NetworkException {
        try {
            this.socket = new Socket(this.hostname, this.port);
            String tmp;
            DataOutputStream stream = new DataOutputStream(this.socket.getOutputStream());
            BufferedReader outStream =
                    new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while((tmp = input.readLine()) != null){
                stream.writeBytes(tmp);
            }
            socket.close();
            return outStream;
        } catch (Exception e){
            throw new NetworkException();
        }
    }
}
