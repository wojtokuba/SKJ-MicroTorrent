package pl.wojtokuba.proj.ViewModel.Tenant;

import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Tenant.TenantRentialDetailsWindow;
import pl.wojtokuba.proj.View.Tenant.TenantRentialsListWindow;

import java.util.Collection;

public class TenantRentialDetailsViewModel {
    TenantRentialDetailsWindow tenantRentialDetailsWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    SessionStorage sessionStorage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);

    public TenantRentialDetailsViewModel(TenantRentialDetailsWindow tenantRentialDetailsWindow){
        this.tenantRentialDetailsWindow = tenantRentialDetailsWindow;
    }

    public Collection<Rential> getAllRentials(){
        return rentialStorage.findAllActiveByUser(sessionStorage.getLoggedInUser());
    }
}
