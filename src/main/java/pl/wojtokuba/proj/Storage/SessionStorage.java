package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.User;


public class SessionStorage {
    private User loggedInUser = null;

    public SessionStorage(){

    }

    public SessionStorage login(User user) {
        this.loggedInUser = user;
        return this;
    }

    public SessionStorage logout() {
        this.loggedInUser = null;
        return this;
    }

    public boolean isLoggedIn(){
        return loggedInUser != null;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }
}
