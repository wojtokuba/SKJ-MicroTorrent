package pl.wojtokuba.proj.Model;

import java.util.ArrayList;

public class InnerHost {
    String name;
    String host;
    int port;
    ArrayList<File> sharedFiles; //in MB

    public InnerHost(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public ArrayList<File> getSharedFiles() {
        return sharedFiles;
    }

    public void setSharedFiles(ArrayList<File> sharedFiles) {
        this.sharedFiles = sharedFiles;
    }
}
