package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperBlocksListViewModel;

public class DeveloperBlocksListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperBlocksListViewModel developerBlocksListViewModel;

    @Override
    public void render() {
        developerBlocksListViewModel = new DeveloperBlocksListViewModel(this);
        contentPanel.addComponent(new Label("MOJE BLOKI")
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
        CustomTable<String> table = new CustomTable<>("ID", "Adres", "Nazwa osiedla", "Liczba mieszkań");
        for (Block block : developerBlocksListViewModel.getBlocks()) {
            table.getTableModel().addRow(String.valueOf(block.getId()), block.getAddress(), block.getEstate().getName(), String.valueOf(0));
        }
        contentPanel.addComponent(table);
    }

}
