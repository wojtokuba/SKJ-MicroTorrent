package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.ComboBoxFormGroup;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.*;
import pl.wojtokuba.proj.View.Developer.DeveloperMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperFlatsNewViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantVehicleAddViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TenantVehicleAddWindow extends TenantMainWindow implements WindowRenderable {

    TenantVehicleAddViewModel tenantVehicleAddViewModel;
    ComboBoxFormGroup<Vehicle> vehicleType;
    ComboBoxFormGroup<Flat> flatList;
    TextBoxFormGroup enginePower;
    TextBoxFormGroup maxWaterSpeed;
    TextBoxFormGroup fuelTankSize;
    TextBoxFormGroup fuelUsage;
    TextBoxFormGroup maxSpeed;
    TextBoxFormGroup vehicleName;
    TextBoxFormGroup vehicleSize;

    Label errorMessage;

    @Override
    public void render() {
        tenantVehicleAddViewModel = new TenantVehicleAddViewModel(this);

        contentPanel.addComponent(new Label("NOWY POJAZD")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));

        contentPanel.addComponent(
            vehicleType = new ComboBoxFormGroup<Vehicle>("Typ pojazdu:",8,2)
                .setValues(Arrays.asList(
                    new CityCar(),
                    new Amfibia(),
                    new TerrainCar(),
                    new Motocycle(),
                    new Boat()
                    )
                )
        );

        vehicleType.getComboBox().addListener((i, i1) -> {
            contentPanel.removeComponent(vehicleType);
            contentPanel.addComponent(5, vehicleName = new TextBoxFormGroup("Nazwa pojazdu:",2,8));
            contentPanel.addComponent(6, vehicleSize = new TextBoxFormGroup("Rozmiar (w m³ lub jako x,y,z w metrach):",2,8));
            if(vehicleType.getComboBox().getItem(i) instanceof CityCar){
                contentPanel.addComponent(7,fuelUsage = new TextBoxFormGroup("Zużycie paliwa / 100km:",2,8));
            }
            else if(vehicleType.getComboBox().getItem(i) instanceof Amfibia){
                contentPanel.addComponent(7, maxWaterSpeed = new TextBoxFormGroup("Maksymalna prędkość wodna:",2,8));
            }
            else if(vehicleType.getComboBox().getItem(i) instanceof TerrainCar){
                contentPanel.addComponent(7, enginePower = new TextBoxFormGroup("Maksymalna moc silnika:",2,8));
            }
            else if(vehicleType.getComboBox().getItem(i) instanceof Motocycle){
                contentPanel.addComponent(7, maxSpeed = new TextBoxFormGroup("Maksymalna prędkość:",2,8));
            }
            else if(vehicleType.getComboBox().getItem(i) instanceof Boat){
                contentPanel.addComponent(7, fuelTankSize = new TextBoxFormGroup("Pojemność zbiornika:",2,8));
            }
            contentPanel.addComponent(8,
                    flatList = new ComboBoxFormGroup<Flat>("Mieszkania z dostępnym garażem:", 4, 6)
                            .setValues(tenantVehicleAddViewModel.getMyFlats())
            );

            contentPanel.addComponent( new Button("Dodaj", tenantVehicleAddViewModel::create).setLayoutData(
                GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1
                )));
            }
        );

        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
        contentPanel.addComponent(errorMessage = new Label("").addStyle(SGR.BOLD)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1)));
    }

    public void setErrorMessage(String text){
        this.errorMessage.setForegroundColor(new TextColor.RGB(255, 0, 0));
        this.errorMessage.setText(text);
    }

    public void success(){
        this.errorMessage.setForegroundColor(new TextColor.RGB(0, 255, 0));
        this.errorMessage.setText("Mieszkanie dodane pomyślnie!");
    }
}
