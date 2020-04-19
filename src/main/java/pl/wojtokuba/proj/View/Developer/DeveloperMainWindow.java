package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Developer.CreateNewEstate;
import pl.wojtokuba.proj.Commands.Developer.OpenMyBlocks;
import pl.wojtokuba.proj.Commands.Developer.OpenMyEstates;
import pl.wojtokuba.proj.Commands.Logout;
import pl.wojtokuba.proj.Components.NavBarMenu;
import pl.wojtokuba.proj.View.BaseWindow;
import pl.wojtokuba.proj.View.WindowDrawable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperMainViewModel;

import java.util.HashMap;

public class DeveloperMainWindow extends BaseWindow {

    DeveloperMainViewModel developerMainViewModel;

    public DeveloperMainWindow(String windowTitle) {
        super(windowTitle);
        developerMainViewModel = new DeveloperMainViewModel(this);
    }

    public DeveloperMainWindow(){
        super("Deweloper");
        developerMainViewModel = new DeveloperMainViewModel(this);
    }

    @Override
    public void render() {

    }

    @Override
    public void drawWindow(){
        MenuBar menuBar = new MenuBar();
        new NavBarMenu("Osiedla",
                new HashMap<>(){{
                    put("Lista", new OpenMyEstates());
                    put("Dodaj Nowe", new CreateNewEstate());
                }},
                menuBar
        );
        new NavBarMenu("Budynki",
                new HashMap<>(){{
                    put("Lista", new OpenMyBlocks());
                    put("Dodaj Nowy", new OpenMyEstates());
                }},
                menuBar
        );
        new NavBarMenu("Mieszkania",
                new HashMap<>(){{
                    put("Lista", new OpenMyEstates());
                    put("Dodaj Nowe", new OpenMyEstates());
                }},
                menuBar
        );
        new NavBarMenu("Wynajem",
                new HashMap<>(){{
                    put("Nowy wynajem", new OpenMyEstates());
                    put("Aktywne Najmy", new OpenMyEstates());
                    put("Najemcy", new OpenMyEstates());
                }},
                menuBar
        );
        new NavBarMenu("System",
                new HashMap<>(){{
                    put("Zakończ", new AppExit());
                    put("Wyloguj", new Logout());
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
