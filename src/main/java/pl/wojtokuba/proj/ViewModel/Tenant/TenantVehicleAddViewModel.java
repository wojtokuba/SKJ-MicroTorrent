package pl.wojtokuba.proj.ViewModel.Tenant;

import jdk.jshell.execution.Util;
import pl.wojtokuba.proj.Components.ComboBoxFormGroup;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Exceptions.TooManyThingsException;
import pl.wojtokuba.proj.Model.*;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SurfaceConverter;
import pl.wojtokuba.proj.View.Developer.DeveloperFlatsNewWindow;
import pl.wojtokuba.proj.View.Tenant.TenantVehicleAddWindow;

import java.util.ArrayList;
import java.util.Collection;

public class TenantVehicleAddViewModel {
    TenantVehicleAddWindow tenantVehicleAddWindow;
    BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    SessionStorage sessionStorage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);

    public TenantVehicleAddViewModel(TenantVehicleAddWindow tenantVehicleAddWindow){
        this.tenantVehicleAddWindow = tenantVehicleAddWindow;
    }

    public void create(){
        tenantVehicleAddWindow.setErrorMessage("");
        if(tenantVehicleAddWindow.getVehicleName().length() == 0 || tenantVehicleAddWindow.getVehicleSize().length() == 0 || tenantVehicleAddWindow.getFlat() == null){
            tenantVehicleAddWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        try {
            Vehicle vehicle = null;
            if(tenantVehicleAddWindow.getVehicleType() instanceof CityCar){
                vehicle = new CityCar(tenantVehicleAddWindow.getVehicleName(), SurfaceConverter.parseSurface(tenantVehicleAddWindow.getVehicleSize()));
                ((CityCar) vehicle).setFuelUsage(Float.parseFloat(tenantVehicleAddWindow.getFuelUsage()));
                vehicle.setVehicleName(tenantVehicleAddWindow.getVehicleName());
            }
            else if(tenantVehicleAddWindow.getVehicleType() instanceof Amfibia){
                vehicle = new Amfibia(tenantVehicleAddWindow.getVehicleName(), SurfaceConverter.parseSurface(tenantVehicleAddWindow.getVehicleSize()));
                ((Amfibia) vehicle).setMaxWaterSpeed(Integer.parseInt(tenantVehicleAddWindow.getMaxWaterSpeed()));
                vehicle.setVehicleName(tenantVehicleAddWindow.getVehicleName());
            }
            else if(tenantVehicleAddWindow.getVehicleType() instanceof TerrainCar){
                vehicle = new TerrainCar(tenantVehicleAddWindow.getVehicleName(), SurfaceConverter.parseSurface(tenantVehicleAddWindow.getVehicleSize()));
                ((TerrainCar) vehicle).setEnginePower(tenantVehicleAddWindow.getEnginePower());
                vehicle.setVehicleName(tenantVehicleAddWindow.getVehicleName());
            }
            else if(tenantVehicleAddWindow.getVehicleType() instanceof Motocycle){
                vehicle = new Motocycle(tenantVehicleAddWindow.getVehicleName(), SurfaceConverter.parseSurface(tenantVehicleAddWindow.getVehicleSize()));
                ((Motocycle) vehicle).setMaxSpeed(Integer.parseInt(tenantVehicleAddWindow.getMaxSpeed()));
                vehicle.setVehicleName(tenantVehicleAddWindow.getVehicleName());
            }
            else if(tenantVehicleAddWindow.getVehicleType() instanceof Boat){
                vehicle = new Boat(tenantVehicleAddWindow.getVehicleName(), SurfaceConverter.parseSurface(tenantVehicleAddWindow.getVehicleSize()));
                ((Boat) vehicle).setFuelTankSize(Integer.parseInt(tenantVehicleAddWindow.getFuelTankSize()));
                vehicle.setVehicleName(tenantVehicleAddWindow.getVehicleName());
            }
            if(tenantVehicleAddWindow.getFlat().getParkingPlace().getAvailableSurface() - vehicle.getItemSize() < 0){
                throw new TooManyThingsException();
            }
            tenantVehicleAddWindow.getFlat().getParkingPlace().addItem(vehicle);
            tenantVehicleAddWindow.success();
        } catch (Exception e){
            tenantVehicleAddWindow.setErrorMessage(e.getMessage());
        }
    }
    public Collection<Flat> getMyFlats(){
        Collection<Flat> result = new ArrayList<>();
        for(Rential rential : rentialStorage.findNotArchivedForUser(sessionStorage.getLoggedInUser())){
            if(rential.isParkingRent()){
                result.add(rential.getFlat());
            }
        }
        return result;
    }
    public Collection<Block> getBlocks(){
        return blockStorage.findAll();
    }
}
