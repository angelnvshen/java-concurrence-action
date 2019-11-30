package own.guava.base.io;

import com.google.common.base.Joiner;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {


    private String filePath = "/Users/my/IdeaProjects_own/core/juc/src/test/resources/";
    private String sourceFile = filePath + "quartz.properties";
    private String targetFile = filePath + "quartz.properties_bak";

    @Test
    public void test_fileCopy_withGuava() throws IOException {

        File source = new File(sourceFile);
        File target = new File(targetFile);

        Files.copy(source, target);
    }

    @Test
    public void test_fileCopy_nio() throws IOException {

        java.nio.file.Files.copy(
                Paths.get(sourceFile),
                Paths.get(targetFile),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    @Test
    public void test_fileMove() throws IOException {
        File targetFile = new File(this.targetFile);
        Files.move(new File(sourceFile), targetFile);
        assert targetFile.exists();

        Files.move(targetFile, new File(sourceFile));
    }

    @Test
    public void test_readLine() throws IOException {

        String expectedResult = "org.quartz.scheduler.instanceName = MyScheduler\n" +
                "org.quartz.threadPool.threadCount = 3\n" +
                "org.quartz.jobStore.class = org.quartz.simpl.RAMJobStore";

        List<String> strings = Files.readLines(new File(sourceFile), Charset.defaultCharset());

        Joiner.on("").join(strings).equals(expectedResult);
    }

    @Test
    public void test_readLine_withProcess() throws IOException {
        Files.asCharSource(new File(sourceFile), Charset.defaultCharset())
//                .forEachLine((s) -> System.out.println(s));
                .readLines(new LineProcessor<List<String>>() {
                    List<String> result = new ArrayList<>();
                    int index = 0;

                    @Override
                    public boolean processLine(String line) throws IOException {
                        result.add(index++ + " : " + line);
                        return true;
                    }

                    @Override
                    public List<String> getResult() {
                        return result;
                    }
                })
                .stream().forEach(System.out::println);
    }

    @Test
    public void test_fileWrite() throws IOException {
        File testFile = new File(filePath + "test.txt");
        testFile.deleteOnExit();

        String content = "hello world";

        Files.asByteSink(testFile).asCharSink(Charset.defaultCharset()).write(content);

        String result = Files.asByteSource(testFile).asCharSource(Charset.defaultCharset()).read();

        assert result.equals(content);
    }

    @Test
    public void test_fileAppend() throws IOException {
        File testFile = new File(filePath + "test.txt");
        testFile.deleteOnExit();

        String content = "hello world";

        Files.asCharSink(testFile, Charset.defaultCharset(), FileWriteMode.APPEND).write(content);

        content = "hello world again";
        Files.asCharSink(testFile, Charset.defaultCharset(), FileWriteMode.APPEND).write(content);

        String result = Files.asCharSource(testFile, Charset.defaultCharset()).read();
        System.out.println(result);
    }

    @Test
    public void test_fileTouch() throws IOException {
        File testFile = new File(filePath + "test.txt");
        testFile.deleteOnExit();

        Files.touch(testFile);
        assert testFile.exists();
    }

    @Test
    public void test_recurse() throws IOException {

        String filePath = "/Users/my/IdeaProjects_own/core/juc/src/";
        List<File> fileList = new ArrayList<>();
        recursionFile(new File(filePath), fileList);

        fileList.stream().forEach(System.out::println);
    }

    private void recursionFile(File root, List<File> fileList) {

        if (root == null || root.isHidden()) {
            return;
        }

//        fileList.add(root);

        if (root.isFile())
            fileList.add(root);
        if (root.isDirectory()) {
            for (File child : root.listFiles()) {
                recursionFile(child, fileList);
            }
        }
    }

    @Test
    public void test_() throws IOException {
        String filePath = "/Users/my/IdeaProjects_own/core/juc/src/";

        Files.fileTraverser().depthFirstPreOrder(new File(filePath))
                .forEach(System.out::println);
    }

    @After
    public void tearDown() {
        File target = new File(targetFile);
        if (target.exists()) {
            target.delete();
        }
    }
}
