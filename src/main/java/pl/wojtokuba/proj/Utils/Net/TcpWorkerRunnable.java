package pl.wojtokuba.proj.Utils.Net;

import pl.wojtokuba.proj.Utils.LoggerUtil;
import pl.wojtokuba.proj.Utils.Net.Server.Preconnect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpWorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public TcpWorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            int ch;
            try {
                Preconnect.readCommunication((byte) input.read(), input, output);
            } catch (Exception e){
                LoggerUtil.getLogger().severe("TCP Network exception occured");
            }
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
