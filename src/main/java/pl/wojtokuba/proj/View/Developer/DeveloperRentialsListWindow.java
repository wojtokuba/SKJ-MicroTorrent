package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.Rential;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperFlatsListViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperRentialsListViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DeveloperRentialsListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperRentialsListViewModel developerRentialsListViewModel;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public void render() {
        developerRentialsListViewModel = new DeveloperRentialsListViewModel(this);
        contentPanel.addComponent(new Label("HISTORIA NAJMÓW")
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
        if(developerRentialsListViewModel.getAllRentials().size() > 0){
            CustomTable<String> table = new CustomTable<>("ID", "Mieszkanie", "Data rozpoczęcia", "Data zakończenia",  "Najemca", "Czy parking?", "Zapełnienie parkingu");
            for (Rential rential : developerRentialsListViewModel.getAllRentials()) {
                table.getTableModel().addRow(
                        String.valueOf(rential.getId()),
                        rential.getFlat().toString(),
                        dateFormat.format(rential.getRentAt()),
                        dateFormat.format(rential.getRentEnd()),
                        rential.getOwner().toString(),
                        rential.isParkingRent() ? "Tak" : "Nie",
                        "50%"
                );
            }
            contentPanel.addComponent(table);
        }
    }

}
