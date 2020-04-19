package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.Estate;

import java.util.*;

public class EstateStorage {
    private final HashMap<Integer, Estate> estate = new HashMap<>();

    public EstateStorage push(Estate estate) {
        this.estate.put(estate.getId(), estate);
        return this;
    }

    public boolean drop(Estate estate){
        this.estate.remove(estate.getId());
        return true;
    }

    public Estate findOneById(int id) {
        try {
            return this.estate.get(id);
        } catch (Exception e){
            return null;
        }
    }

    public Collection<Estate> findAll(){
        return new ArrayList<>(this.estate.values());
    }
}
