package pl.wojtokuba.proj.View;

import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Components.AppInfoNavbar;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.util.Arrays;

public abstract class BaseWindow extends AbstractWindow implements WindowRenderable, WindowDrawable {

    public MainViewManager mainViewManager;
    public Panel contentPanel;
    public GridLayout borderLayout;

    public BaseWindow(String windowTitle) {
        super(windowTitle);
        drawBasis();
        drawWindow();
        render();
        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }
    public BaseWindow(){
        super();
        drawBasis();
        drawWindow();
        render();
        mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
    }
    public BaseWindow(String windowTitle, boolean overrideRendering){
        super(windowTitle);
        if(!overrideRendering){
            drawBasis();
            drawWindow();
            render();
            mainViewManager.getWindowBasedTextGUI().addWindowAndWait(this);
        }
    }

    protected void drawBasis(){
        setHints(Arrays.asList(Hint.NO_DECORATIONS, Hint.FULL_SCREEN));
        mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
        contentPanel = new Panel(new GridLayout(10));
        borderLayout = (GridLayout) contentPanel.getLayoutManager();
        setComponent(contentPanel);
        contentPanel.addComponent(new AppInfoNavbar());
        contentPanel.addComponent(new Separator(Direction.HORIZONTAL)
                .setLayoutData(GridLayout.createLayoutData(
                        GridLayout.Alignment.FILL,
                        GridLayout.Alignment.FILL,
                        true,
                        false,
                        10,
                        1)
                ));
    }
    public abstract void render();
    public abstract void drawWindow();
}
