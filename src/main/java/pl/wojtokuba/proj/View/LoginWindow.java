package pl.wojtokuba.proj.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.ViewModel.LoginViewModel;

import java.util.Arrays;

public class LoginWindow extends AbstractWindow {
    private MainViewManager mainViewManager;
    Panel contentPanel;
    GridLayout gridLayout;
    LoginViewModel loginViewModel;
    ComboBox<Object> userType = new ComboBox<>();
    TextBox userLogin = new TextBox();
    public LoginWindow(){
        super("Logowanie");
        loginViewModel = new LoginViewModel(this);
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new GridLayout(4));
        gridLayout = (GridLayout)contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(10);
        gridLayout.setVerticalSpacing(2);
        contentPanel.addComponent(new Label("PJATK Developer Soft by Jakub Wojtowicz <s20912>").addStyle(SGR.BOLD)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.CENTER,
                        GridLayout.Alignment.BEGINNING,
                        true,
                        false,
                        4,
                        1)));

        Label title = new Label("Wybierz jako kto chcesz sie zalogowac:");
        title.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.CENTER,
                GridLayout.Alignment.BEGINNING,
                true,
                false,
                4,
                1));
        contentPanel.addComponent(title);
        contentPanel.addComponent(
                userType.addItem("Deweloper")
                        .addItem("Najemca")
                        .setLayoutData(GridLayout.createLayoutData(
                                GridLayout.Alignment.CENTER,
                                GridLayout.Alignment.CENTER,
                                true,
                                false,
                                4,
                                1
                        )));
        setHints(Arrays.asList(Hint.CENTERED));

        contentPanel.addComponent(new Label("Nazwa Uzytkownika:").setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.END,
                GridLayout.Alignment.END,
                false,
                false,
                3,
                1
        )));
        contentPanel.addComponent(
                userLogin.setPreferredSize(new TerminalSize(50, 1))
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

        contentPanel.addComponent(
                new Button("Zaloguj", loginViewModel::LoginUser).setLayoutData(
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
}
