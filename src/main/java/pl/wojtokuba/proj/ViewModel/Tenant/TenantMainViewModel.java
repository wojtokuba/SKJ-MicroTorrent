package pl.wojtokuba.proj.ViewModel.Tenant;

import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperMainWindow;
import pl.wojtokuba.proj.View.Tenant.TenantMainWindow;

public class TenantMainViewModel {
    TenantMainWindow tenantMainWindow;
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);

    public TenantMainViewModel(TenantMainWindow tenantMainWindow){
        this.tenantMainWindow = tenantMainWindow;
    }

}
