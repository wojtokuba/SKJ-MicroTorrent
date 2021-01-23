package pl.wojtokuba.proj.View.Sharing;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.InnerHost;
import pl.wojtokuba.proj.Model.VirtualRemoteFile;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Sharing.DownloadP2MPViewModel;
import pl.wojtokuba.proj.ViewModel.Sharing.ForeignClientsViewModel;

import java.util.ArrayList;
import java.util.Collection;

public class DownloadP2MPWindow extends ServerMainWindow implements WindowRenderable {

    DownloadP2MPViewModel downloadP2MPViewModel;
    TextBoxFormGroup downloadFilename;
    Panel pagePanel;
    String errorMessage = null;

    @Override
    public void render() {
        downloadP2MPViewModel = new DownloadP2MPViewModel(this);
        pagePanel = new Panel(new GridLayout(10)).setLayoutData(GridLayout.createHorizontallyFilledLayoutData(10));
        contentPanel.addComponent(pagePanel);
        renderDefaultView();
    }

    public String getFilename(){
        return this.downloadFilename.getValue();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void resetView(){
        pagePanel.removeAllComponents();
    }

    private void renderDefaultView(){
        buildHeader();

        pagePanel.addComponent(downloadFilename = new TextBoxFormGroup("Podaj nazwę pliku do pobrania: ",3,7));

        if(errorMessage != null){
            pagePanel.addComponent(new Label(errorMessage)
                    .addStyle(SGR.BOLD)
                    .setForegroundColor(TextColor.ANSI.RED)
                    .setLayoutData(GridLayout.createLayoutData(
                            GridLayout.Alignment.CENTER,
                            GridLayout.Alignment.CENTER,
                            true, false, 10, 1)
                    )
            );
            errorMessage = null;
        }

        pagePanel.addComponent(
                new Button("Pobierz", downloadP2MPViewModel::downloadFile).setLayoutData(
                        GridLayout.createLayoutData(
                                GridLayout.Alignment.CENTER,
                                GridLayout.Alignment.CENTER,
                                true,
                                false,
                                10,
                                1
                        )));
    }

    public void renderStartCheckingHosts(){
        pagePanel.addComponent(new Label("Sprawdzamy dostępność pliku na hostach w sieci...")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
    }

    public void renderResultCheckingHosts(Collection<VirtualRemoteFile> fileOnHosts){
        buildHeader();

        CustomTable<String> table = new CustomTable<>("ID", "Nazwa hosta", "Skrót hasha pliku", "Liczba chunków do pobrania");
        int i = 0;
        for (VirtualRemoteFile file : fileOnHosts) {
            table.getTableModel()
                    .addRow(
                            String.valueOf(++i),
                            file.getHost().getName(),
                            file.getFileHash(),
                            String.valueOf(file.getChunkAmount())
                    );
        }
        pagePanel.addComponent(table);
        pagePanel.addComponent(new Label("Pobieranie pliku zaraz się rozpocznie...")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
    }

    private void buildHeader() {
        resetView();
        pagePanel.addComponent(new Label("Pobieranie pliku P2MP")
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
        pagePanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
    }
}
