//package own.jdk.executorService.forkAndJoin;
//
//import com.sun.tools.javac.util.Assert;
//
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveTask;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class ForkAndJoinSimpleDemo {
//
//    static ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Random random = new Random(10);
//        List<Integer> list = Stream.generate(() -> random.nextInt(100_000))
//                .limit(10_000_000)
//                .collect(Collectors.toList());
//
//        long star = System.currentTimeMillis();
//        ForkAndJoinRequest forkAndJoinRequest = new ForkAndJoinRequest(list);
//        forkJoinPool.submit(forkAndJoinRequest);
//
//        System.out.println(forkAndJoinRequest.get() + " -> cost : " + (System.currentTimeMillis() - star));
//
//        forkJoinPool.shutdown();
//        System.out.println(" ============== ");
//
//        long star1 = System.currentTimeMillis();
//        Long sum = list.stream().mapToLong(n -> n).sum();
//        System.out.println(sum + " -> cost : " + (System.currentTimeMillis() - star1));
//
//        System.out.println(" ============== ");
//        long star2 = System.currentTimeMillis();
//        long result = 0;
//        for (int i = 0; i < list.size(); i++) {
//            result += list.get(i);
//        }
//        System.out.println(result + " -> cost : " + (System.currentTimeMillis() - star2));
//    }
//
//    static class ForkAndJoinRequest extends RecursiveTask<Long> {
//
//        private int start;
//        private int end;
//        private List<Integer> data;
//
//        public ForkAndJoinRequest(List<Integer> data) {
//            Assert.checkNonNull(data);
//            this.start = 0;
//            this.end = data.size();
//            this.data = data;
//        }
//
//        public ForkAndJoinRequest(int start, int end, List<Integer> data) {
//            Assert.checkNonNull(data);
//            this.start = start;
//            this.end = end;
//            this.data = data;
//        }
//
//        @Override
//        protected Long compute() {
//            if (end - start >= 25) {
//                Long result = 0L;
//                for (int i = start; i < end; i++) {
//                    result += data.get(i);
//                }
//                return result;
//            } else {
//                int mid = (start + end) / 2;
//                ForkAndJoinRequest joinRequest = new ForkAndJoinRequest(start, mid, data);
//                joinRequest.join();
//                ForkAndJoinRequest joinRequest2 = new ForkAndJoinRequest(mid + 1, end, data);
//                joinRequest2.join();
//                return joinRequest.join() + joinRequest2.join();
//            }
//        }
//    }
//}
