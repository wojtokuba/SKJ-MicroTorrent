package pl.wojtokuba.proj.Utils;

// $Id$
/*
 * Copyright (C) 2010 sk89q <http://www.sk89q.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.HashMap;
import java.util.logging.Logger;

public class SimpleInjector {
    private static final Logger logger = Logger.getLogger(SimpleInjector.class.getCanonicalName());
    public static HashMap<String, Object> argClasses = new HashMap<String, Object>();
    private static boolean _isInitialized;

    public static void Setup(){
        if(_isInitialized)
            return;
        Object[] objects = new Object[]{
                new MainViewManager()
        };
        for (int i = 0; i < objects.length; ++i) {
            argClasses.put(objects[i].getClass().getName(), objects[i]);
        }
        _isInitialized = true;
    }

    public static Object resolveObject(Class<?> clazz) {
        try {
            logger.fine("Getting class: " + clazz + ": ");
            return argClasses.get(clazz.getName());
        } catch (IllegalArgumentException e){
            logger.severe("Error getting class " + clazz + ": ");
            e.printStackTrace();
            return null;
        }
    }
}
