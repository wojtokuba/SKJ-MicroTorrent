package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesNewViewModel;

public class DeveloperEstatesNewWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperEstatesNewViewModel developerEstatesNewViewModel;
    TextBoxFormGroup estateName;
    TextBoxFormGroup estateAddress;
    Label errorMessage;

    @Override
    public void render() {
        developerEstatesNewViewModel = new DeveloperEstatesNewViewModel(this);

        contentPanel.addComponent(new Label("NOWE OSIEDLE")
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

        contentPanel.addComponent(estateName = new TextBoxFormGroup("Nazwa osiedla:",3,7));
        contentPanel.addComponent(estateAddress = new TextBoxFormGroup("Adres osiedla:",3,7));

        contentPanel.addComponent( new Button("Utwórz", developerEstatesNewViewModel::create).setLayoutData(
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

    public String getName(){
        return this.estateName.getValue();
    }

    public String getAddress(){
        return this.estateAddress.getValue();
    }
    public void setErrorMessage(String text){
        this.errorMessage.setForegroundColor(new TextColor.RGB(255, 0, 0));
        this.errorMessage.setText(text);
    }

    public void success(){
        this.estateName.setValue("");
        this.estateAddress.setValue("");
        this.errorMessage.setForegroundColor(new TextColor.RGB(0, 255, 0));
        this.errorMessage.setText("Osiedle dodane pomyślnie!");
    }
}
