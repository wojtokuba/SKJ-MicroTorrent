package pl.wojtokuba.proj.Commands;

public abstract class BaseCommand implements Runnable{
    private String name;

    BaseCommand(String commandName){
        this.name = commandName;
    }

    public String getName() {
        return name;
    }
}
