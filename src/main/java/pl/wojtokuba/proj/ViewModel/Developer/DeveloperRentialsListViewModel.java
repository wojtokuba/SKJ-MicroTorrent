package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperFlatsListWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperRentialsListWindow;

import java.util.Collection;

public class DeveloperRentialsListViewModel {
    DeveloperRentialsListWindow developerRentialsListWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);

    public DeveloperRentialsListViewModel(DeveloperRentialsListWindow developerRentialsListWindow){
        this.developerRentialsListWindow = developerRentialsListWindow;
    }

    public Collection<Rential> getAllRentials(){
        return rentialStorage.findAllActiveFirst();
    }
}
