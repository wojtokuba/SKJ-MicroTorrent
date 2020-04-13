package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.gui2.AbstractWindow;
import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Panel;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperMainViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantMainViewModel;

import java.util.Arrays;

public class TenantMainWindow extends AbstractWindow {

    MainViewManager mainViewManager;
    Panel contentPanel;
    BorderLayout borderLayout;
    TenantMainViewModel tenantMainViewModel;

    public TenantMainWindow() {
        super("Najemca");
        tenantMainViewModel = new TenantMainViewModel(this);
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new BorderLayout());
        borderLayout = (BorderLayout) contentPanel.getLayoutManager();
//        contentPanel.addComponent(new Menu)
        setHints(Arrays.asList(Hint.CENTERED));



        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }

}
