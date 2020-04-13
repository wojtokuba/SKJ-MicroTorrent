package pl.wojtokuba.proj.Commands;

public class CheckRentialDatesCommand extends BaseCommand {
    private static boolean isRunning;

    public CheckRentialDatesCommand() {
        super("Checking for rentials to release...");
    }

    @Override
    public void run() {
        //dont allow to overlapse multiple instances of same command at once
        if(isRunning)
            return;
        isRunning = true;

        /*
        Content of command
         */
        isRunning = false;
    }
}
