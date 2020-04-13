package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.LoginWindow;

public class App {

    public static void main(String[] args){
        //setup IoC injector
        SimpleInjector.Setup();
        new LoginWindow();
    }
}
