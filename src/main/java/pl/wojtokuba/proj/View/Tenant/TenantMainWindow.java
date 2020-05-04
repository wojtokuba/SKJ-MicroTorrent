package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Developer.*;
import pl.wojtokuba.proj.Commands.Logout;
import pl.wojtokuba.proj.Commands.Tenant.AddItemToFlat;
import pl.wojtokuba.proj.Commands.Tenant.OpenTenantRentials;
import pl.wojtokuba.proj.Components.AppInfoNavbar;
import pl.wojtokuba.proj.Components.NavBarMenu;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.BaseWindow;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperMainViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantMainViewModel;

import java.util.Arrays;
import java.util.HashMap;

public class TenantMainWindow extends BaseWindow {

    MainViewManager mainViewManager = (MainViewManager)SimpleInjector.resolveObject(MainViewManager.class);
    GridLayout borderLayout;
    TenantMainViewModel tenantMainViewModel;

    public TenantMainWindow() {
        super("Najemca");
        tenantMainViewModel = new TenantMainViewModel(this);
    }

    public TenantMainWindow(boolean overrideRendering) {
        super("Najemca", overrideRendering);
        tenantMainViewModel = new TenantMainViewModel(this);
    }


    @Override
    public void render() {

    }

    @Override
    public void drawWindow(){
        MenuBar menuBar = new MenuBar();
        new NavBarMenu("Moje Wynajmy",
                new HashMap<>(){{
                    put("Wynajmowane mieszkania", new OpenTenantRentials());
                    put("Dodaj przedmiot", new AddItemToFlat());
//                    put("Dodaj współlokatora", new OpenMyEstates());
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
