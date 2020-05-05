package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Model.Vehicle;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.TimeLapseManager;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantMainViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantRentialDetailsViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantRentialsListViewModel;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

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

        CustomTable<String> tenants = new CustomTable<>("Id", "Imię", "Nazwisko", "PESEL");
        for (User user : rential.getCompanions().values()) {
            tenants.getTableModel().addRow(
                    String.valueOf(user.getId()),
                    user.getName(),
                    user.getLastName(),
                    user.getPesel()
            );
        }
        tenants.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.BEGINNING,
                true, false, 5, 1)
        );
        tenants.setSelectAction(() -> {
            MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
            MessageDialogButton dialog = new MessageDialogBuilder()
                    .setTitle("Eksmisja lokatora")
                    .setText("Czy na pewno chcesz eksmitować tego lokatora?")
                    .addButton(MessageDialogButton.OK)
                    .addButton(MessageDialogButton.Cancel)
                    .build()
                    .showDialog(mainViewManager.getWindowBasedTextGUI());
            if(dialog.toString().equals("OK")){
                User user = rential.getCompanions().get(Integer.parseInt(tenants.getTableModel().getRow(tenants.getSelectedRow()).get(0)));
                rential.removeCompanion(user);
                new MessageDialogBuilder()
                        .setTitle("Usuwanie lokatora")
                        .setText("Lokator został wyeksmitowany!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(mainViewManager.getWindowBasedTextGUI());
                close();
                new TenantRentialDetailsWindow(rential);
            }
        });
        contentPanel.addComponent(tenants);
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));

        Button prolongate = new Button("Przedłuż wynajem")
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 5, 1)
                );
        prolongate.addListener(button -> {
            String daysField = new TextInputDialogBuilder()
                    .setTitle("Przedłużanie wynajmu")
                    .setDescription("Podaj liczbę dni, o ile przedłużyć najem")
                    .setValidationPattern(Pattern.compile("[0-9]+"), "Musisz podać liczbę!")
                    .build()
                    .showDialog(mainViewManager.getWindowBasedTextGUI());
            if(!daysField.equals("")){
                rential.setRentEnd(TimeLapseManager.addDays(rential.getRentEnd().getTime(), Integer.parseInt(daysField)));
                new MessageDialogBuilder()
                        .setTitle("Przedłużanie wynajmu")
                        .setText("Najem został przedłużony o "+daysField+" dni!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(mainViewManager.getWindowBasedTextGUI());
            }
        });
        Button finish = new Button("Zakończ wynajem")
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 5, 1)
                );
        finish.addListener(button -> {
            TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);
            MessageDialogButton dialog = new MessageDialogBuilder()
                    .setTitle("Kończenie najmu")
                    .setText("Czy na pewno chcesz zakończyć wynajem?")
                    .addButton(MessageDialogButton.OK)
                    .addButton(MessageDialogButton.Cancel)
                    .build()
                    .showDialog(mainViewManager.getWindowBasedTextGUI());
            if(dialog.toString().equals("OK")) {
                rential.setRentEnd(new Timestamp(timeLapseManager.getAppDate().getTime()));
                rential.setArchived(true);
                rential.setPayCall(null);
                rential.getFlat().getParkingPlace().resetItems();
                new MessageDialogBuilder()
                        .setTitle("Kończenie najmu")
                        .setText("Najem został zakończony!")
                        .addButton(MessageDialogButton.OK)
                        .build()
                        .showDialog(mainViewManager.getWindowBasedTextGUI());
                close();
                new TenantMainWindow();
            }
        });
        contentPanel.addComponent(prolongate);
        contentPanel.addComponent(finish);
    }

}
