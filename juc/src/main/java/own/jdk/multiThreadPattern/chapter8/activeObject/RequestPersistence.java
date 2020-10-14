package own.jdk.multiThreadPattern.chapter8.activeObject;

import java.io.Closeable;

public interface RequestPersistence extends Closeable {
    void store(MMSDeliverRequest request);
}