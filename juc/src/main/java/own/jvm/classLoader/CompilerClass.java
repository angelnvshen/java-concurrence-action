package own.jvm.classLoader;

import sun.rmi.rmic.iiop.ClassPathLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class CompilerClass {

    static String filePath = "/Users/my/Desktop/own/jvm/classloader/Example2.java";

    public static void main3(String[] args) {

        //获取系统Java编译器
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        //获取Java文件管理器
        StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);

        //定义要编译的源文件
        File file = new File(filePath);
        //通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个 JavaFileObject，也被称为一个汇编单元
        Iterable<? extends JavaFileObject> compilationUnits = standardFileManager.getJavaFileObjects(file);

        //生成编译任务
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, compilationUnits);

        //执行编译任务
        task.call();
    }

    public static void main1(String[] args) throws
            ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {

        PathClassLoader classLoader = new PathClassLoader("/Users/my/Desktop/own/");
        Class<?> example = classLoader.loadClass("Example1");
        Object o = example.newInstance();
//        example.getMethod("main", String[].class).invoke(o, null);

        Object toString = example.getMethod("toString", null).invoke(o, null);
        System.out.println(toString);

        System.out.println(" ==== main =======");
        Example1 example11 = new Example1();
        example11.main(null);

        System.out.println(example11.getClass().getClassLoader());

        System.out.println(" ==== other ClassLoader load =======");
        ClassLoader systemClassLoader = ClassPathLoader.getSystemClassLoader();
        Class<?> example1 = systemClassLoader.loadClass("own.jvm.classLoader.Example1");

        String[] names = new String[]{};
        // example1.getMethod("main", String[].class).invoke(o,null); exception ...
        example1.getMethod("main", String[].class).invoke(o, new Object[]{names});

        // 静态方法：invoke obj参数可以为null。
        example1.getMethod("main", String[].class).invoke(null, new Object[]{names});
    }

    public static void main4(String[] args) throws Exception {
        PathClassLoader classLoader = new PathClassLoader("/Users/my/Desktop/");
        Class<?> example = classLoader.loadClass("own.jvm.classLoader.Example2");
        Object o = example.newInstance();
        Object toString = example.getMethod("toString", null).invoke(o, null);
        System.out.println(toString);
    }


    public static void main(String[] args) throws Exception {
        PathClassLoader pathClassLoader1 = new PathClassLoader("/Users/my/Desktop/");
        /*Class<?> example = pathClassLoader1.loadClass("own.jvm.classLoader.Example1");
        Object o = example.newInstance();
        example.getMethod("main", String[].class).invoke(null, new Object[]{new String[]{}});*/

        ExampleExtendImpl exampleExtend = new ExampleExtendImpl();
        System.out.println("exampleExtend classloader is " + exampleExtend.getClass().getClassLoader());

        Class<?> extendImpl1 = pathClassLoader1.loadClass("own.jvm.classLoader.ExampleExtendImpl2");
        Object exampleExtend1 = extendImpl1.newInstance();

        System.out.println(" =========== ");
        System.out.println("pathClassLoader1 's parent is " + pathClassLoader1.getParent());
        System.out.println("exampleExtend1 classloader is " + exampleExtend1.getClass().getClassLoader());
        // exampleExtend is pathClassLoader1's parent load
        extendImpl1.getMethod("print", ExampleExtend.class).invoke(exampleExtend1, exampleExtend);

        System.out.println(" =========== ");

        PathClassLoader pathClassLoader2 = new PathClassLoader("/Users/my/Desktop/");
        Class<?> extendImpl2 = pathClassLoader2.loadClass("own.jvm.classLoader.ExampleExtendImpl2");
        Object exampleExtend2 = extendImpl2.newInstance();
        System.out.println("pathClassLoader2 's parent is " + pathClassLoader2.getParent());
        System.out.println("exampleExtend2 classloader is " + exampleExtend2.getClass().getClassLoader());

        extendImpl2.getMethod("print", ExampleExtend.class).invoke(exampleExtend2, exampleExtend1);

    }
}
