package own.pattern.composite.fileDemo;


public abstract class FileSystemNode {
    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    public abstract long countSizeOfFiles();

    public abstract int countNumOfFiles();

    public String getPath() {
        return path;
    }
}
