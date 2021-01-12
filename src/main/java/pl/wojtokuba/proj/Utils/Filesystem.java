package pl.wojtokuba.proj.Utils;

import org.apache.commons.io.FilenameUtils;
import pl.wojtokuba.proj.Exceptions.FilesystemException;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Storage.ConfigStorage;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

public class Filesystem {
    static ConfigStorage configStorage;

    private static java.io.File getUploadsDir(){
        configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        return new java.io.File(configStorage.get(ConfigStorage.SettingValues.FILES_LOCATION));
    }

    public static Collection<File> getAllSharableFiles() throws FilesystemException {
        configStorage = (ConfigStorage) SimpleInjector.resolveObject(ConfigStorage.class);
        ArrayList<File> fileList = new ArrayList<File>();
        java.io.File directory = getUploadsDir();
        try {
            if(!directory.exists()){
                directory.mkdir();
            }
        } catch (Exception e){
            throw new FilesystemException();
        }
        for(java.io.File file : directory.listFiles()){
            fileList.add(new File(
                    file.getName(),
                    FilenameUtils.getExtension(file.getName()),
                    getFileHash(file),
                    file.getAbsolutePath(),
                    file
            ));
        }
        return fileList;
    }

    public static String getUsersHomeDir() {
        String users_home = System.getProperty("user.home");
        return users_home.replace("\\", "/"); // to support all platforms.
    }

    public static String getFileHash(java.io.File file){
        try {
            byte[] b = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            byte[] hash = MessageDigest.getInstance("MD5").digest(b);
            return String.format("%032X", new BigInteger(1, hash));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
