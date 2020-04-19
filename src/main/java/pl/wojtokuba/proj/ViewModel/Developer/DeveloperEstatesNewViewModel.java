package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.Storage.EstateStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesListWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperEstatesNewWindow;

public class DeveloperEstatesNewViewModel {
    DeveloperEstatesNewWindow developerEstatesNewWindow;
    EstateStorage estateStorage = (EstateStorage) SimpleInjector.resolveObject(EstateStorage.class);
    private MainViewManager viewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);

    public DeveloperEstatesNewViewModel(DeveloperEstatesNewWindow developerEstatesNewWindow){
        this.developerEstatesNewWindow = developerEstatesNewWindow;
    }

    public void create(){
        developerEstatesNewWindow.setErrorMessage("");
        if(developerEstatesNewWindow.getName().length() == 0 || developerEstatesNewWindow.getAddress().length() == 0){
            developerEstatesNewWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        estateStorage.push(new Estate().setName(developerEstatesNewWindow.getName()).setAddress(developerEstatesNewWindow.getAddress()));
        developerEstatesNewWindow.success();
    }

}
