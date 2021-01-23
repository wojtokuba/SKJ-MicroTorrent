package pl.wojtokuba.proj.Model;

public class VirtualRemoteFile{
    InnerHost host;
    String fileHash;
    int chunkAmount = 0;

    public VirtualRemoteFile(InnerHost host , String fileHash, int chunkAmount) {
        this.host = host;
        this.fileHash = fileHash;
        this.chunkAmount = chunkAmount;
    }

    public InnerHost getHost() {
        return host;
    }

    public String getFileHash() {
        return fileHash;
    }

    public int getChunkAmount() {
        return chunkAmount;
    }
}
