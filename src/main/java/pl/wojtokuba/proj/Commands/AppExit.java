package pl.wojtokuba.proj.Commands;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.App;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.LoginWindow;

import java.io.IOException;

public class AppExit implements Runnable {
    SessionStorage storage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);
    MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {

        mainViewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        App.prepareToStop();
        try {
            mainViewManager.getScreen().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
