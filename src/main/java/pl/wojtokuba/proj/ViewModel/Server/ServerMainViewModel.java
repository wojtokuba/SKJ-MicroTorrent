package pl.wojtokuba.proj.ViewModel.Server;

import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;

public class ServerMainViewModel {
    ServerMainWindow serverMainWindow;

    public ServerMainViewModel(ServerMainWindow serverMainWindow){
        this.serverMainWindow = serverMainWindow;
    }

}
