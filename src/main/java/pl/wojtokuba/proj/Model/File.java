package pl.wojtokuba.proj.Model;

public class File {
    String name;
    String extension;
    String checksum;
    String absolutePath;
    double size;
    java.io.File file; //in MB

    public File(String name, String extension, String checksum, String absolutePath, java.io.File file){
        this.name = name;
        this.extension = extension;
        this.checksum = checksum;
        this.absolutePath = absolutePath;
        this.file = file;
        this.size = file.length() / (1024*1024) ;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getChecksum() {
        return checksum;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }


    public java.io.File getFile() {
        return file;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", checksum='" + checksum + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", size=" + size +
                ", file=" + file +
                '}';
    }
}
