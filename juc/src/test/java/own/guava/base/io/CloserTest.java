package own.guava.base.io;

import org.junit.Test;

public class CloserTest {

    @Test//(expected = RuntimeException.class)
    public void test() {
        try {
            System.out.println("try ... ");
            throw new RuntimeException("1");
        } catch (Exception e) {
            System.out.println("exception ... ");
        } finally {
            System.out.println("finally ... ");
        }
    }


    @Test(expected = RuntimeException.class)
    public void test2() {
        try {
            System.out.println("try ... ");
            throw new RuntimeException("1");
        } catch (Exception e) {
            System.out.println("exception ... ");
            throw new RuntimeException("2");
        } finally {
            System.out.println("finally ... ");
        }
    }

    // java.lang.RuntimeException: 2
    @Test
    public void test3() {
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            throw e;
        } finally {
            throw new RuntimeException("2");
        }
    }

    @Test
    public void test4() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            RuntimeException runtimeException = new RuntimeException("2");
            if (t != null)
                runtimeException.addSuppressed(t);
            throw runtimeException;
        }
    }

    /**
     *  closer.close()的方法类似下边的效果
     */
    @Test
    public void test5() {
        Throwable t = null;
        try {
            throw new RuntimeException("1");
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            try {
                throw new RuntimeException("2");
            } catch (Exception e) {
                if (t == null) {
                    throw e;
                }
                t.addSuppressed(e);
            }

        }
    }
}
