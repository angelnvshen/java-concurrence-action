package own.pattern.composite.fileDemo;

import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemNode {

    private List<FileSystemNode> subNodes = new ArrayList<>();

    public Directory(String path) {
        super(path);
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public void removeSubNode(FileSystemNode fileOrDir) {

        int size = subNodes.size();
        int i = 0;

        for (; i < size; i++) {
            if (subNodes.get(i).path.equalsIgnoreCase(fileOrDir.getPath())) {
                break;
            }
        }

        if (i < size)
            subNodes.remove(i);
    }

    public long countSizeOfFiles() {

        long sizeofFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            sizeofFiles += fileOrDir.countSizeOfFiles();
        }
        return sizeofFiles;
    }

    public int countNumOfFiles() {

        int numOfFiles = 0;
        for (FileSystemNode node : subNodes) {
            numOfFiles += node.countNumOfFiles();
        }

        return numOfFiles;
    }
}
