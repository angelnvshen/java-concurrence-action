package own.stu.redis.oneMaster.fakeDistribute.util;

public class SplitPartSize {

    static int max_part_num = 1000;

    static int max_part_size = 10 * 1024 * 1024; // 10MB
    static int min_part_size = 512 * 1024; // 512KB

    static int[] array = new int[]{1024 * 512, 2, 2, 2, 2, 2};

    public static int filePartSize(long total) {
        int size = (int) ((total + max_part_num - 1) / max_part_num);
        if (size > max_part_size) {
            throw new RuntimeException("upload file too big , over 9G");
        }

        size = Math.max(min_part_size, size);

        int trueSize = 1;
        for (int i = 0; i < array.length; i++) {
            trueSize *= array[i];
            if(trueSize > size){
                break;
            }
        }

        return trueSize;
    }

    public static void main(String[] args) {
        System.out.println(filePartSize(1024 * 1024 * 1024 * 3L));
    }

}
