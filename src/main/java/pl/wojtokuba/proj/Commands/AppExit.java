package pl.wojtokuba.proj.Commands;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.IOException;

public class AppExit implements Runnable {
    MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {

        mainViewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        try {
            mainViewManager.getScreen().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
