package pl.wojtokuba.proj.ViewModel;

import pl.wojtokuba.proj.View.LoginWindow;

public class LoginViewModel {
    LoginWindow loginWindow;
    public LoginViewModel(LoginWindow loginWindow){
        this.loginWindow = loginWindow;
    }
    public void LoginUser(){
        System.out.println("Logging in...");
    }
}
