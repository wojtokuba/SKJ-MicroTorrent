package pl.wojtokuba.proj;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Model.*;
import pl.wojtokuba.proj.Storage.*;
import pl.wojtokuba.proj.Utils.*;
import pl.wojtokuba.proj.View.LoginWindow;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args){

        // LOGGING FINE
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel( Level.FINE );
        rootLog.getHandlers()[0].setLevel( Level.FINE ); // Default console handler
        // END LOGGER

        //setup IoC injector
        SimpleInjector.Setup();
        TimeLapseManager timeLapseManager = (TimeLapseManager)SimpleInjector.resolveObject(TimeLapseManager.class);
        assert timeLapseManager != null;
        timeLapseManager.start();
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
        RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
        TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);

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
                userStorage.push(new User("ten3", SystemRoles.TENANT)
                        .setName("Piotr")
                        .setLastName("Gancarczyk")
                        .setPesel("11225533434")
                        .setBirthDate("02-23-1966")
                );
                userStorage.push(new User("ten4", SystemRoles.TENANT)
                        .setName("Grzegorz")
                        .setLastName("Szewczuk")
                        .setPesel("11225533434")
                        .setBirthDate("02-23-1966")
                );
                userStorage.push(new User("ten5", SystemRoles.TENANT)
                        .setName("Tomasz")
                        .setLastName("Antosik")
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
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("5")
                    .setSurface(165)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(15)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("6")
                    .setSurface(69)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(14)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("7")
                    .setSurface(43)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(24)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("8")
                    .setSurface(23)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(6)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("9")
                    .setSurface(65)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(11)
                    )
            );
            flatStorage.push(new Flat()
                    .setBlock(blockStorage.findOneById(1))
                    .setLocalNo("10")
                    .setSurface(232)
                    .setParkingPlace(new ParkingPlace()
                            .setSurface(100)
                    )
            );
            try {
                flatStorage
                        .findOneById(1)
                        .getParkingPlace()
                        .addItem(new CityCar("Szybka miejska fura", 2).setFuelUsage(33))
                        .addItem(new CityCar("Szybsza miejska fura", 2).setFuelUsage(33))
                        .addItem(new Boat("Dar pomorza", 5).setFuelTankSize(11));
                flatStorage
                        .findOneById(2)
                        .getParkingPlace()
                        .addItem(new CityCar("Porsche 911", 3).setFuelUsage(33))
                        .addItem(new TerrainCar("Volvo XC60", 1).setEnginePower("190KM"))
                        .addItem(new Boat("Dziecinka", 5).setFuelTankSize(11));
                flatStorage
                        .findOneById(3)
                        .getParkingPlace()
                        .addItem(new Amfibia("Amfibia", 1).setMaxWaterSpeed(33))
                        .addItem(new Motocycle("Suzuki", 4).setMaxSpeed(280))
                        .addItem(new CityCar("Mini Cooper S", 3).setFuelUsage(1));
                rentialStorage.push(new Rential()
                        .setFlat(flatStorage.findOneById(1))
                        .setOwner(userStorage.findOneById(2))
                        .setCompanion(userStorage.findOneById(5))
                        .setRentEnd(TimeLapseManager.addDays(timeLapseManager.getAppDate().getTime(), 3))
                        .setParkingRent(true)
                );
                rentialStorage.push(new Rential()
                        .setFlat(flatStorage.findOneById(2))
                        .setOwner(userStorage.findOneById(3))
                        .setRentEnd(TimeLapseManager.addDays(timeLapseManager.getAppDate().getTime(), 5))
                        .setParkingRent(true)
                );
                rentialStorage.push(new Rential()
                        .setFlat(flatStorage.findOneById(3))
                        .setOwner(userStorage.findOneById(4))
                        .setRentEnd(TimeLapseManager.addDays(timeLapseManager.getAppDate().getTime(), 12))
                        .setParkingRent(true)
                );
                rentialStorage.push(new Rential()
                        .setFlat(flatStorage.findOneById(4))
                        .setOwner(userStorage.findOneById(5))
                        .setRentEnd(TimeLapseManager.addDays(timeLapseManager.getAppDate().getTime(), 10))
                        .setParkingRent(true)
                );
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
