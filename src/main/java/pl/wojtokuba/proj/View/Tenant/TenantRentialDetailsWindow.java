package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.Vehicle;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantRentialDetailsViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantRentialsListViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TenantRentialDetailsWindow extends TenantMainWindow implements WindowRenderable {

    TenantRentialDetailsViewModel tenantRentialDetailsViewModel;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private RentialStorage rentialStorage = (RentialStorage)SimpleInjector.resolveObject(RentialStorage.class);
    Rential rential;

    public TenantRentialDetailsWindow(Rential rential){
        super(true);
        this.rential = rential;
        drawBasis();
        drawWindow();
        render();
        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);

    }

    @Override
    public void render() {
        tenantRentialDetailsViewModel = new TenantRentialDetailsViewModel(this);
        contentPanel.addComponent(new Label("SZCZEGÓŁY NAJMU: "+rential.toString())
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
        contentPanel.addComponent(new Label("PRZEDMIOTY NA PARKINGU:")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 5, 1)
                )
        );
        contentPanel.addComponent(new Label("LOKATORZY:")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 5, 1)
                )
        );
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
        CustomTable<String> table = new CustomTable<>("ID", "Typ pojazdu", "Nazwa pojazdu", "Rozmiar");
        for (Vehicle vehicle : rential.getFlat().getParkingPlace().getItems().values()) {
            table.getTableModel().addRow(
                    String.valueOf(vehicle.getId()),
                    vehicle.toString(),
                    vehicle.getVehicleName(),
                    vehicle.getItemSize() + " m³"
            );
        }
        table.setSelectAction(() -> {
            MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
            MessageDialogButton dialog = new MessageDialogBuilder()
                    .setTitle("Usuwanie pojazdu")
                    .setText("Czy na pewno chcesz usunąć ten pojazd?")
                    .addButton(MessageDialogButton.OK)
                    .addButton(MessageDialogButton.Cancel)
                    .build()
                    .showDialog(mainViewManager.getWindowBasedTextGUI());
            if(dialog.toString().equals("OK")){
                Vehicle vehicle = rential.getFlat().getParkingPlace().getItems().get(Integer.parseInt(table.getTableModel().getRow(table.getSelectedRow()).get(0)));
                rential.getFlat().getParkingPlace().deleteItem(vehicle);
                new MessageDialogBuilder()
                        .setTitle("Usuwanie pojazdu")
                        .setText("Pojazd usunięty!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(mainViewManager.getWindowBasedTextGUI());
                close();
                new TenantRentialDetailsWindow(rential);
            }
        });
        table.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.BEGINNING,
                true, false, 5, 1)
        );
        contentPanel.addComponent(table);

        CustomTable<String> tenants = new CustomTable<>("Imię", "Nazwisko", "PESEL");
        for (Vehicle vehicle : rential.getFlat().getParkingPlace().getItems().values()) {
            tenants.getTableModel().addRow(
                    vehicle.toString(),
                    vehicle.getVehicleName(),
                    String.valueOf(vehicle.getItemSize())
            );
        }
        tenants.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.BEGINNING,
                true, false, 5, 1)
        );
        contentPanel.addComponent(tenants);
    }

}
