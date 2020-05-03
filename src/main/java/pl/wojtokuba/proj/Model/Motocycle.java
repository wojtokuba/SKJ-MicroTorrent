package pl.wojtokuba.proj.Model;

public class Motocycle extends Vehicle {

    private int maxSpeed;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public Motocycle setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    @Override
    public String toString() {
        return "Motocykl";
    }
}
