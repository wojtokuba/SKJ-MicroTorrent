package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Utils.Filesystem;

import java.util.*;

public class SharedFilesStorage {
    private final HashMap<String, File> files = new HashMap<>();

    public SharedFilesStorage push(File file) {
        this.files.put(file.getChecksum(), file);
        return this;
    }

    public boolean delete(File file){
        this.files.remove(file.getChecksum());
        return true;
    }

    public Collection<File> findByName(String name){
        Collection<File> result = new ArrayList<>();
        for(File file : this.files.values()){
            if(file.getName().equals(name)){
                result.add(file);
            }
        }
        return result;
    }

    public File findOneByName(String name){
        Collection<File> result = new ArrayList<>();
        for(File file : this.files.values()){
            if(file.getName().equals(name)){
               return file;
            }
        }
        return null;
    }

    public boolean contains(File srcFile){
        for(File file : this.files.values()){
            if(file.getChecksum().equals(srcFile.getChecksum())){
                return true;
            }
        }
        return false;
    }

}
