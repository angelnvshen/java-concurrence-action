package own.jdk.unsafe;

import lombok.Data;

public class Atomic {

    public static void main(String[] args) {

        /*AtomicInteger integer = new AtomicInteger(10);
        System.out.println(integer);
        System.out.println(integer.get());
        assert !integer.compareAndSet(12, 10);
        integer.getAndSet(12);
        assert 12 == integer.get();*/

        /*AtomicLongArray longArray = new AtomicLongArray(10);
        longArray.set(1, 100L);
        assert 120L ==longArray.getAndAdd(1, 10);*/

        /*Weather sunning = new Weather("SUNNING");
        AtomicReference<Weather> weatherAtomicReference = new AtomicReference<>(sunning);
        System.out.println(weatherAtomicReference.get());
        System.out.println(weatherAtomicReference.compareAndSet(sunning, new Weather("RAINNING")));
        System.out.println(weatherAtomicReference.get());*/

        /*Weather sunning = new Weather("SUNNING");
        AtomicReferenceFieldUpdater<Weather, String> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Weather.class, String.class, "desc");
        referenceFieldUpdater.accumulateAndGet(sunning, "the weather is ", (prev, x)-> x + prev);
        System.out.println(sunning.getDesc());*/

    }

    @Data
    static class Weather {
        volatile String desc;

        public Weather(String desc) {
            this.desc = desc;
        }

        public Weather() {
        }
    }
}
