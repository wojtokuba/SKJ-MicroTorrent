package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperBlocksNewWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesNewWindow;

import java.util.Collection;

public class DeveloperBlocksNewViewModel {
    DeveloperBlocksNewWindow developerBlocksNewWindow;
    EstateStorage estateStorage = (EstateStorage) SimpleInjector.resolveObject(EstateStorage.class);
    BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    public DeveloperBlocksNewViewModel(DeveloperBlocksNewWindow developerBlocksNewWindow){
        this.developerBlocksNewWindow = developerBlocksNewWindow;
    }

    public void create(){
        developerBlocksNewWindow.setErrorMessage("");
        if(developerBlocksNewWindow.getEstate() == null || developerBlocksNewWindow.getAddress().length() == 0){
            developerBlocksNewWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        blockStorage.push(new Block()
                        .setEstate(developerBlocksNewWindow.getEstate())
                        .setAddress(developerBlocksNewWindow.getAddress())
        );
        developerBlocksNewWindow.success();
    }

    public Collection<Estate> getEstates(){
        return estateStorage.findAll();
    }
}
