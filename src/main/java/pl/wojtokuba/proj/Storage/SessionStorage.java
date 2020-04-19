package pl.wojtokuba.proj.Storage;

import com.googlecode.lanterna.gui2.Window;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.LoginWindow;


public class SessionStorage {
    private User loggedInUser = null;
    private final MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    public SessionStorage login(User user) {
        this.loggedInUser = user;
        return this;
    }

    public SessionStorage logout(MainViewManager viewManager) {
        this.loggedInUser = null;
        viewManager.getWindowBasedTextGUI().getWindows().forEach(Window::close);
        new LoginWindow();
        return this;
    }

    public boolean isLoggedIn(){
        return loggedInUser != null;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }
}
