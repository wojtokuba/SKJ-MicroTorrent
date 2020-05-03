package pl.wojtokuba.proj.Model;

public class TerrainCar extends Vehicle {

    private String enginePower;

    public String getEnginePower() {
        return enginePower;
    }

    public TerrainCar setEnginePower(String enginePower) {
        this.enginePower = enginePower;
        return this;
    }

    @Override
    public String toString() {
        return "Samochód terenowy";
    }
}
