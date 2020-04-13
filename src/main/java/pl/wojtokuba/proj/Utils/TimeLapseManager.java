package pl.wojtokuba.proj.Utils;

import pl.wojtokuba.proj.Commands.BaseCommand;
import pl.wojtokuba.proj.Commands.CheckRentialDatesCommand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        timeThread = new Thread(this);
        timeThread.start();
        appDate = new Date();
        System.out.println("Setting date to: " + dateFormat.format(appDate));
        //queue checking rential dates
        queue.add(new CheckRentialDatesCommand());
    }
    public void end(){
        isFinishPlanned = true;
        System.out.println("Time thread is planned for stop...");
    }

    private void runQueue(){
        if(everyTenSecs == 10) {
            for (BaseCommand command : queue) {
                //execute every command in new thread in order not to change time distances
                new Thread(command).start();
            }
            System.out.println("Running check crons");
            everyTenSecs = 0;
        }
        everyTenSecs++;
    }

    private void timeLapses(){
        if(everyFiveSecs == 5){
            Calendar c = Calendar.getInstance();
            c.setTime(appDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            appDate = c.getTime();
            System.out.println("Time lapses, new day has begun: " + dateFormat.format(appDate));
            everyFiveSecs = 0;
        }
        everyFiveSecs++;
    }

    @Override
    public void run() {
        while (true){
            //leave thread on interrupt
            if(isFinishPlanned)
                break;
            runQueue();
            timeLapses();
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
