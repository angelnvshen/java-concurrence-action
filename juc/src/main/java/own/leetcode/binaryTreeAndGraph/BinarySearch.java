package own.leetcode.binaryTreeAndGraph;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {

    // 递归
    public static boolean binarySearch(int[] sortArray, int start, int end, int target) {
        boolean in = false;

        if (start > end) {
            return in;
        }

        int mid = (start + end) / 2;
        if (sortArray[mid] == target) {
            in = true;
            return in;
        }
        if (sortArray[mid] > target) {
            return binarySearch(sortArray, start, mid - 1, target);
        }

        return binarySearch(sortArray, mid + 1, end, target);
    }

    // 循环
    public static boolean binarySearch(int[] sortArray, int target) {

        int start = 0;
        int end = sortArray.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (sortArray[mid] == target) {
                return true;
            }
            if (sortArray[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    public static int searchInsertPosition(List<Integer> list, int target) {

        int index = -1;
        int start = 0;
        int end = list.size() - 1;
        while (index == -1) {
            int mid = (start + end) / 2;
            if (list.get(mid) == target) {
                index = mid;
            }
            if (target < list.get(mid)) {
                if (mid == 0 || target > list.get(mid - 1)) {
                    index = mid;
                }
                end = mid - 1;
            }
            if (target > list.get(mid)) {
                if (mid == list.size() - 1 || target < list.get(mid + 1)) {
                    index = mid + 1;
                }
                start = mid + 1;
            }
        }

        return index;
    }

    public static int left_bound(List<Integer> list, int target) {

        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) == target) {
                if (mid == 0 || list.get(mid - 1) < target) {
                    return mid;
                }
                end = mid - 1;
            } else if (target > list.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static int right_bound(List<Integer> list, int target) {

        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) == target) {
                if (mid == list.size() - 1 || list.get(mid + 1) > target) {
                    return mid;
                }
                start = mid + 1;
            } else if (target > list.get(mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    /**
     * rotatedArray is sortArrayRotated
     */
    public static int findInRotateArray(int[] rotatedArray, int target) {

        int start = 0;
        int end = rotatedArray.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (rotatedArray[mid] == target) {
                return mid;
            }
            if (target < rotatedArray[mid]) {
                if (rotatedArray[start] < rotatedArray[mid]) {
                    if (rotatedArray[start] <= target) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else if (rotatedArray[start] > rotatedArray[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (target > rotatedArray[mid]) {
                if (rotatedArray[start] < rotatedArray[mid]) {
                    start = mid + 1;
                } else if (rotatedArray[start] > rotatedArray[mid]) {
                    if (target >= rotatedArray[start]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                } else {
                    start = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] objects = new int[]{1, 3, 4, 5, 6, 8, 12, 23, 45};
//        System.out.println(binarySearch(objects, 0, objects.length - 1, 213));
//        System.out.println(binarySearch(objects, 213));


//        List<Integer> list = Arrays.asList(1, 3, 4, 5, 7, 8, 12, 23, 45);
//        System.out.println(searchInsertPosition(list, 20));
//        System.out.println(searchInsertPosition(list, 20));
//        System.out.println(searchInsertPosition(list, 6));
//        System.out.println(searchInsertPosition(list, 1));
//        System.out.println(searchInsertPosition(list, -1));

//        List<Integer> list = Arrays.asList(1, 3, 3, 3, 7, 8, 12, 23, 45);
//        System.out.println(left_bound(list, 45));
//        System.out.println(right_bound(list, 45));

        int[] test =new int[]{9,12,15,20,1,3,6,7};
        for(int i = 0;i<22;i++){
            System.out.println(i + " ： " + findInRotateArray(test, i));
        }

    }

}
