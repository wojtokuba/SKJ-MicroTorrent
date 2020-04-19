package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.menu.Menu;
import com.googlecode.lanterna.gui2.menu.MenuBar;
import com.googlecode.lanterna.gui2.menu.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class NavBarMenu extends Menu {
    public NavBarMenu(String menuName, HashMap<String, Runnable> items, MenuBar menuBar){
        super(menuName);

        for(Map.Entry<String, Runnable> item : items.entrySet()){
            add(new MenuItem(item.getKey(), item.getValue()));
        }
        setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.BEGINNING,
                true,
                true,
                1,
                1)
        );
        menuBar.add(this);
    }
}

