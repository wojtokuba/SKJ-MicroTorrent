package pl.wojtokuba.proj.Model;

public class CityCar extends Vehicle {

    private float fuelUsage;

    public CityCar(String vehicleName, float itemSize) {
        super(vehicleName, itemSize);
    }

    public CityCar() {
        super();
    }

    public float getFuelUsage() {
        return fuelUsage;
    }

    public CityCar setFuelUsage(float fuelUsage) {
        this.fuelUsage = fuelUsage;
        return this;
    }

    @Override
    public String toString() {
        return "Samochód miejski";
    }
}
