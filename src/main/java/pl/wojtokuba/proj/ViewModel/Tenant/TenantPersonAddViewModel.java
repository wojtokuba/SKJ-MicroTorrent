package pl.wojtokuba.proj.ViewModel.Tenant;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Tenant.TenantPersonAddWindow;

import java.util.ArrayList;
import java.util.Collection;

public class TenantPersonAddViewModel {
    TenantPersonAddWindow tenantPersonAddWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);
    SessionStorage sessionStorage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);

    public TenantPersonAddViewModel(TenantPersonAddWindow tenantPersonAddWindow){
        this.tenantPersonAddWindow = tenantPersonAddWindow;
    }

    public void create(){
        tenantPersonAddWindow.setErrorMessage("");
        if(tenantPersonAddWindow.getUsername().length() == 0 ||
                tenantPersonAddWindow.getName().length() == 0 ||
                tenantPersonAddWindow.getLastName().length() == 0 ||
                tenantPersonAddWindow.getPesel().length() == 0 ||
                tenantPersonAddWindow.getBirthDate().length() == 0 ||
                tenantPersonAddWindow.getAddress().length() == 0 ||
                tenantPersonAddWindow.getSelectedFlat() == null
        ){
            tenantPersonAddWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        try {
            User user = new User(tenantPersonAddWindow.getUsername())
                    .setName(tenantPersonAddWindow.getName())
                    .setLastName(tenantPersonAddWindow.getLastName())
                    .setPesel(tenantPersonAddWindow.getPesel())
                    .setAddress(tenantPersonAddWindow.getAddress())
                    .setBirthDate(tenantPersonAddWindow.getBirthDate());
            userStorage.push(user);
            Rential rential = rentialStorage.findOneActiveByFlat(tenantPersonAddWindow.getSelectedFlat());
            rential.setCompanion(user);
        } catch (EntityNotUniqueException e){
            tenantPersonAddWindow.setErrorMessage("Użytkownik z tą nazwą już istnieje!");
            return;
        }

        tenantPersonAddWindow.success();
    }

    public Collection<Flat> getMyFlats(){
        Collection<Flat> result = new ArrayList<>();
        for(Rential rential : rentialStorage.findAllActiveByUser(sessionStorage.getLoggedInUser())){
            result.add(rential.getFlat());
        }
        return result;
    }}
