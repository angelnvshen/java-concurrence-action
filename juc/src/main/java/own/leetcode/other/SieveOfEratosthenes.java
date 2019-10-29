package own.leetcode.other;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SieveOfEratosthenes {

    public static List<Integer> sieveOfEratosthenesCached(int n) {
        if (n < 2) {
            return Lists.newArrayList();
        }

        Integer keyOfn = null;
        for (int i = 0; i < keys.size(); i++) {
            if (i == keys.size() - 1 && keys.get(i) < n) {
                break;
            }

            if (keys.get(i) == n) {
                keyOfn = i;
                break;
            }

            if (keys.get(i) < n && n < keys.get(i + 1)) {
                keyOfn = i;
                break;
            }
        }

        List<Integer> values = null;
        if (keyOfn != null) {
            values = getPrimeMap.get(keys.get(keyOfn));
        }


        if (values == null) {
            values = sieveOfEratosthenes(n);
            if ((keys != null && (keys.size() == 0) || values.size() > keys.size()))
                keys = new ArrayList<>(values);

            for (int i = values.size() - 1; i >= 0; i--) {
                Integer key = values.get(i);
                if (!getPrimeMap.containsValue(key)) {
                    getPrimeMap.put(key, new ArrayList<>(values.subList(0, i + 1)));
                } else {
                    break;
                }
            }
            Integer biggestKey = values.get(values.size() - 1);
            if (biggestKey < n) {
                List<Integer> biggestValues = getPrimeMap.get(biggestKey);
                getPrimeMap.put(biggestKey + 1, biggestValues);
                keys.add(n);
            }
        }
        return values;
    }

    // 排除法查找小于给定数值的所有质数
    public static List<Integer> sieveOfEratosthenes(int n) {

        boolean[] primes = new boolean[n + 1];
        for (int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }

        for (int p = 2; p * p <= n; p++) { // can be parallel
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (primes[i] == true)
                result.add(i);
        }
        return result;
    }

    // 30 -> 2 3 5 7 11 13 17 19 23 29
    public static void main(String[] args) {
        /*List<Integer> integers = sieveOfEratosthenes(30);
        System.out.println(integers);
        System.out.println(" ======== ");*/
        sieveOfEratosthenesCached(30);
        System.out.println(getPrimeMap);
        System.out.println(keys);

        System.out.println(" ======== ");
        /*for (int i = 0; i <= 50; i++) {
            System.out.println(i + " ： " + sieveOfEratosthenesCached(i));
        }*/

        int times = 100_000;

        long start2 = System.currentTimeMillis();
        for (int i = times; i >= 0; i--) {
            sieveOfEratosthenesCached(i);
            //System.out.println(i + " ： " + sieveOfEratosthenesCached(i));
        }
        System.out.println("cost : " + (System.currentTimeMillis() - start2));

    }

    private static Map<Integer, List<Integer>> getPrimeMap = new LinkedHashMap<>();
    private static List<Integer> keys = new ArrayList<>();
}
