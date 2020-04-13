package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperMainWindow;
import pl.wojtokuba.proj.View.LoginWindow;

public class DeveloperMainViewModel {
    DeveloperMainWindow developerMainWindow;
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);

    public DeveloperMainViewModel(DeveloperMainWindow developerMainWindow){
        this.developerMainWindow = developerMainWindow;
    }

}
