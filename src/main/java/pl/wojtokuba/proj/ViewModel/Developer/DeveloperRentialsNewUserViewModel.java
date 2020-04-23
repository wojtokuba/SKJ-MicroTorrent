package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Exceptions.NotTenantRoleException;
import pl.wojtokuba.proj.Exceptions.TooManyRentialsException;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperRentialsNewUserWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperRentialsNewWindow;

import java.util.Collection;

public class DeveloperRentialsNewUserViewModel {
    DeveloperRentialsNewUserWindow developerRentialsNewUserWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);

    public DeveloperRentialsNewUserViewModel(DeveloperRentialsNewUserWindow developerRentialsNewUserWindow){
        this.developerRentialsNewUserWindow = developerRentialsNewUserWindow;
    }

    public void create(){
        developerRentialsNewUserWindow.setErrorMessage("");
        if(developerRentialsNewUserWindow.getUsername().length() == 0 ||
                developerRentialsNewUserWindow.getName().length() == 0 ||
                developerRentialsNewUserWindow.getLastName().length() == 0 ||
                developerRentialsNewUserWindow.getPesel().length() == 0 ||
                developerRentialsNewUserWindow.getBirthDate().length() == 0 ||
                developerRentialsNewUserWindow.getAddress().length() == 0
        ){
            developerRentialsNewUserWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        try {
            userStorage.push(
                    new User(developerRentialsNewUserWindow.getUsername())
                        .setName(developerRentialsNewUserWindow.getName())
                        .setLastName(developerRentialsNewUserWindow.getLastName())
                        .setPesel(developerRentialsNewUserWindow.getPesel())
                        .setAddress(developerRentialsNewUserWindow.getAddress())
                        .setBirthDate(developerRentialsNewUserWindow.getBirthDate())
            );
        } catch (EntityNotUniqueException e){
            developerRentialsNewUserWindow.setErrorMessage("Użytkownik z tą nazwą już istnieje!");
            return;
        }

        developerRentialsNewUserWindow.success();
    }

    public Collection<Flat> getFreeFlats(){
        return flatStorage.findAllFree();
    }
}
