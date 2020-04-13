package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Utils.SystemRoles;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Vehicle {
    private static final AtomicInteger count = new AtomicInteger(1);

    private int id;
    private String vehicleName;
    private ParkingPlace assignedParkingPlace;

    public Vehicle(String vehicleName){
        this.id = count.incrementAndGet();
        this.vehicleName = vehicleName;
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

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public ParkingPlace getAssignedParkingPlace() {
        return assignedParkingPlace;
    }

    public void setAssignedParkingPlace(ParkingPlace assignedParkingPlace) {
        this.assignedParkingPlace = assignedParkingPlace;
    }
}
