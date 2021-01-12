package pl.wojtokuba.proj.Utils;

import pl.wojtokuba.proj.Commands.BaseCommand;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeLapseManager implements Runnable{
    private static Thread timeThread;
    private static List<BaseCommand> queue = new ArrayList<BaseCommand>();
    private static Date appDate;
    private boolean isFinishPlanned = false;
    private final int timeInterval = 1000;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private static int everyFiveSecs = 0;
    private static int everyTenSecs = 0;

    public TimeLapseManager(){

    }

    public void start(){
        timeThread = new Thread(this);
        appDate = new Date();
        LoggerUtil.getLogger().fine("Setting date to: " + dateFormat.format(appDate));
        //queue checking rential dates
//        queue.add(new CheckRentialDatesCommand());
//        queue.add(new ExecuteExmissionCommand());
//        queue.add(new RemoveFilesForProlongatedCommand());
        timeThread.start();
    }
    public void end(){
        isFinishPlanned = true;
        LoggerUtil.getLogger().fine("Time thread is planned for stop...");
    }

    //queue is executed every 10 seconds.
    private void runQueue(){
        if(everyTenSecs == 10) {
            for (BaseCommand command : queue) {
                //execute every command in new thread in order not to change time distances
                new Thread(command).start();
            }
            LoggerUtil.getLogger().fine("Running check crons");
            everyTenSecs = 0;
        }
        everyTenSecs++;
    }

    //next day comes every 5 seconds
    private void timeLapses(){
        if(everyFiveSecs == 5){
            Calendar c = Calendar.getInstance();
            c.setTime(appDate);
            c.add(Calendar.DATE, 1);
            appDate = c.getTime();
            LoggerUtil.getLogger().fine("Time lapses, new day has begun: " + dateFormat.format(appDate));
            everyFiveSecs = 0;
        }
        everyFiveSecs++;
    }

    public Date getAppDate(){
        return appDate;
    }

    @Override
    public void run() {

        //loop until we plan to close the app.
        while (!isFinishPlanned) {
            runQueue();
            timeLapses();
            try {
                //loop slows down for timeInterval value (1000 ms by default)
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Timestamp addDays(long timestamp, int days){
        Timestamp ts = new Timestamp(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DATE, days);
        ts.setTime(cal.getTime().getTime());
        return ts;
    }

    public static Timestamp addMonths(long timestamp, int months){
        Timestamp ts = new Timestamp(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.MONTH, months);
        ts.setTime(cal.getTime().getTime());
        return ts;
    }
}
