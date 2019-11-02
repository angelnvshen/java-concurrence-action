package own.stu.redis.oneMaster.fakeDistribute.service.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MergeRunnable implements Runnable {
    long startPos;
    String mergeFileName;
    File partFile;

    public MergeRunnable(long startPos, String mergeFileName, File partFile) {
        this.startPos = startPos;
        this.mergeFileName = mergeFileName;
        this.partFile = partFile;
    }

    public void run() {

        try (
                RandomAccessFile rFile = new RandomAccessFile(mergeFileName, "rw")
        ) {
            rFile.seek(startPos);
            FileInputStream fs = new FileInputStream(partFile);
            byte[] b = new byte[fs.available()];
            fs.read(b);
            fs.close();
            rFile.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}