package pl.wojtokuba.proj.ViewModel;

import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SystemRoles;
import pl.wojtokuba.proj.View.Developer.DeveloperMainWindow;
import pl.wojtokuba.proj.View.LoginWindow;
import pl.wojtokuba.proj.View.Tenant.TenantMainWindow;

public class LoginViewModel {
    LoginWindow loginWindow;
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);
    SessionStorage sessionStorage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);
    public LoginViewModel(LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }


    public void LoginUser(){
        loginWindow.setErrorMessage("");
        User logged = userStorage.findOneByUsername(loginWindow.getUsername());
        if(logged == null){
            loginWindow.setErrorMessage("Nie znaleziono uzytkownika.");
            return;
        }
        loginWindow.close();
        sessionStorage.login(logged);
        if(logged.getPermissions() == SystemRoles.DEVELOPER){
            new DeveloperMainWindow();
        } else {
            new TenantMainWindow();
        }
    }
}
