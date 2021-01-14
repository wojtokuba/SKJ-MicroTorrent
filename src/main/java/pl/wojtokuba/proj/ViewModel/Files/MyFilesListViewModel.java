package pl.wojtokuba.proj.ViewModel.Files;
import pl.wojtokuba.proj.Exceptions.FilesystemException;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.Filesystem;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Files.MyFilesListWindow;

import java.util.ArrayList;
import java.util.Collection;

public class MyFilesListViewModel {
    MyFilesListWindow myFilesListWindow;

    public MyFilesListViewModel(MyFilesListWindow myFilesListWindow){
        this.myFilesListWindow = myFilesListWindow;
    }

    public Collection<File> getFiles() {
        try {
            return Filesystem.getAllSharableFiles();
        } catch (FilesystemException e){
            return new ArrayList<>();
        }
    }

    public boolean isFileShared(File file){
        SharedFilesStorage sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);
        return sharedFilesStorage.contains(file);
    }
}
