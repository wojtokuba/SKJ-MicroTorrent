package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Estate {
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);
    private final int id;
    private String name;
    private String address;

    public Estate(){
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Estate setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Estate setAddress(String address) {
        this.address = address;
        return this;
    }

    public Collection<Block> getBlocks(){
        if(blockStorage != null)
            return blockStorage.findByEstate(this);
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return getName();
    }
}
