package pl.wojtokuba.proj.Commands.Sharing;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Sharing.DownloadP2MPWindow;
import pl.wojtokuba.proj.View.Sharing.ForeignClientsWindow;

public class DownloadP2MP implements Runnable {
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {
        viewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new DownloadP2MPWindow();
    }
}
