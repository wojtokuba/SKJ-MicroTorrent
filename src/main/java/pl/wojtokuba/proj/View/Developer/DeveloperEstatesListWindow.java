package pl.wojtokuba.proj.View.Developer;

import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesListViewModel;

public class DeveloperEstatesListWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperEstatesListViewModel developerEstatesListViewModel;

    @Override
    public void render(){
        developerEstatesListViewModel = new DeveloperEstatesListViewModel(this);
        CustomTable<String> table = new CustomTable<>("ID", "Nazwa osiedla", "Adres osiedla", "Liczba bloków", "Lista mieszkań");
        for(Estate estate : developerEstatesListViewModel.getEstates()){
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
