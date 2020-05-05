package pl.wojtokuba.proj.Commands;

import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;

public class RemoveFilesForProlongatedCommand extends BaseCommand {
    private static boolean isRunning;

    public RemoveFilesForProlongatedCommand() {
        super("Removing files for all prolongated rentials...");
    }

    private final RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);

    @Override
    public void run() {
        //dont allow to overlapse multiple instances of same command at once
        if(isRunning)
            return;
        isRunning = true;
        assert rentialStorage != null;
        for(Rential rential : rentialStorage.findAllNotFinished()){
            if(rential.getPayCall() != null){
                rential.setPayCall(null);
            }
        }
        /*
        Content of command
         */
        isRunning = false;
    }
}
