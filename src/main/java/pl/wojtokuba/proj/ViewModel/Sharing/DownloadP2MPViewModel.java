package pl.wojtokuba.proj.ViewModel.Sharing;
import pl.wojtokuba.proj.Exceptions.NetworkException;
import pl.wojtokuba.proj.Model.InnerHost;
import pl.wojtokuba.proj.Model.VirtualRemoteFile;
import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Storage.ForeignHostsStorage;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.Net.Client.FileTransferService;
import pl.wojtokuba.proj.Utils.Net.Client.HelloService;
import pl.wojtokuba.proj.Utils.Net.Server.TCPServer;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Sharing.DownloadP2MPWindow;
import pl.wojtokuba.proj.View.Sharing.ForeignClientsWindow;

import java.util.ArrayList;
import java.util.Collection;

public class DownloadP2MPViewModel {
    DownloadP2MPWindow downloadP2MPWindow;
    SharedFilesStorage sharedFilesStorage;
    ForeignHostsStorage foreignHostsStorage;
    ConfigStorage configStorage;
    Thread thread = null;
    public TCPServer tcpServer;

    public DownloadP2MPViewModel(DownloadP2MPWindow downloadP2MPWindow){
        sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);
        foreignHostsStorage = (ForeignHostsStorage) SimpleInjector.resolveObject(ForeignHostsStorage.class);
        configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        tcpServer = (TCPServer) SimpleInjector.resolveObject(TCPServer.class);

        this.downloadP2MPWindow = downloadP2MPWindow;
    }

    public void downloadFile(){
        downloadP2MPWindow.resetView();
        if(downloadP2MPWindow.getFilename().length() == 0){
            downloadP2MPWindow.setErrorMessage("Nazwa pliku nie może być pusta.");
            downloadP2MPWindow.render();
            return;
        }
        downloadP2MPWindow.renderStartCheckingHosts();
        if(thread != null) {
            thread.interrupt();
        }
        thread = new Thread(() -> {
            try {
                synchronized (this){
                    System.out.println("Searching hosts...");
                    Collection<VirtualRemoteFile> files = FileTransferService.getFileAvailableHosts(downloadP2MPWindow.getFilename());
                    if(files.size() == 0){
                        downloadP2MPWindow.setErrorMessage("Obecnie żaden serwer nie posiada tego pliku.");
                        downloadP2MPWindow.render();
                    } else {
                        downloadP2MPWindow.renderResultCheckingHosts(files);
                    }
                }
            } catch (Exception e){
                downloadP2MPWindow.setErrorMessage("Wystąpił błąd podczas pobierania list plików.");
                downloadP2MPWindow.render();
            }
        });
        thread.start();

    }
}
