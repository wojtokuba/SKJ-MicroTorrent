package pl.wojtokuba.proj.Utils.Net.Client;

import pl.wojtokuba.proj.Model.InnerHost;
import pl.wojtokuba.proj.Model.VirtualRemoteFile;
import pl.wojtokuba.proj.Storage.ForeignHostsStorage;
import pl.wojtokuba.proj.Utils.ByteConverter;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;


/* Known issue of this way of handling files is that we're basing on name only.
*
*  We should also verify hash is this file the same on every remote host
*  and we're getting exactly the same chunk.
*/
public class FileTransferService extends BaseClient{

    public static Collection<VirtualRemoteFile> getFileAvailableHosts(String filename){
        ForeignHostsStorage foreignHostsStorage = (ForeignHostsStorage) SimpleInjector.resolveObject(ForeignHostsStorage.class);
        ArrayList<VirtualRemoteFile> hosts = new ArrayList<>();

        for(InnerHost host : foreignHostsStorage.findAll()){
            VirtualRemoteFile vFile = checkIsFileAvailable(host, filename);
            if(vFile != null){
                hosts.add(vFile);
            }
        }

        return hosts;
    }


    //according to communicates - if byte is 1, file with this name is available on the server
    private static VirtualRemoteFile checkIsFileAvailable(InnerHost host, String filename){
        try {
            byte[] buf = new byte[filename.length()+2];

            //at first provide length of string then string.
            buf[0] = (byte)(filename.length() & 0xff);
            buf[1] = (byte)((filename.length() >> 8) & 0xff);
            for(int i = 0; i < filename.length(); i++){
                buf[i+2] = (byte)filename.charAt(i);
            }

            InputStream outStream = sendMessage(host.getHost(), host.getPort(), Communicates.FILE, buf);
            if(outStream.read() == 1){
                buf = new byte[5];
                byte[] buf2 = new byte[4];
                for(int i = 0; i < 5; i++){
                    buf[i] = (byte) outStream.read();
                }
                //parse file chunks amount
                for(int i = 0; i < 4; i++){
                    buf2[i] = (byte) outStream.read();
                }
                return new VirtualRemoteFile(host, ByteConverter.byteArrayToString(buf), ByteBuffer.wrap(buf2).getInt());
            }
            //according to communicates - if byte is 1, file with this name is available on the server
        } catch (Exception ignored) {}

        return null;
    }


}
