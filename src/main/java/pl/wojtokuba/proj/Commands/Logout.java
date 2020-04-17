package pl.wojtokuba.proj.Commands;

import com.googlecode.lanterna.gui2.AbstractWindow;
import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.LoginWindow;

public class Logout implements Runnable {
    SessionStorage storage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);
    MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {

        storage.logout(mainViewManager);
        new LoginWindow();
    }
}
