package pl.wojtokuba.proj.Utils.Net;

import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Utils.LoggerUtil;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

//TCP threaded pool server from http://tutorials.jenkov.com/java-multithreaded-servers/thread-pooled-server.html
public class TCPServer implements Runnable{
    private int          serverPort   = 10123;
    private ServerSocket serverSocket = null;
    private boolean      isStopped    = false;
    private Thread       runningThread = null;
    private ExecutorService threadPool = null;
    ConfigStorage configStorage;

    public void startTCPListen(){
        configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        threadPool = Executors.newFixedThreadPool(Integer.parseInt(configStorage.get(ConfigStorage.SettingValues.MAX_CLIENTS)));
        new Thread(this).start();
        LoggerUtil.getLogger().fine("TCP Server is just being started");
    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public void run(){
        synchronized (this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(!isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    LoggerUtil.getLogger().fine("TCP Server stopped.");
                    break;
                }
                LoggerUtil.getLogger().severe("Error accepting client connection");
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            LoggerUtil.getLogger().fine("Handling new connection");

            this.threadPool.execute(
                    new TcpWorkerRunnable(clientSocket, "Thread Pooled Server"));

        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            LoggerUtil.getLogger().fine("Started listening on: 0.0.0.0:"+this.serverPort);
        } catch (IOException e) {
            this.serverPort++;
            openServerSocket();
            LoggerUtil.getLogger().severe("Cannot open port"+this.serverPort);
            throw new RuntimeException("Cannot open port"+this.serverPort, e);
        }
    }
}
