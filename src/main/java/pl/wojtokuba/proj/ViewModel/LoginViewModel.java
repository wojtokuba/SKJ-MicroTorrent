package pl.wojtokuba.proj.ViewModel;

import pl.wojtokuba.proj.Storage.ConfigStorage;
import pl.wojtokuba.proj.Utils.BaseAppConfigs;
import pl.wojtokuba.proj.Utils.Net.TCPServer;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.AppWorkModeWindow;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;

public class LoginViewModel {
    AppWorkModeWindow appWorkModeWindow;
    ConfigStorage config = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
    TCPServer tcpServer = (TCPServer) SimpleInjector.resolveObject(TCPServer.class);
    public LoginViewModel(AppWorkModeWindow appWorkModeWindow){
        this.appWorkModeWindow = appWorkModeWindow;
    }


    public void LoginUser(){
        appWorkModeWindow.setErrorMessage("");
        config.set(ConfigStorage.SettingValues.TRANSMISSION_PROTOCOL, this.appWorkModeWindow.getAppTransmissionProtocol());
        appWorkModeWindow.close();
        if (config.get(ConfigStorage.SettingValues.TRANSMISSION_PROTOCOL).equals(BaseAppConfigs.PROTO_TCP)){
            tcpServer.startTCPListen();
        }
        new ServerMainWindow();
    }
}
