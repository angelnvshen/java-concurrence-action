package own.pattern.composite.fileDemo;

public class File extends FileSystemNode {

    public File(String path) {
        super(path);
    }

    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) return 0;
        return file.length();
    }

    public int countNumOfFiles() {

        return 1;
    }
}
