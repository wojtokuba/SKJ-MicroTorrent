package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.ThemeDefinition;
import com.googlecode.lanterna.gui2.AbstractListBox;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.List;

public class AppNavbar extends ActionListBox{
    public AppNavbar(){
        setSelectedIndex(0);
    }
    private int itemId = 1;
    @Override
    public ActionListBox addItem(String label, Runnable action) {

        return super.addItem(itemId++ +". "+ label, action);
    }

    @Override
    public Result handleKeyStroke(KeyStroke keyStroke) {
        switch (keyStroke.getKeyType()){
            case F1:
                setSelectedIndex(0);
                return Result.HANDLED;
            case F2:
                setSelectedIndex(1);
                return Result.HANDLED;
            case F3:
                setSelectedIndex(2);
                return Result.HANDLED;
            case F4:
                setSelectedIndex(3);
                return Result.HANDLED;
            case F5:
                setSelectedIndex(4);
                return Result.HANDLED;
            case F6:
                setSelectedIndex(5);
                return Result.HANDLED;
            case F7:
                setSelectedIndex(6);
                return Result.HANDLED;
            default:
                return super.handleKeyStroke(keyStroke);
        }
    }
}

