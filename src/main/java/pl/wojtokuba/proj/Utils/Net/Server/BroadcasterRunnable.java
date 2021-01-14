package pl.wojtokuba.proj.Utils.Net.Server;

import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.IOException;
import java.net.InetAddress;

/**
 *  BroadCasterRunnable is responsible for serving broadcast packet with info that our application is available to connect.
 */
public class BroadcasterRunnable implements Runnable{

    protected Thread broadcastThread = null;
    public static boolean isStopped = false;
    private TCPServer tcpServer;

    public BroadcasterRunnable() {
        broadcastThread = new Thread(this);
        broadcastThread.start();
    }

    public void run() {
        tcpServer = (TCPServer) SimpleInjector.resolveObject(TCPServer.class);

        while(!isStopped){
            try {
                Broadcaster.broadcast("__PEJOT__%%"+tcpServer.getServerListenPort()+"%%__READY__", InetAddress.getByName("255.255.255.255"));
                Thread.sleep(5000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void prepareToStop(){
        isStopped = true;
    }
}
