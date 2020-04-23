package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.ComboBoxFormGroup;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Estate;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesNewViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperFlatsNewViewModel;

public class DeveloperFlatsNewWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperFlatsNewViewModel developerFlatsNewViewModel;
    TextBoxFormGroup localNo;
    TextBoxFormGroup surface;
    TextBoxFormGroup parkingSurface;
    ComboBoxFormGroup<Block> blocks;
    Label errorMessage;

    @Override
    public void render() {
        developerFlatsNewViewModel = new DeveloperFlatsNewViewModel(this);

        contentPanel.addComponent(new Label("NOWE MIESZKANIE")
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

        contentPanel.addComponent(localNo = new TextBoxFormGroup("Numer lokalu:",8,2));
        contentPanel.addComponent(surface = new TextBoxFormGroup("Powierzchnia (w m³ lub jako x,y,z w metrach):",8,2));
        contentPanel.addComponent(parkingSurface = new TextBoxFormGroup("Powierzchnia parkingu (w m³ lub jako x,y,z w metrach):",8,2));
        contentPanel.addComponent(
                blocks = new ComboBoxFormGroup<Block>("Budynek:",8,2)
                          .setValues(developerFlatsNewViewModel.getBlocks())
        );
        contentPanel.addComponent( new Button("Utwórz", developerFlatsNewViewModel::create).setLayoutData(
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

    public String getLocalNo(){
        return this.localNo.getValue();
    }

    public String getSurface(){
        return this.surface.getValue();
    }

    public String getParkingSurface(){
        return this.parkingSurface.getValue();
    }

    public Block getBlock(){
        return this.blocks.getSelected();
    }
    public void setErrorMessage(String text){
        this.errorMessage.setForegroundColor(new TextColor.RGB(255, 0, 0));
        this.errorMessage.setText(text);
    }

    public void success(){
        this.localNo.setValue("");
        this.surface.setValue("");
        this.errorMessage.setForegroundColor(new TextColor.RGB(0, 255, 0));
        this.errorMessage.setText("Mieszkanie dodane pomyślnie!");
    }
}
