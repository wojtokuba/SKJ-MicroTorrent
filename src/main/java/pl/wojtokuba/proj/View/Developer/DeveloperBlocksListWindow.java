package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.table.DefaultTableRenderer;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableCellBorderStyle;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperBlocksListViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesListViewModel;

public class DeveloperBlocksListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperBlocksListViewModel developerBlocksListViewModel;

    @Override
    public void render(){
        developerBlocksListViewModel = new DeveloperBlocksListViewModel(this);
        CustomTable<String> table = new CustomTable<>("ID", "Adres", "Nazwa osiedla", "Liczba mieszkań");
        for(Block block : developerBlocksListViewModel.getBlocks()){
            table.getTableModel().addRow(String.valueOf(block.getId()), block.getAddress(), block.getEstate().getName(), String.valueOf(0));
        }
        contentPanel.addComponent(table);
    }

}
