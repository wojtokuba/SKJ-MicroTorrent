package pl.wojtokuba.proj.Commands.Developer;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperBlocksNewWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesNewWindow;

public class CreateNewBlock implements Runnable {
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {
        viewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new DeveloperBlocksNewWindow();
    }
}
