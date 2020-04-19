package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BlockStorage {
    private final HashMap<Integer, Block> block = new HashMap<>();

    public BlockStorage push(Block block) {
        this.block.put(block.getId(), block);
        return this;
    }

    public boolean drop(Block block){
        this.block.remove(block.getId());
        return true;
    }

    public Block findOneById(int id) {
        try {
            return this.block.get(id);
        } catch (Exception e){
            return null;
        }
    }

    public Collection<Block> findAll(){
        return new ArrayList<>(this.block.values());
    }

    public Collection<Block> findByEstate(Estate estate){
        Collection<Block> result = new ArrayList<>();
        for(Block block : this.block.values()){
            if(block.getEstate() == estate){
                result.add(block);
            }
        }
        return result;
    }
}
