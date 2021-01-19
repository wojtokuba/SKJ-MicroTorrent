package pl.wojtokuba.proj.ViewModel.Sharing;
import pl.wojtokuba.proj.Components.CheckBoxFormGroup;
import pl.wojtokuba.proj.Exceptions.FilesystemException;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.InnerHost;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Storage.ForeignHostsStorage;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.Filesystem;
import pl.wojtokuba.proj.Utils.Net.Communicates;
import pl.wojtokuba.proj.Utils.Net.Server.TCPServer;
import pl.wojtokuba.proj.Utils.Net.TCPClient;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Sharing.ForeignClientsWindow;
import pl.wojtokuba.proj.View.Sharing.ShareFileWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class ForeignClientsViewModel {
    ForeignClientsWindow foreignClientsWindow;
    SharedFilesStorage sharedFilesStorage;
    ForeignHostsStorage foreignHostsStorage;
    ConfigStorage configStorage;
    public TCPServer tcpServer;

    public ForeignClientsViewModel(ForeignClientsWindow foreignClientsWindow){
        sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);
        foreignHostsStorage = (ForeignHostsStorage) SimpleInjector.resolveObject(ForeignHostsStorage.class);
        configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        tcpServer = (TCPServer) SimpleInjector.resolveObject(TCPServer.class);

        this.foreignClientsWindow = foreignClientsWindow;
    }

    public Collection<InnerHost> getInnerHosts() {
        try {
            return foreignHostsStorage.findAll();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public void createClient(){
        foreignClientsWindow.setCheckingClients(true);
        foreignClientsWindow.render();
        String strHostPort = foreignClientsWindow.getNewHostName().getValue();
        if(strHostPort.length() == 0 | !strHostPort.contains(":")){
            foreignClientsWindow.setCheckingFailure("Podany adres jest nieprawidłowo sformatowany.");
            return;
        }
        try {
            String[] hostPort = strHostPort.split(":");
            TCPClient tcpCli = new TCPClient(hostPort[0], Integer.parseInt(hostPort[1]));
            byte[] buffer = new byte[2];
            byte userId = Byte.parseByte(configStorage.get(ConfigStorage.SettingValues.CLIENT_NUMBER));
            buffer[0] = Communicates.EHLO;
            buffer[1] = userId;

            BufferedReader bufRead = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer)));
            InputStream bufOut;
            try {
                bufOut = tcpCli.sendAndGetResponse(bufRead);
            } catch (Exception e){
                foreignClientsWindow.setCheckingFailure("Podany host nie odpowiedział. ");
                return;
            }

            if(bufOut.read() != Communicates.EHLO){
                foreignClientsWindow.setCheckingFailure("Otrzymany pakiet jest nieprawidłowy.");
                return;
            }
            int remoteId = bufOut.read();
            foreignHostsStorage.push(new InnerHost(Integer.toString(remoteId),hostPort[0], Integer.parseInt(hostPort[1])));
            foreignClientsWindow.setCheckingClients(false);
            foreignClientsWindow.render();
        } catch (Exception e){
            e.printStackTrace();
            foreignClientsWindow.setCheckingFailure("Wystąpił błąd. Spróbuj ponownie.");
        }

    }
}
