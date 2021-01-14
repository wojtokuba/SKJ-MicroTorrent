package pl.wojtokuba.proj.View.Files;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Separator;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Files.MyFilesListViewModel;

public class MyFilesListWindow extends ServerMainWindow implements WindowRenderable {

    MyFilesListViewModel myFilesListViewModel;

    @Override
    public void render() {
        myFilesListViewModel = new MyFilesListViewModel(this);
        contentPanel.addComponent(new Label("MOJE PLIKI")
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
        CustomTable<String> table = new CustomTable<>("ID", "Nazwa pliku", "Rozmiar (MB)", "Rozszerzenie", "Hash (8 znaków)", "Udostępniony");
        int i = 0;
        for (File file : myFilesListViewModel.getFiles()) {
            System.out.println(myFilesListViewModel.isFileShared(file));
            table.getTableModel()
                    .addRow(
                            String.valueOf(++i),
                            file.getName(),
                            String.valueOf(file.getSize()),
                            file.getExtension(),
                            file.getChecksum().substring(0, Math.min(file.getChecksum().length(), 8)),
                            myFilesListViewModel.isFileShared(file) ? "Tak" : "Nie"
                    );
        }

        contentPanel.addComponent(table);
    }

}
