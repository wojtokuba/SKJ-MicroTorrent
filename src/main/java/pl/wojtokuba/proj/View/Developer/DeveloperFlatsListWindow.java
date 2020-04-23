package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperFlatsListViewModel;

public class DeveloperFlatsListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperFlatsListViewModel developerFlatsListViewModel;

    @Override
    public void render() {
        developerFlatsListViewModel = new DeveloperFlatsListViewModel(this);
        contentPanel.addComponent(new Label("MOJE MIESZKANIA")
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
        CustomTable<String> table = new CustomTable<>("ID", "Adres", "Osiedle", "Powierzchnia", "Powierzchnia parkingu");
        for (Flat flat : developerFlatsListViewModel.getFlats()) {
            table.getTableModel().addRow(
                    String.valueOf(flat.getId()),
                    flat.getBlock().getAddress() + "/" + flat.getLocalNo(),
                    flat.getBlock().getEstate().toString(),
                    flat.getSurface() +" m³",
                    flat.getParkingPlace().getSurface() +" m³"

            );
        }
        contentPanel.addComponent(table);
    }

}
