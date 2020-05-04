package pl.wojtokuba.proj.Model;

public class Boat extends Vehicle {

    private int fuelTankSize;

    public Boat(String vehicleName, float itemSize) {
        super(vehicleName, itemSize);
    }

    public Boat() {

    }

    public int getFuelTankSize() {
        return fuelTankSize;
    }

    public Boat setFuelTankSize(int fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
        return this;
    }

    @Override
    public String toString() {
        return "Łódź";
    }
}
