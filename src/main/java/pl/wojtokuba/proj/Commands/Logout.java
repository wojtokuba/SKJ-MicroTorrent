package pl.wojtokuba.proj.Commands;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.AppWorkModeWindow;

public class Logout implements Runnable {
    MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {

        mainViewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new AppWorkModeWindow();
    }
}
