package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.concurrent.atomic.AtomicInteger;

//For code quality purposes class naming changed from Osoba to User
public class User {
    private static final AtomicInteger count = new AtomicInteger(0);

    private final int id;
    private SystemRoles permissions;
    private String username;
    private String pesel;
    private String name;
    private String lastName;
    private String address;
    private String birthDate;

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

    public String getPesel() {
        return pesel;
    }

    public User setPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Override
    public String toString() {
        return "["+username+"] "+name+" "+lastName;
    }
}