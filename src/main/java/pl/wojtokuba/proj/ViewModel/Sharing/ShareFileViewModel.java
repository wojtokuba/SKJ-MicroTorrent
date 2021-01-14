package pl.wojtokuba.proj.ViewModel.Sharing;
import pl.wojtokuba.proj.Components.CheckBoxFormGroup;
import pl.wojtokuba.proj.Exceptions.FilesystemException;
import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Storage.SharedFilesStorage;
import pl.wojtokuba.proj.Utils.Filesystem;
import pl.wojtokuba.proj.Utils.SimpleInjector;
import pl.wojtokuba.proj.View.Sharing.ShareFileWindow;

import java.util.ArrayList;
import java.util.Collection;

public class ShareFileViewModel {
    ShareFileWindow shareFileWindow;
    SharedFilesStorage sharedFilesStorage;

    public ShareFileViewModel(ShareFileWindow shareFileWindow){
        sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);;
        this.shareFileWindow = shareFileWindow;
    }

    public Collection<File> getFiles() {
        try {
            return Filesystem.getAllSharableFiles();
        } catch (FilesystemException e){
            return new ArrayList<>();
        }
    }

    public String getUploadDirPath(){
        return Filesystem.getUserDirectoryPath();
    }

    public void startSharing(){
        int i = 0;
        for(CheckBoxFormGroup checkbox : shareFileWindow.getCheckboxList()){
            if(checkbox.getIsChecked()){
                sharedFilesStorage.push(shareFileWindow.getFiles().get(i));
            } else {
                sharedFilesStorage.delete(shareFileWindow.getFiles().get(i));
            }
            i++;
        }
        shareFileWindow.clear();
    }

    public boolean isFileShared(File file){
        SharedFilesStorage sharedFilesStorage = (SharedFilesStorage) SimpleInjector.resolveObject(SharedFilesStorage.class);
        return sharedFilesStorage.contains(file);
    }

    public void refresh(){
        shareFileWindow.render();
    }
}
