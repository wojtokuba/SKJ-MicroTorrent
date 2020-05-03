package pl.wojtokuba.proj.ViewModel.Tenant;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.ParkingPlace;
import pl.wojtokuba.proj.Model.Rential;
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
        System.out.println("SUBMIT");
        tenantVehicleAddWindow.setErrorMessage("");
//        if(tenantVehicleAddWindow.getLocalNo().length() == 0 || tenantVehicleAddWindow.getBlock() == null){
//            tenantVehicleAddWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
//            return;
//        }
//        developerFlatsNewWindow.success();
    }

    public Collection<Flat> getMyFlats(){
        Collection<Flat> result = new ArrayList<>();
        for(Rential rential : rentialStorage.findAllActiveByUser(sessionStorage.getLoggedInUser())){
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
