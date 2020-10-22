package own.stu.springcloud.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CommandThatFastFails extends HystrixCommand<String> {

    private final boolean thrownException;

    public CommandThatFastFails(boolean thrownException) {
        super(HystrixCommandGroupKey.Factory.asKey("exampleGroup"));
        this.thrownException = thrownException;
    }

    @Override
    protected String run() throws Exception {
        if (thrownException) {
            throw new RuntimeException("failure from CommandThatFailsFast");
        } else {
            return "success";
        }
    }

    @Override
    protected String getFallback() {
        return "fallback ---- ";
    }

    public static class CommandThatFastFailsTest {
        @Test
        public void testSuccess() {
            assertEquals("success", new CommandThatFastFails(false));
            System.out.println(" ------ ");
        }

        @Test
        public void testFails(){

            try {
                new CommandThatFastFails(true).execute();
                fail("we should have thrown an exception");
            } catch (HystrixRuntimeException e) {
                assertEquals("failure from CommandThatFailsFast", e.getCause().getMessage());
                e.printStackTrace();
            }
        }

        @Test
        public void testFailsSlient(){
            try {
                assertEquals("fallback ---- ", new CommandThatFastFails(true).execute());
            }catch (HystrixRuntimeException e){
                fail("we should not get an exception as we fail silently with a fallback");
            }
        }
    }
}