package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperBlocksListWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesListWindow;

import java.util.Collection;

public class DeveloperBlocksListViewModel {
    DeveloperBlocksListWindow developerBlocksListWindow;
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);
    EstateStorage estateStorage = (EstateStorage) SimpleInjector.resolveObject(EstateStorage.class);
    BlockStorage blockStorage = (BlockStorage) SimpleInjector.resolveObject(BlockStorage.class);

    public DeveloperBlocksListViewModel(DeveloperBlocksListWindow developerBlocksListWindow){
        this.developerBlocksListWindow = developerBlocksListWindow;
    }

    public Collection<Block> getBlocks(){
        return blockStorage.findAll();
    }

}
