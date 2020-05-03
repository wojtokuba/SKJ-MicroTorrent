package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//Changed name from Mieszkanie to Flat to be systematic
public class ParkingPlace {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private Map<Integer, Vehicle> items;
    private float surface;

    public ParkingPlace(){
        this.id = count.incrementAndGet();
        items = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public float getSurface() {
        return surface;
    }

    public ParkingPlace setSurface(float surface) {
        this.surface = surface;
        return this;
    }

    public Map<Integer, Vehicle> getItems() {
        return items;
    }

    public ParkingPlace addItem(Vehicle vehicle) {
        this.items.put(vehicle.getId(), vehicle);
        return this;
    }

    public ParkingPlace resetItems(){
        this.items = new HashMap<>();
        return this;
    }
}

