package pl.wojtokuba.proj.Keystrokes;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import pl.wojtokuba.proj.Utils.MainViewManager;
import pl.wojtokuba.proj.Utils.SimpleInjector;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExitKeyStrokeListener implements TextGUI.Listener {
    private MainViewManager mainViewManager = (MainViewManager) SimpleInjector.resolveObject(MainViewManager.class);
    @Override
    public boolean onUnhandledKeyStroke(TextGUI textGUI, KeyStroke keyStroke) {
        if(keyStroke.getKeyType().equals(KeyType.Escape))
        {
          //example window close event
        }
        return false;
    }
}
