package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class UserStorage {
    private final HashMap<Integer, User> users = new HashMap<>();
    private final Set<String> uniqueUsername = new HashSet<>();

    public UserStorage push(User user) throws EntityNotUniqueException {
        if(uniqueUsername.contains(user.getUsername()))
            throw new EntityNotUniqueException();
        this.users.put(user.getId(), user);
        return this;
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
