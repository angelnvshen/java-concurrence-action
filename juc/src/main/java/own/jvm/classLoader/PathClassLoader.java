package own.jvm.classLoader;

import java.io.*;

public class PathClassLoader extends ClassLoader {

    public String drive;

    public PathClassLoader(String drive) {
        super();
        this.drive = drive;
    }

    public static final String fileType = ".class";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {

        byte[] bytes = null;
        try {
            name = name.replace(".", File.separator);
            FileInputStream fileInputStream = new FileInputStream(drive + name + fileType);
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                arrayOutputStream.write(ch);
            }
            bytes = arrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }
}
