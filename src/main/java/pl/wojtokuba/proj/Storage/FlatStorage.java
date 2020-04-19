package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Model.Flat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FlatStorage {
    private final HashMap<Integer, Flat> flat = new HashMap<>();

    public FlatStorage push(Flat Flat) {
        this.flat.put(Flat.getId(), Flat);
        return this;
    }

    public boolean drop(Flat Flat){
        this.flat.remove(Flat.getId());
        return true;
    }

    public Flat findOneById(int id) {
        try {
            return this.flat.get(id);
        } catch (Exception e){
            return null;
        }
    }

    public Collection<Flat> findAll(){
        return new ArrayList<>(this.flat.values());
    }

    public Collection<Flat> findByBlock(Block block){
        Collection<Flat> result = new ArrayList<>();
        for(Flat flat : this.flat.values()){
            if(flat.getBlock() == block){
                result.add(flat);
            }
        }
        return result;
    }

    public Collection<Flat> findByEstate(Estate estate){
        Collection<Flat> result = new ArrayList<>();
        for(Flat flat : this.flat.values()){
            if(flat.getBlock().getEstate() == estate){
                result.add(flat);
            }
        }
        return result;
    }
}
