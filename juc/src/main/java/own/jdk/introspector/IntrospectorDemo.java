package own.jdk.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class IntrospectorDemo {

    public static void main2(String[] args)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        UserInfo userInfo = new UserInfo();

        PropertyDescriptor descriptor = new PropertyDescriptor("name", UserInfo.class);

        descriptor.getWriteMethod().invoke(userInfo, "hello");

        Method readMethod = descriptor.getReadMethod();
        Object invoke = readMethod.invoke(userInfo, null);
        System.out.println(invoke);
    }

    public static void main(String[] args) throws IntrospectionException {

        UserInfo userInfo = new UserInfo();

        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        Arrays.asList(beanInfo.getPropertyDescriptors()).stream()
                .forEach(propertyDescriptor -> {
                    if(propertyDescriptor.getName().equals("name")){

                        try {
                            propertyDescriptor.getWriteMethod().invoke(userInfo, "hello");

                            Method readMethod = propertyDescriptor.getReadMethod();
                            Object invoke = readMethod.invoke(userInfo, null);
                            System.out.println(invoke);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    static class UserInfo {

        private int age;

        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
