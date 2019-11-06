package own.jvm.classLoader;

public class ExampleExtendImpl implements ExampleExtend {

    @Override
    public void print(ExampleExtend exampleExtend) {
        System.out.println(exampleExtend.getClass().getClassLoader());
    }
}
