package pl.wojtokuba.proj.View.Sharing;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import pl.wojtokuba.proj.Commands.AppExit;
import pl.wojtokuba.proj.Commands.Logout;
import pl.wojtokuba.proj.Commands.Server.OpenMyFileList;
import pl.wojtokuba.proj.Components.*;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.View.BaseWindow;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Server.ServerMainViewModel;
import pl.wojtokuba.proj.ViewModel.Sharing.ShareFileViewModel;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ShareFileWindow extends ServerMainWindow implements WindowRenderable {

    ShareFileViewModel shareFileViewModel;
    List<CheckBoxFormGroup> checkboxList;
    ArrayList<File> files;
    Panel localPanel;

    @Override
    public void render() {
        if(localPanel == null){
            localPanel = new Panel(new GridLayout(10));
            contentPanel.addComponent(localPanel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(10)));
        }
        localPanel.removeAllComponents();
        shareFileViewModel = new ShareFileViewModel(this);
        localPanel.addComponent(new Label("Zaznacz pliki, które chcesz udostępniać lub odznacz aby zakończyć.")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
        localPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
        localPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));

        files = new ArrayList<>(shareFileViewModel.getFiles());
        checkboxList = new ArrayList<>(files.size());
        for (File file : files) {
            CheckBoxFormGroup item = new CheckBoxFormGroup(file.getName(),3,7);
            item.setValue(shareFileViewModel.isFileShared(file));
            checkboxList.add(item);
            localPanel.addComponent(item);
        }

        if(files.size() == 0){
            localPanel.addComponent(new Label("Nie znaleziono żadnych plików w katalogu: "+shareFileViewModel.getUploadDirPath())
                    .addStyle(SGR.BOLD)
                    .setForegroundColor(TextColor.ANSI.RED)
                    .setLayoutData(GridLayout.createLayoutData(
                            GridLayout.Alignment.BEGINNING,
                            GridLayout.Alignment.BEGINNING,
                            true, false, 10, 1)
                    )
            );
        }

        localPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));

        if(files.size() > 0) {
            localPanel.addComponent(
                new Button("Rozpocznij udostępnianie", shareFileViewModel::startSharing).setLayoutData(
                    GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1
                    )));
        } else {
            localPanel.addComponent(
                new Button("Odśwież", shareFileViewModel::refresh).setLayoutData(
                    GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.CENTER,
                        true,
                        false,
                        10,
                        1
                    )));
        }
    }

    public void clear(){
        contentPanel.removeComponent(localPanel);
    }
    public ArrayList<File> getFiles(){
        return this.files;
    }

    public List<CheckBoxFormGroup> getCheckboxList() {
        return checkboxList;
    }
}
