package pl.wojtokuba.proj.Utils;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.ExtendedTerminal;

import java.io.IOException;

public class MainViewManager {
    private DefaultTerminalFactory defaultTerminalFactory;
    private Screen screen;
    private WindowBasedTextGUI windowBasedTextGUI;

    public MainViewManager(){
        try {
            defaultTerminalFactory = new DefaultTerminalFactory();
            defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(200,60));
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
