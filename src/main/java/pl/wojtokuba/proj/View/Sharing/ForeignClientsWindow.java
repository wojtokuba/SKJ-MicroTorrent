package pl.wojtokuba.proj.View.Sharing;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.CheckBoxFormGroup;
import pl.wojtokuba.proj.Components.CustomTable;
import pl.wojtokuba.proj.Components.TextBoxFormGroup;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.InnerHost;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.Net.Server.TCPServer;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Server.ServerMainWindow;
import pl.wojtokuba.proj.View.WindowRenderable;
import pl.wojtokuba.proj.ViewModel.Sharing.ForeignClientsViewModel;
import pl.wojtokuba.proj.ViewModel.Sharing.ShareFileViewModel;

import java.util.ArrayList;
import java.util.List;

public class ForeignClientsWindow extends ServerMainWindow implements WindowRenderable {

    ForeignClientsViewModel foreignClientsViewModel;
    TextBoxFormGroup newHostName;
    ArrayList<InnerHost> innerHosts;
    Panel localPanel;
    boolean isCheckingClients = false;
    boolean checkFailure = false;
    String errorMessage = null;
    
    @Override
    public void render() {
        foreignClientsViewModel = new ForeignClientsViewModel(this);
        if(localPanel == null){
            localPanel = new Panel(new GridLayout(10));
            contentPanel.addComponent(localPanel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(10)));
        }
        localPanel.removeAllComponents();

        if(isCheckingClients){
            localPanel.addComponent(new Label("Sprawdzam, czy podany host odpowiada...")
                    .addStyle(SGR.BOLD)
                    .setForegroundColor(TextColor.ANSI.BLUE)
                    .setLayoutData(GridLayout.createLayoutData(
                            GridLayout.Alignment.CENTER,
                            GridLayout.Alignment.BEGINNING,
                            true, false, 10, 1)
                    )
            );
            return;
        }
        if(checkFailure){
            localPanel.addComponent(new Label(errorMessage)
                    .addStyle(SGR.BOLD)
                    .setForegroundColor(TextColor.ANSI.RED)
                    .setLayoutData(GridLayout.createLayoutData(
                            GridLayout.Alignment.CENTER,
                            GridLayout.Alignment.BEGINNING,
                            true, false, 10, 1)
                    )
            );
            checkFailure = false;
            errorMessage = null;
        }

        localPanel.addComponent(new Label("Lista hostów z którymi będzie następowała wymiana")
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

        innerHosts = new ArrayList<>(foreignClientsViewModel.getInnerHosts());
        if(innerHosts.size() > 0){
            CustomTable<String> table = new CustomTable<>("Numer instancji", "Adres IP", "Port");
            for (InnerHost host : innerHosts) {
                table.getTableModel().addRow(
                        String.valueOf(host.getName()),
                        host.getHost(),
                        String.valueOf(host.getPort())
                );
            }
            localPanel.addComponent(table.setLayoutData(GridLayout.createLayoutData(
                    GridLayout.Alignment.CENTER,
                    GridLayout.Alignment.CENTER,
                    true, false, 10, 1)
            ));
        }

        if(innerHosts.size() == 0){
            localPanel.addComponent(new Label("Nie masz żadnych hostów z którymi możemy wymieniać pliki.")
                    .addStyle(SGR.BOLD)
                    .setForegroundColor(TextColor.ANSI.RED)
                    .setLayoutData(GridLayout.createLayoutData(
                            GridLayout.Alignment.CENTER,
                            GridLayout.Alignment.CENTER,
                            true, false, 10, 1)
                    )
            );
        }
        localPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(10)));
        localPanel.addComponent(new Label("Twój adres to: "+foreignClientsViewModel.tcpServer.getHostPort())
                .addStyle(SGR.BOLD)
                .setForegroundColor(TextColor.ANSI.BLUE)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true, false, 10, 1)
                )
        );
        localPanel.addComponent(new Label("Wprowadź nazwę hosta oraz port, aby dodać go do swojej sieci:")
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

        localPanel.addComponent(newHostName = new TextBoxFormGroup("Nazwa hosta oraz port np. localhost:3000 :",3,7));

//        localPanel.addComponent(
//                new Separator(Direction.HORIZONTAL)
//                        .setLayoutData(
//                                GridLayout.createHorizontallyFilledLayoutData(10)));
//
        localPanel.addComponent(
                new Button("Dodaj klienta", foreignClientsViewModel::createClient).setLayoutData(
                        GridLayout.createLayoutData(
                                GridLayout.Alignment.CENTER,
                                GridLayout.Alignment.CENTER,
                                true,
                                false,
                                10,
                                1
                        )));

    }

    public void setCheckingClients(boolean checking){
        this.isCheckingClients = checking;
    }

    public TextBoxFormGroup getNewHostName() {
        return newHostName;
    }

    public void setCheckingFailure(String errorMessage) {
        this.checkFailure = true;
        this.errorMessage = errorMessage;
        this.isCheckingClients = false;
        render();
    }
}
