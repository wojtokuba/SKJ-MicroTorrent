package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Vehicle {
    private static final AtomicInteger count = new AtomicInteger(1);

    private final int id;
    private String vehicleName;
    private float itemSize;

    public Vehicle(String vehicleName, float itemSize){
        this.id = count.incrementAndGet();
        this.vehicleName = vehicleName;
        this.itemSize = itemSize;
    }

    public Vehicle(){
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public Vehicle setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
        return this;
    }

    public float getItemSize() {
        return itemSize;
    }

}
