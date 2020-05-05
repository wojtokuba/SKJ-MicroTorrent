package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Exceptions.EntityNotUniqueException;
import pl.wojtokuba.proj.Exceptions.NotTenantRoleException;
import pl.wojtokuba.proj.Exceptions.ProblematicTenantException;
import pl.wojtokuba.proj.Exceptions.TooManyRentialsException;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Storage.BlockStorage;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SurfaceConverter;
import pl.wojtokuba.proj.Utils.SystemRoles;
import pl.wojtokuba.proj.Utils.TimeLapseManager;
import pl.wojtokuba.proj.View.Developer.DeveloperFlatsNewWindow;
import pl.wojtokuba.proj.View.Developer.DeveloperRentialsNewWindow;

import java.util.Collection;

public class DeveloperRentialsNewViewModel {
    DeveloperRentialsNewWindow developerRentialsNewWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);

    public DeveloperRentialsNewViewModel(DeveloperRentialsNewWindow developerRentialsNewWindow){
        this.developerRentialsNewWindow = developerRentialsNewWindow;
    }

    public void create(){
        developerRentialsNewWindow.setErrorMessage("");
        if(developerRentialsNewWindow.getEndDateValue().length() == 0 || developerRentialsNewWindow.getFlat() == null){
            developerRentialsNewWindow.setErrorMessage("Wszystkie pola muszą być wypełnione!");
            return;
        }
        try {
            //check is user not called 3 tripple
            Collection<Rential> called = rentialStorage.findCalledForUser(developerRentialsNewWindow.getUsername());
            if(called.size() >= 3){
                StringBuilder rooms = new StringBuilder();
                for (Rential rential : called){
                    rooms.append(rential.getFlat()).append(", ");
                }
                throw new ProblematicTenantException("Osoba "+
                        developerRentialsNewWindow.getUsername().getFullName()+
                        " posiadała już najem pomieszczeń: "+rooms);
            }
            //end

            rentialStorage.push(
                    new Rential()
                            .setOwner(developerRentialsNewWindow.getUsername())
                            .setFlat(developerRentialsNewWindow.getFlat())
                            .setRentEnd(developerRentialsNewWindow.getEndDate())
                            .setParkingRent(developerRentialsNewWindow.isParkingRent())
            );
        } catch (NotTenantRoleException e){
            developerRentialsNewWindow.setErrorMessage("Podany użytkownik jest deweloperem!");
            return;
        } catch (TooManyRentialsException e) {
            developerRentialsNewWindow.setErrorMessage("Łączna ilość miejsc parkingowych i mieszkań przekracza 5. Wynajem nieudany!");
            return;
        } catch (ProblematicTenantException e){
            developerRentialsNewWindow.setErrorMessage(e.getMessage());
            return;
        } catch (Exception e){
            e.printStackTrace();
            developerRentialsNewWindow.setErrorMessage("Wystąpił błąd. Upewnij się, że podałeś prawidłowe wartości!");
            return;
        }

        developerRentialsNewWindow.success();
    }

    public Collection<Flat> getFreeFlats(){
        return flatStorage.findAllFree();
    }

    public Collection<User> getTenants(){
        return userStorage.findByRole(SystemRoles.TENANT);
    }
}
