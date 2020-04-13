package pl.wojtokuba.proj.Utils;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import pl.wojtokuba.proj.Keystrokes.ExitKeyStrokeListener;

import java.io.IOException;

public class MainViewManager {
    private DefaultTerminalFactory defaultTerminalFactory;
    private Screen screen;
    private WindowBasedTextGUI windowBasedTextGUI;

    public MainViewManager(){
        try {
            defaultTerminalFactory = new DefaultTerminalFactory();
            screen = defaultTerminalFactory.createScreen();
            windowBasedTextGUI = new MultiWindowTextGUI(screen);
            screen.startScreen();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public DefaultTerminalFactory getDefaultTerminalFactory() {
        return defaultTerminalFactory;
    }

    public Screen getScreen() {
        return screen;
    }

    public WindowBasedTextGUI getWindowBasedTextGUI() {
        return windowBasedTextGUI;
    }
}
