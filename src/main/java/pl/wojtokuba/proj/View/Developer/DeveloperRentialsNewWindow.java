package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.CheckBoxFormGroup;
import pl.wojtokuba.proj.Components.ComboBoxFormGroup;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.Block;
import pl.wojtokuba.proj.Model.Flat;
import pl.wojtokuba.proj.Model.User;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.TimeLapseManager;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperEstatesNewViewModel;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperRentialsNewViewModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DeveloperRentialsNewWindow extends DeveloperMainWindow implements WindowRenderable {

    DeveloperRentialsNewViewModel developerRentialsNewViewModel;
    ComboBoxFormGroup<User> username;
    CheckBoxFormGroup isParkingRent;
    TextBoxFormGroup rentialEndDate;
    ComboBoxFormGroup<Flat> selectedFlat;
    Label errorMessage;

    @Override
    public void render() {
        developerRentialsNewViewModel = new DeveloperRentialsNewViewModel(this);

        contentPanel.addComponent(new Label("NOWY WYNAJEM")
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

        contentPanel.addComponent(
                username = new ComboBoxFormGroup<User>("Najemca:",8,2)
                        .setValues(developerRentialsNewViewModel.getTenants())
        );
        contentPanel.addComponent(
                selectedFlat = new ComboBoxFormGroup<Flat>("Mieszkanie:",8,2)
                        .setValues(developerRentialsNewViewModel.getFreeFlats())
        );
        contentPanel.addComponent(isParkingRent = new CheckBoxFormGroup("Czy wynajmuje też parking:",3,7));
        contentPanel.addComponent(rentialEndDate = new TextBoxFormGroup("Długość wynajmu w dniach:",3,7));
        contentPanel.addComponent( new Button("Rozpocznij wynajem", developerRentialsNewViewModel::create).setLayoutData(
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

    public User getUsername(){
        return this.username.getSelected();
    }

    public String getEndDateValue(){
        return this.rentialEndDate.getValue();
    }

    public boolean isParkingRent(){
        return this.isParkingRent.getIsChecked();
    }

    public Flat getFlat(){
        return this.selectedFlat.getSelected();
    }

    public Timestamp getEndDate(){
        TimeLapseManager timeLapseManager = (TimeLapseManager) SimpleInjector.resolveObject(TimeLapseManager.class);
        Timestamp ts = new Timestamp(timeLapseManager.getAppDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DATE, Integer.parseInt(this.rentialEndDate.getValue()));
        ts.setTime(cal.getTime().getTime());
        return ts;
    }

    public void setErrorMessage(String text){
        this.errorMessage.setForegroundColor(new TextColor.RGB(255, 0, 0));
        this.errorMessage.setText(text);
    }

    public void success(){
        this.rentialEndDate.setValue("");
        this.isParkingRent.setValue(false);
        this.selectedFlat.setValues(developerRentialsNewViewModel.getFreeFlats());
        this.errorMessage.setForegroundColor(new TextColor.RGB(0, 255, 0));
        this.errorMessage.setText("Mieszkanie wynajęte pomyślnie!");
        close();
        new DeveloperRentialsNewWindow();
    }
}
