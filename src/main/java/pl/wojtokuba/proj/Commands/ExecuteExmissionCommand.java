package pl.wojtokuba.proj.Commands;

import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.LoggerUtil;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.TimeLapseManager;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ExecuteExmissionCommand extends BaseCommand {
    private static boolean isRunning;

    public ExecuteExmissionCommand() {
        super("Executing rential exmissions...");
    }

    private final RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    private final TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);

    @Override
    public void run() {
        //dont allow to overlapse multiple instances of same command at once
        if(isRunning)
            return;
        isRunning = true;
        assert rentialStorage != null;
        for(Rential rential : rentialStorage.findFinishedNotArchived()){
            Date now = new Date(timeLapseManager.getAppDate().getTime());
            Date rentEnd = new Date(rential.getRentEnd().getTime());
            //
            long daysDiff = TimeUnit.DAYS.convert(now.getTime() - rentEnd.getTime(), TimeUnit.MILLISECONDS);
            LoggerUtil.getLogger().fine("Time diff is :"+ daysDiff+ " for : " +  rential.toString());
            if(daysDiff >= 30){
                LoggerUtil.getLogger().fine("- Exmission has begun for: "+ rential.toString());

                //if items in parking place exists
                if(rential.getFlat().getParkingPlace().getItems().size() > 0){
                    LoggerUtil.getLogger().fine("-- Found items in parking place. Selling...");
                    rential.getFlat().getParkingPlace().resetItems();
                    //Prolongate rential for 60 days
                    rential.setRentEnd(TimeLapseManager.addMonths(rential.getRentEnd().getTime(), 2));
                    LoggerUtil.getLogger().fine("-- Rential prolongated for 2 months...");

                } else {
                    LoggerUtil.getLogger().fine("-- No more items in parking place. Rential cleanup...");
                    rential.setArchived(true);
                    rential.getFlat().getParkingPlace().resetItems();
                }
            }
        }
        /*
        Content of command
         */
        isRunning = false;
    }
}
