package pl.wojtokuba.proj.View.Developer;

import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.ViewModel.Developer.DeveloperMainViewModel;

import java.util.Arrays;

public class DeveloperMainWindow extends AbstractWindow {

    MainViewManager mainViewManager;
    Panel contentPanel;
    BorderLayout borderLayout;
    DeveloperMainViewModel developerMainViewModel;

    public DeveloperMainWindow() {
        super("Deweloper");
        developerMainViewModel = new DeveloperMainViewModel(this);
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new BorderLayout());
        borderLayout = (BorderLayout) contentPanel.getLayoutManager();
//        contentPanel.addComponent(new Menu)
        setHints(Arrays.asList(Hint.CENTERED));



        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }

}