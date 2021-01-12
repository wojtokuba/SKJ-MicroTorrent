package pl.wojtokuba.proj.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import org.w3c.dom.Text;
import pl.wojtokuba.proj.Utils.BaseAppConfigs;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.ViewModel.LoginViewModel;

import java.util.Arrays;

public class AppWorkModeWindow extends AbstractWindow {

    MainViewManager mainViewManager;
    Panel contentPanel;
    GridLayout gridLayout;
    LoginViewModel loginViewModel;
    ComboBox<String> appWorkMode = new ComboBox<>();
    ComboBox<String> appTransmissionProtocol = new ComboBox<>();
    Label errorMessage = new Label("");

    public AppWorkModeWindow() {
        super("Wybierz tryb działania aplikacji");
        loginViewModel = new LoginViewModel(this);
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new GridLayout(4));
        gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(10);
        gridLayout.setVerticalSpacing(1);
        appTransmissionProtocol.addItem(BaseAppConfigs.PROTO_TCP);
        appTransmissionProtocol.addItem(BaseAppConfigs.PROTO_UDP);
        contentPanel.addComponent(new Label("PJATK TORRent Soft by Jakub Wojtowicz <s20912>").addStyle(SGR.BOLD)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true,
                        false,
                        4,
                        1)));

        setHints(Arrays.asList(Hint.CENTERED));

        contentPanel.addComponent(new Label("Protokół transmisji:").setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.END,
                GridLayout.Alignment.END,
                false,
                false,
                3,
                1
        )));
        contentPanel.addComponent(
                appTransmissionProtocol.setPreferredSize(new TerminalSize(50, 1))
                        .setLayoutData(GridLayout.createLayoutData(
                                GridLayout.Alignment.BEGINNING,
                                GridLayout.Alignment.CENTER,
                                true,
                                false,
                                1,
                                1)));
        setComponent(contentPanel);
        contentPanel.addComponent(
                new Separator(Direction.HORIZONTAL)
                        .setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(4)));

        contentPanel.addComponent(errorMessage.addStyle(SGR.BLINK).setForegroundColor(new TextColor.RGB(255, 0, 0))
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.END,
                        true,
                        true,
                        4,
                        1)));

        contentPanel.addComponent(
                new Button("Uruchom", loginViewModel::LoginUser).setLayoutData(
                        GridLayout.createLayoutData(
                                GridLayout.Alignment.CENTER,
                                GridLayout.Alignment.CENTER,
                                true,
                                false,
                                4,
                                1
                        )));


        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }

    public String getAppTransmissionProtocol() {
        return this.appTransmissionProtocol.getText();
    }

    public void setErrorMessage(String message) {
        this.errorMessage.setText(message);
    }
}
