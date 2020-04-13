package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.TimeLapseManager;
import pl.wojtokuba.proj.View.LoginWindow;

public class App {

    public static void main(String[] args){
        //setup IoC injector
        SimpleInjector.Setup();

        //initialize first, default window
        new LoginWindow();

        //wait for time thread stop then finish
        prepareToStop();
    }

    private static void prepareToStop(){
        TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);
        if(timeLapseManager != null)
            timeLapseManager.end();
    }
}
