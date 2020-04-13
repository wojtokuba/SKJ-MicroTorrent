package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.concurrent.atomic.AtomicInteger;

//For code quality purposes class naming changed from Osoba to User
public class User {
    private static final AtomicInteger count = new AtomicInteger(1);

    private int id;
    private SystemRoles permissions;
    private String username;

    public User(String username, SystemRoles permissions){
        this.id = count.incrementAndGet();
        this.permissions = permissions;
        this.username = username;
    }

    public User(String username){
        this.id = count.incrementAndGet();
        this.permissions = SystemRoles.TENANT;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public SystemRoles getPermissions() {
        return permissions;
    }

    public void setPermissions(SystemRoles permissions) {
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}