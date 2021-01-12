package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Utils.*;
import pl.wojtokuba.proj.Utils.Net.TCPServer;
import pl.wojtokuba.proj.View.AppWorkModeWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){

        // LOGGING FINE
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel( Level.FINE );
        rootLog.getHandlers()[0].setLevel( Level.FINE ); // Default console handler
        // END LOGGER

        //setup IoC injector
        SimpleInjector.Setup();
        new AppWorkModeWindow();

        //wait for time thread stop then finish
        prepareToStop();
    }

    public static void prepareToStop(){
        TCPServer tcpServer = (TCPServer) SimpleInjector.resolveObject(TCPServer.class);
        if(tcpServer != null){
            tcpServer.setStopped(true);
        }
    }
}
