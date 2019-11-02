package own.stu.redis.oneMaster.fakeDistribute.service.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class SplitRunnable implements Runnable {
    int byteSize;
    String partFileName;
    File originFile;
    int startPos;

    public SplitRunnable(int byteSize, int startPos, String partFileName, File originFile) {
        this.startPos = startPos;
        this.byteSize = byteSize;
        this.partFileName = partFileName;
        this.originFile = originFile;
    }

    public void run() {

        try (
                RandomAccessFile rFile = new RandomAccessFile(originFile, "r");
                OutputStream os = new FileOutputStream(partFileName)
        ) {
            byte[] b = new byte[byteSize];
            rFile.seek(startPos);// 移动指针到每“段”开头
            int s = rFile.read(b);
            os.write(b, 0, s);
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
