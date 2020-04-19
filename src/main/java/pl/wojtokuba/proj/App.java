package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.LoggerUtil;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SystemRoles;
import pl.wojtokuba.proj.Utils.TimeLapseManager;
import pl.wojtokuba.proj.View.LoginWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){

        // LOGGING FINE @TODO REMOVE THIS SECTION BEFORE SUBMITTING
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel( Level.FINE );
        rootLog.getHandlers()[0].setLevel( Level.FINE ); // Default console handler
        // END LOGGER

        //setup IoC injector
        SimpleInjector.Setup();
        mockData();
        //initialize first, default window
        new LoginWindow();

        //wait for time thread stop then finish
        prepareToStop();
    }

    public static void prepareToStop(){
        TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);
        if(timeLapseManager != null)
            timeLapseManager.end();
    }

    private static void mockData(){
        EstateStorage estateStorage = (EstateStorage) SimpleInjector.resolveObject(EstateStorage.class);
        BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);
        FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
        UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);
        if(estateStorage != null && blockStorage != null && userStorage != null && flatStorage != null){

            try {
                LoggerUtil.getLogger().fine("MOCKING SYSTEM USERS: [dev, ten1, ten2]...");
                userStorage.push(new User("dev", SystemRoles.DEVELOPER));
                userStorage.push(new User("ten1", SystemRoles.TENANT));
                userStorage.push(new User("ten2", SystemRoles.TENANT));
            } catch (EntityNotUniqueException e){
                e.fillInStackTrace();
            }

            LoggerUtil.getLogger().fine("Mocking Estates...");
            estateStorage.push(new Estate().setAddress("Al. Jerozolimskie 11-15").setName("Błękitne Wzgórza"));
            LoggerUtil.getLogger().fine("Mocking Blocks...");
            blockStorage.push(new Block()
                    .setEstate((Estate) estateStorage.findAll().toArray()[0])
                    .setAddress("Al. Jerozolimskie 11")
            );
            blockStorage.push(new Block()
                    .setEstate((Estate) estateStorage.findAll().toArray()[0])
                    .setAddress("Al. Jerozolimskie 12")
            );
            blockStorage.push(new Block()
                    .setEstate((Estate) estateStorage.findAll().toArray()[0])
                    .setAddress("Al. Jerozolimskie 13")
            );
            blockStorage.push(new Block()
                    .setEstate((Estate) estateStorage.findAll().toArray()[0])
                    .setAddress("Al. Jerozolimskie 14")
            );
            blockStorage.push(new Block()
                    .setEstate((Estate) estateStorage.findAll().toArray()[0])
                    .setAddress("Al. Jerozolimskie 15")
            );
            LoggerUtil.getLogger().fine("Mocking Flats...");
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("1")
                    .setSurface(65)
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("2")
                    .setSurface(65)
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("3")
                    .setSurface(65)
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("4")
                    .setSurface(65)
            );
        }
    }
}
