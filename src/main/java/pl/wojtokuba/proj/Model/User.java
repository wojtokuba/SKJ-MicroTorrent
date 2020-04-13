package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.concurrent.atomic.AtomicInteger;

//For code quality purposes class naming changed from Osoba to User
public class User {
    private static final AtomicInteger count = new AtomicInteger(1);

    private int id;
    private SystemRoles permissions;
    private String username;

    public User(String username){
        this.id = count.incrementAndGet();
        this.permissions = SystemRoles.TENANT;
        this.username = username;
    }

}