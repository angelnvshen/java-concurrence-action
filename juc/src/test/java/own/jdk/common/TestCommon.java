package own.jdk.common;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;

public class TestCommon {

    @After
    public void afterWait() throws InterruptedException {
        Thread.currentThread().join();
    }

    @Before
    public void before() {
        // slaf4j simple configure
        BasicConfigurator.configure();
    }
}
