package own.jdk.coure.concurrent.demo3;

import junit.framework.TestCase;
import own.jdk.course.concurrent.demo3.SieveActor;
import own.jdk.course.concurrent.demo3.SieveSequential;

import static own.leetcode.other.SieveOfEratosthenes.sieveOfEratosthenes;

public class SieveTest extends TestCase {
    static final double expectedScalability = 1.6;

    private static int getNCores() {
        String ncoresStr = System.getenv("COURSERA_GRADER_NCORES");
        if (ncoresStr == null) {
            return Runtime.getRuntime().availableProcessors();
        } else {
            return Integer.parseInt(ncoresStr);
        }
    }

    private static long driver(final int limit, final int ref) {
        new SieveActor().countPrimes(limit); // warmup
        System.gc();
        new SieveActor().countPrimes(limit); // warmup
        System.gc();
        new SieveActor().countPrimes(limit); // warmup
        System.gc();

        final long parStart = System.currentTimeMillis();
        final int parCount = new SieveActor().countPrimes(limit);
        final long parElapsed = System.currentTimeMillis() - parStart;

        assertEquals("Mismatch in computed number of primes for limit " + limit, ref, parCount);
        return parElapsed;
    }

    public void testActorSieveOneHundredThousand() throws InterruptedException {
        final int limit = 100_000;
        final int ref = new SieveSequential().countPrimes(limit);

        long prev = -1;
        int cores = 2;
        while (cores <= getNCores()) {
            edu.rice.pcdp.runtime.Runtime.resizeWorkerThreads(cores);
            final long elapsed = driver(limit, ref);

            System.out.println(limit + " cost : " + elapsed + " compute in " + cores);

            if (prev > 0) {
                double scalability = (double) prev / (double) elapsed;
                assertTrue(String.format("Expected scalability of %fx going from %d cores to %d cores, but found %fx",
                        expectedScalability, cores / 2, cores, scalability), scalability >= expectedScalability);
            }

            cores *= 2;
            prev = elapsed;
        }
    }


    public void testActorSieveTwoHundredThousand() throws InterruptedException {
        final int limit = 200_000;
        final int ref = new SieveSequential().countPrimes(limit);

        long prev = -1;
        int cores = 2;
        while (cores <= getNCores()) {
            edu.rice.pcdp.runtime.Runtime.resizeWorkerThreads(cores);
            final long elapsed = driver(limit, ref);

            System.out.println(limit + " cost : " + elapsed + " compute in " + cores);

            if (prev > 0) {
                double scalability = (double) prev / (double) elapsed;
                assertTrue(String.format("Expected scalability of %fx going from %d cores to %d cores, but found %fx",
                        expectedScalability, cores / 2, cores, scalability), scalability >= expectedScalability);
            }

            cores *= 2;
            prev = elapsed;
        }
    }

    public void testSingleCorWithBetterArgorithm(){

        final long parStart = System.currentTimeMillis();
        sieveOfEratosthenes(200_000);
        final long parElapsed = System.currentTimeMillis() - parStart;
        System.out.println("200_000 cost : " + parElapsed + " compute in 1");
    }
}