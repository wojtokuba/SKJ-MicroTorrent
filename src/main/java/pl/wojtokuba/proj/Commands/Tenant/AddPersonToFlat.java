package pl.wojtokuba.proj.Commands.Tenant;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Tenant.TenantPersonAddWindow;
import pl.wojtokuba.proj.View.Tenant.TenantVehicleAddWindow;

public class AddPersonToFlat implements Runnable {
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    @Override
    public void run() {
        viewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new TenantPersonAddWindow();
    }
}
