package pl.wojtokuba.proj.Model;

public class Amfibia extends Vehicle {

    private int maxWaterSpeed;

    public int getMaxWaterSpeed() {
        return maxWaterSpeed;
    }

    public void setMaxWaterSpeed(int maxWaterSpeed) {
        this.maxWaterSpeed = maxWaterSpeed;
    }

    @Override
    public String toString() {
        return "Amfibia";
    }
}
