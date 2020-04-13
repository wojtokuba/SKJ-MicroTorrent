package pl.wojtokuba.proj.Utils;

import pl.wojtokuba.proj.Storage.UserStorage;

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
                new MainViewManager(),
                new UserStorage(),
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
