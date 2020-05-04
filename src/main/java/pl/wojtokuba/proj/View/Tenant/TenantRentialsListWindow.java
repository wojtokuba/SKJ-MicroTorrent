package pl.wojtokuba.proj.View.Tenant;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.table.TableModel;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.Storage.RentialStorage;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Developer.DeveloperMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperRentialsListViewModel;
import pl.wojtokuba.proj.ViewModel.Tenant.TenantRentialsListViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TenantRentialsListWindow extends TenantMainWindow implements WindowRenderable {

    TenantRentialsListViewModel tenantRentialsListViewModel;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private final RentialStorage rentialStorage = (RentialStorage)SimpleInjector.resolveObject(RentialStorage.class);

    @Override
    public void render() {
        tenantRentialsListViewModel = new TenantRentialsListViewModel(this);
        contentPanel.addComponent(new Label("MOJE NAJMY")
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
        if(tenantRentialsListViewModel.getAllRentials().size() > 0){
            CustomTable<String> table = new CustomTable<>("ID", "Mieszkanie", "Data rozpoczęcia", "Data zakończenia",  "Najemca", "Czy parking?", "Zapełnienie parkingu");
            for (Rential rential : tenantRentialsListViewModel.getAllRentials()) {
                table.getTableModel().addRow(
                        String.valueOf(rential.getId()),
                        rential.getFlat().toString(),
                        dateFormat.format(rential.getRentAt()),
                        dateFormat.format(rential.getRentEnd()),
                        rential.getOwner().toString(),
                        rential.isParkingRent() ? "Tak" : "Nie",
                        String.format("%.2f", ((rential.getFlat().getParkingPlace().getSurface() - rential.getFlat().getParkingPlace().getAvailableSurface()) / rential.getFlat().getParkingPlace().getSurface()) * 100) + "%"
                );
            }
            table.setSelectAction(() -> {
                RentialStorage rentialStorage = (RentialStorage) SimpleInjector.resolveObject(RentialStorage.class);
                Rential rential = rentialStorage.findOneById(Integer.parseInt(table.getTableModel().getRow(table.getSelectedRow()).get(0)));
                close();
                new TenantRentialDetailsWindow(rential);
            });
            contentPanel.addComponent(table);
        }
    }

}
