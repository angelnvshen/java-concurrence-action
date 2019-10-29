package own.jdk.course.concurrent;

/**
 *  A single element in any of the list implementations.
 */
public final class Entry {

    public final Integer object;

    public Entry next;

    public Entry(Integer object) {
        this.object = object;
    }
}
