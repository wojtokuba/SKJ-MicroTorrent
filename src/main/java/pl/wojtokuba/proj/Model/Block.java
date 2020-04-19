package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Block {
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);

    private final int id;
    private String address;
    private Estate estate;

    public Block(){
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Block setAddress(String address) {
        this.address = address;
        return this;
    }

    public Estate getEstate() {
        return estate;
    }

    public Block setEstate(Estate estate) {
        this.estate = estate;
        return this;
    }

    public Collection<Flat> getFlats(){
        if(flatStorage != null)
            return flatStorage.findByBlock(this);
        return new ArrayList<>();
    }
}
