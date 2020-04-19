package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperBlocksListWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperFlatsListWindow;

import java.util.Collection;

public class DeveloperFlatsListViewModel {
    DeveloperFlatsListWindow developerFlatsListWindow;
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);

    public DeveloperFlatsListViewModel(DeveloperFlatsListWindow developerFlatsListWindow){
        this.developerFlatsListWindow = developerFlatsListWindow;
    }

    public Collection<Flat> getFlats(){
        return flatStorage.findAll();
    }

}
