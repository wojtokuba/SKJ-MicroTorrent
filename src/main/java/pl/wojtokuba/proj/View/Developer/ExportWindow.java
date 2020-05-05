package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.View.Tenant.TenantMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperRentialsListViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.ExportViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExportWindow extends DeveloperMainWindow implements WindowRenderable {

    ExportViewModel exportViewModel;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public void render() {
        exportViewModel = new ExportViewModel(this);
        String reportPath = exportViewModel.createReport();
        new MessageDialogBuilder()
                .setTitle("Sukces!")
                .setText("Raport został zapisany w ścieżce: "+reportPath+"!")
                .addButton(MessageDialogButton.OK)
                .build()
                .showDialog(mainViewManager.getWindowBasedTextGUI());
        close();
        new DeveloperMainWindow();
    }

}
