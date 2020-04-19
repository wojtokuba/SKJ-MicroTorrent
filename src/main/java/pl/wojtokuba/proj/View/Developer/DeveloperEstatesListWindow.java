package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesListViewModel;

public class DeveloperEstatesListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperEstatesListViewModel developerEstatesListViewModel;

    @Override
    public void render() {
        developerEstatesListViewModel = new DeveloperEstatesListViewModel(this);
        contentPanel.addComponent(new Label("MOJE OSIEDLA")
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
        CustomTable<String> table = new CustomTable<>("ID", "Nazwa osiedla", "Adres osiedla", "Liczba bloków", "Lista mieszkań");
        for (Estate estate : developerEstatesListViewModel.getEstates()) {
            table.getTableModel()
                    .addRow(
                            String.valueOf(estate.getId()),
                            estate.getName(),
                            estate.getAddress(),
                            String.valueOf(estate.getBlocks().size()),
                            String.valueOf(developerEstatesListViewModel.getFlatsByEstate(estate).size())
                    );
        }

        contentPanel.addComponent(table);
    }

}
