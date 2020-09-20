package own.leetcode;

public class Solution2 {

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.canPlaceFlowers(new int[]{0,0,0,1}, 1);
    }

    /**
     * @param flowerbed: an array
     * @param n:         an Integer
     * @return: if n new flowers can be planted in it without violating the no-adjacent-flowers rule
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // Write your code here
        if (flowerbed == null || flowerbed.length == 0) return false;
        if (n == 0) return true;

        int num = 0;
        int count = 1; // 补个0
        for(int i : flowerbed){
            if(i == 1){
                count = 0;
                continue;
            }

            if(i == 0){
                count += 1;
            }
            if(count == 3){
                num += 1;
                count = 1;
            }
        }

        if(count == 2){//如果最后count为2而不是1，表示最后一个位置可以种花
            num += 1;
        }

        return num >= n;
    }
}