package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

//Changed name from Mieszkanie to Flat to be systematic
public class Flat implements Comparable<Flat> {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private Block block;
    private String localNo;
    private float surface;
    private ParkingPlace parkingPlace;


    public Flat(){
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public Block getBlock() {
        return block;
    }

    public Flat setBlock(Block block) {
        this.block = block;
        return this;
    }

    public String getLocalNo() {
        return localNo;
    }

    public Flat setLocalNo(String localNo) {
        this.localNo = localNo;
        return this;
    }

    public float getSurface() {
        return surface;
    }

    public Flat setSurface(float surface) {
        this.surface = surface;
        return this;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public Flat setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
        return this;
    }

    @Override
    public String toString() {
        return getBlock().getAddress() + "/"+getLocalNo()+ ", "+getSurface()+"m³";
    }

    @Override
    public int compareTo(Flat o) {
        return (int)(getSurface() - o.getSurface());
    }
}
