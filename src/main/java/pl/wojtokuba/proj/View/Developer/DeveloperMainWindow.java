package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Developer.*;
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
                    put("Dodaj Nowy", new CreateNewBlock());
                }},
                menuBar
        );
        new NavBarMenu("Mieszkania",
                new HashMap<>(){{
                    put("Lista", new OpenMyFlats());
                    put("Dodaj Nowe", new CreateNewFlat());
                }},
                menuBar
        );
        new NavBarMenu("Wynajem",
                new HashMap<>(){{
                    put("Aktywne Najmy", new OpenMyRentials());
                    put("Nowy wynajem", new CreateNewRential());
                    put("Nowy najemca", new CreateNewRentalUser());
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
