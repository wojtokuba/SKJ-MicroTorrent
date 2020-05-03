package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.ParkingPlace;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SurfaceConverter;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesNewWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperFlatsNewWindow;

import java.util.Collection;

public class DeveloperFlatsNewViewModel {
    DeveloperFlatsNewWindow developerFlatsNewWindow;
    BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);

    public DeveloperFlatsNewViewModel(DeveloperFlatsNewWindow developerFlatsNewWindow){
        this.developerFlatsNewWindow = developerFlatsNewWindow;
    }

    public void create(){
        developerFlatsNewWindow.setErrorMessage("");
        if(developerFlatsNewWindow.getLocalNo().length() == 0 || developerFlatsNewWindow.getBlock() == null){
            developerFlatsNewWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        flatStorage.push(new Flat()
                .setLocalNo(developerFlatsNewWindow.getLocalNo())
                .setBlock(developerFlatsNewWindow.getBlock())
                .setSurface(SurfaceConverter.parseSurface(developerFlatsNewWindow.getSurface()))
                .setParkingPlace(new ParkingPlace()
                        .setSurface(SurfaceConverter.parseSurface(developerFlatsNewWindow.getParkingSurface()))
                )
        );
        developerFlatsNewWindow.success();
    }

    public Collection<Block> getBlocks(){
        return blockStorage.findAll();
    }
}
