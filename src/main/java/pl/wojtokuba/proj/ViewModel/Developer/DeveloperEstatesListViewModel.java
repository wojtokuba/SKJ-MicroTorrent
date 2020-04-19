package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesListWindow;

import java.util.Collection;

public class DeveloperEstatesListViewModel {
    DeveloperEstatesListWindow developerEstatesListWindow;
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);
    EstateStorage estateStorage = (EstateStorage) SimpleInjector.resolveObject(EstateStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);

    public DeveloperEstatesListViewModel(DeveloperEstatesListWindow developerEstatesListWindow){
        this.developerEstatesListWindow = developerEstatesListWindow;
    }

    public Collection<Estate> getEstates(){
        return estateStorage.findAll();
    }

    public Collection<Flat> getFlatsByEstate(Estate estate){
        return flatStorage.findByEstate(estate);
    }
}
