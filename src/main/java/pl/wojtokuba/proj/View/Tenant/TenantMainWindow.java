package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Developer.OpenMyEstates;
import pl.wojtokuba.proj.Commands.Logout;
import pl.wojtokuba.proj.Components.AppInfoNavbar;
import pl.wojtokuba.proj.Components.AppNavbar;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperMainViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantMainViewModel;

import java.util.Arrays;

public class TenantMainWindow extends AbstractWindow {

    MainViewManager mainViewManager;
    Panel contentPanel;
    GridLayout borderLayout;
    TenantMainViewModel tenantMainViewModel;

    public TenantMainWindow() {
        super("Najemca");
        tenantMainViewModel = new TenantMainViewModel(this);
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new GridLayout(10));
        borderLayout = (GridLayout) contentPanel.getLayoutManager();
        setComponent(contentPanel);
        contentPanel.addComponent(new AppInfoNavbar());
        contentPanel.addComponent(new Separator(Direction.HORIZONTAL)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.FILL,
                        GridLayout.Alignment.FILL,
                        true,
                        false,
                        10,
                        1)
                ));
        ActionListBox menu = new AppNavbar();
        menu.addItem("Moje Osiedla", new OpenMyEstates());
        menu.addItem("Moje Budynki", new OpenMyEstates());
        menu.addItem("Moje mieszkania", new OpenMyEstates());
        menu.addItem("Płatności", new OpenMyEstates());
        menu.addItem("Wyloguj", new Logout());
        menu.addItem("Zakończ", new AppExit());
        contentPanel.addComponent(menu);
        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }

}
