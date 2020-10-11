package own.jdk.multiThreadInAction.chapter4.demo2;

import java.util.Map;

public interface StatProcessor {

    void process(String record);

    Map<Long, DelayItem> getResult();
}