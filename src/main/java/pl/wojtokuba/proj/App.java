package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.*;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.*;
import pl.wojtokuba.proj.View.LoginWindow;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){

//        // LOGGING FINE @TODO REMOVE THIS SECTION BEFORE SUBMITTING
//        Logger rootLog = Logger.getLogger("");
//        rootLog.setLevel( Level.FINE );
//        rootLog.getHandlers()[0].setLevel( Level.FINE ); // Default console handler
//        // END LOGGER

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
                userStorage.push(new User("ten1", SystemRoles.TENANT)
                        .setName("Jan")
                        .setLastName("Kowalski")
                        .setPesel("00202033232")
                        .setBirthDate("02-23-1996")
                );
                userStorage.push(new User("ten2", SystemRoles.TENANT)
                        .setName("Piotr")
                        .setLastName("Nowak")
                        .setPesel("11225533434")
                        .setBirthDate("02-23-1966")
                );
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
                    .setSurface(22)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(15)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("2")
                    .setSurface(33)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(11)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("3")
                    .setSurface(24)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(14)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("4")
                    .setSurface(122)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(15)
                    )
            );
        }
    }
}
