package own.stu.algorithms_4th.fundamentals.basicProgrammingModel.exercise;

import edu.princeton.cs.algs4.Queue;

public class Josephus {
    public static void main(String[] args) {
        deal(3, 4 );
    }

    private static void deal(int n, int m){
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        while (!queue.isEmpty()){
            for(int i = 0;i<m-1;i++){
                queue.enqueue(queue.dequeue());
            }
            System.out.println(queue.dequeue() + " ");
        }
    }
}
