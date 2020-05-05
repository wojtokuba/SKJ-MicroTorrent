package pl.wojtokuba.proj.ViewModel.Developer;

import pl.wojtokuba.proj.Exceptions.NotTenantRoleException;
import pl.wojtokuba.proj.Exceptions.ProblematicTenantException;
import pl.wojtokuba.proj.Exceptions.TooManyRentialsException;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Model.Vehicle;
import pl.wojtokuba.proj.Storage.FlatStorage;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Storage.UserStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SystemRoles;
import pl.wojtokuba.proj.View.Developer.DeveloperRentialsNewWindow;
import pl.wojtokuba.proj.View.Developer.ExportWindow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ExportViewModel {
    ExportWindow exportWindow;
    RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
    FlatStorage flatStorage = (FlatStorage) SimpleInjector.resolveObject(FlatStorage.class);
    UserStorage userStorage = (UserStorage) SimpleInjector.resolveObject(UserStorage.class);

    public ExportViewModel(ExportWindow exportWindow){
        this.exportWindow = exportWindow;
    }

    public String createReport(){
        File file = new File("lokatorzy.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("lokatorzy.txt"));
            ArrayList<Rential> rentials = new ArrayList<>(this.getRentials());
            //sort
            rentials.sort((Rential a, Rential b) -> (int) (a.getFlat().getSurface() - b.getFlat().getSurface()));

            for (Rential rential : rentials){
                ArrayList<Vehicle> vehicles = new ArrayList<>(rential.getFlat().getParkingPlace().getItems().values());
                //sort ASC
                vehicles.sort((Vehicle a, Vehicle b) -> {
                    int size = (int)(b.getItemSize() - a.getItemSize());
                    if(size == 0){
                        return b.getVehicleName().compareTo(a.getVehicleName());
                    } else {
                        return size;
                    }
                });
                writer.write("-------------MIESZKANIE---------------\n");
                writer.write("Mieszkanie: "+rential.getFlat()+"\n");
                writer.write("- Właściciel: "+rential.getOwner()+"\n");
                writer.write("- Lokatorzy: \n");
                for (User user : rential.getCompanions().values()){
                    writer.write("-- "+user);
                    writer.newLine();
                }
                writer.write("- Pojazdy: \n");
                for (Vehicle vehicle : vehicles){
                    writer.write("-- "+vehicle+" - "+vehicle.getVehicleName()+" - "+ vehicle.getItemSize()+"m³");
                    writer.newLine();
                }
                writer.write("--------------------------------------\n");
                writer.newLine();
            }
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public Collection<Rential> getRentials(){
        return rentialStorage.findAllActive();
    }
    public Collection<Flat> getFlats(){
        return flatStorage.findAll();
    }
}
