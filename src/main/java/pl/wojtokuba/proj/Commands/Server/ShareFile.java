package pl.wojtokuba.proj.Commands.Server;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Files.MyFilesListWindow;
import pl.wojtokuba.proj.View.Sharing.ShareFileWindow;

public class ShareFile implements Runnable {
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {
        viewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new ShareFileWindow();
    }
}
