package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserStorage {
    private HashMap<Integer, User> users = new HashMap<>();
    private Set<String> uniqueUsername = new HashSet<>();

    public UserStorage(){
        MockUsers();
    }

    public UserStorage push(User user) throws EntityNotUniqueException {
        if(uniqueUsername.contains(user.getUsername()))
            throw new EntityNotUniqueException();
        this.users.put(user.getId(), user);
        return this;
    }

    public void MockUsers(){
        try {
            System.out.println("MOCKING SYSTEM USERS: dev, ten1, ten2!!!");
            this.push(new User("dev", SystemRoles.DEVELOPER));
            this.push(new User("ten1", SystemRoles.TENANT));
            this.push(new User("ten2", SystemRoles.TENANT));
        } catch (EntityNotUniqueException e){
            e.fillInStackTrace();
        }
    }

    public boolean drop(User user){
        try {
            uniqueUsername.remove(user.getUsername());
            this.users.remove(user.getId());
            return true;
        } catch (Exception e){
           return true;
        }
    }

    public User findOneById(int id) {
        try {
            return this.users.get(id);
        } catch (Exception e){
            return null;
        }
    }

    public Collection<User> findByRole(SystemRoles role){
        Collection<User> result = new ArrayList<>();
        users.forEach((id, user) -> {
            if(user.getPermissions() == role)
                result.add(user);
        });
        return result;
    }

    public User findOneByUsername(String username) {
        try {
            for(User user : this.users.values()){
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
            return null;
        } catch (Exception e){
            return null;
        }
    }
}
