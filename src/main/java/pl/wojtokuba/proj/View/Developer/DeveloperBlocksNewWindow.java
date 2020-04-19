package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.ComboBoxFormGroup;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperBlocksNewViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesNewViewModel;

public class DeveloperBlocksNewWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperBlocksNewViewModel developerBlocksNewViewModel;
    ComboBoxFormGroup<Estate> assignedEstate;
    TextBoxFormGroup blockAddress;
    Label errorMessage;

    @Override
    public void render() {
        developerBlocksNewViewModel = new DeveloperBlocksNewViewModel(this);

        contentPanel.addComponent(new Label("NOWY BUDYNEK")
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

        contentPanel.addComponent(blockAddress = new TextBoxFormGroup("Adres budynku:",2,8));
        contentPanel.addComponent(
                assignedEstate = new ComboBoxFormGroup<Estate>("Przypisane osiedle:", 4, 6)
                .setValues(developerBlocksNewViewModel.getEstates())
        );

        contentPanel.addComponent( new Button("Utwórz", developerBlocksNewViewModel::create).setLayoutData(
                GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1
                )));
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
        contentPanel.addComponent(errorMessage = new Label("").addStyle(SGR.BOLD)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1)));
    }

    public Estate getEstate(){
        return this.assignedEstate.getSelected();
    }

    public String getAddress(){
        return this.blockAddress.getValue();
    }

    public void setErrorMessage(String text){
        this.errorMessage.setForegroundColor(new TextColor.RGB(255, 0, 0));
        this.errorMessage.setText(text);
    }

    public void success(){
        this.blockAddress.setValue("");
        this.errorMessage.setForegroundColor(new TextColor.RGB(0, 255, 0));
        this.errorMessage.setText("Budynek dodany pomyślnie!");
    }
}
