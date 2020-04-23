package pl.wojtokuba.proj.Commands;

import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

public class CheckRentialDatesCommand extends BaseCommand {
    private static boolean isRunning;

    public CheckRentialDatesCommand() {
        super("Checking for rentials to release...");
    }

    private final RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);

    @Override
    public void run() {
        //dont allow to overlapse multiple instances of same command at once
        if(isRunning)
            return;
        isRunning = true;
        assert rentialStorage != null;
        for(Rential rential : rentialStorage.findFinishedNotArchived()){
            System.out.println("ARCHIVING "+ rential.toString());
            rential.setArchived(true);
            rential.getFlat().getParkingPlace().resetItems();
        }
        /*
        Content of command
         */
        isRunning = false;
    }
}
