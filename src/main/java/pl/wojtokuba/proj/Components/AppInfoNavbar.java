package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TerminalTextUtils;
import com.googlecode.lanterna.graphics.ThemeDefinition;
import com.googlecode.lanterna.gui2.*;
import pl.wojtokuba.proj.Storage.SessionStorage;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.Utils.SystemRoles;

public class AppInfoNavbar extends Label {
    private static final SessionStorage sessionStorage = (SessionStorage) SimpleInjector.resolveObject(SessionStorage.class);
    public AppInfoNavbar(){
        super("Zalogowany jako: "+ sessionStorage.getLoggedInUser().getUsername() + " | " + (sessionStorage.getLoggedInUser().getPermissions() == SystemRoles.DEVELOPER ? "Deweloper" : "Lokator"));
        setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
                GridLayout.Alignment.FILL,
                true,
                false,
                10,
                1));
        addStyle(SGR.BOLD);
    }
}
