package own.jdk.executorService.forkAndJoin;

import com.sun.tools.javac.util.Assert;

import java.util.List;
import java.util.concurrent.CountedCompleter;

public class CountedCompleterTest {

    static class CountedCompleterRequest extends CountedCompleter<Long> {

        private int start;
        private int end;
        private List<Integer> data;

        public CountedCompleterRequest(List<Integer> data) {
            Assert.checkNonNull(data);
            this.start = 0;
            this.end = data.size();
            this.data = data;
        }

        public CountedCompleterRequest(int start, int end, List<Integer> data) {
            Assert.checkNonNull(data);
            this.start = start;
            this.end = end;
            this.data = data;
        }

        @Override
        public void compute() {

        }

        protected Long compeute() {
            if (end - start >= 25) {
                Long result = 0L;
                for (int i = start; i < end; i++) {
                    result += data.get(i);
                }
                return result;
            } else {
                int mid = (start + end) / 2;
                CountedCompleterRequest joinRequest = new CountedCompleterRequest(start, mid, data);
                joinRequest.join();
                CountedCompleterRequest joinRequest2 = new CountedCompleterRequest(mid + 1, end, data);
                joinRequest2.join();
                return joinRequest.join() + joinRequest2.join();
            }
        }
    }
}
