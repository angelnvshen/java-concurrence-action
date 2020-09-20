package own.pattern.factory.demo.dependencyInjection;

public interface ApplicationContext {
    Object getBean(String beanId);
}
