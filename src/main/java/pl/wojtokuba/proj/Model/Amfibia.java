package pl.wojtokuba.proj.Model;

public class Amfibia extends Vehicle {

    private int maxWaterSpeed;

    public Amfibia(String vehicleName, float itemSize) {
        super(vehicleName, itemSize);
    }

    public Amfibia() {

    }

    public int getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    public Amfibia setMaxWaterSpeed(int maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
        return this;
    }

    @Override
    public String toString() {
        return "Amfibia";
    }
}
