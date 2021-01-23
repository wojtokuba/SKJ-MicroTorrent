package pl.wojtokuba.proj.View.Server;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Logout;
import pl.wojtokuba.proj.Commands.Server.OpenMyFileList;
import pl.wojtokuba.proj.Commands.Server.ShareFile;
import pl.wojtokuba.proj.Commands.Sharing.DownloadP2MP;
import pl.wojtokuba.proj.Commands.Sharing.ForeignClients;
import pl.wojtokuba.proj.Components.NavBarMenu;
import pl.wojtokuba.proj.View.BaseWindow;
import pl.wojtokuba.proj.ViewModel.Server.ServerMainViewModel;

import java.util.HashMap;

public class ServerMainWindow extends BaseWindow {

    ServerMainViewModel serverMainViewModel;

    public ServerMainWindow(String windowTitle) {
        super(windowTitle);
        serverMainViewModel = new ServerMainViewModel(this);
    }

    public ServerMainWindow(){
        super("Server Mode");
        serverMainViewModel = new ServerMainViewModel(this);
    }

    @Override
    public void render() {

    }

    @Override
    public void drawWindow(){
        MenuBar menuBar = new MenuBar();
        new NavBarMenu("Moje pliki",
                new HashMap<String, Runnable>(){{
                    put("Lista plików", new OpenMyFileList());
//                    put("Ścieżki systemowe", new OpenMyEstates());
                }},
                menuBar
        );
        new NavBarMenu("Wymiana plików",
                new HashMap<String, Runnable>(){{
                    put("Inni klienci", new ForeignClients());
//                    put("Pobierz plik P2P", new OpenMyBlocks());
                    put("Pobierz plik P2MP", new DownloadP2MP());
                    put("Udostępniaj plik", new ShareFile());
                }},
                menuBar
        );
        new NavBarMenu("System",
                new HashMap<String, Runnable>(){{
                    put("Zakończ", new AppExit());
                    put("Zmień ustawienia", new Logout());
                }},
                menuBar
        );
        menuBar.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.FILL,
                GridLayout.Alignment.BEGINNING,
                true,
                false,
                10,
                1));
        contentPanel.addComponent(menuBar);
    }

}
