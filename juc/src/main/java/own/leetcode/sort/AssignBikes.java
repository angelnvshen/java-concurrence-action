package own.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignBikes {

    public static void main(String[] args) {
        AssignBikes bikes = new AssignBikes();

        System.out.println(bikes.assignBikes(new int[][]{{0, 0}, {2, 1}},
                new int[][]{{1, 2}, {3, 3}}));
    }

    /**
     * @param workers: workers' location
     * @param bikes:   bikes' location
     * @return: assign bikes
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        // write your code here
        if (workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
            return new int[0];
        }

        int n = workers.length;
        int m = bikes.length;
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int distance = Math.abs(workers[i][0] - bikes[j][0]) +
                        Math.abs(workers[i][1] - bikes[j][1]);
                nodes.add(new Node(distance, j, i));
            }
        }

        Collections.sort(nodes, (a, b) -> {
            if (a.distance != b.distance) {
                return a.distance - b.distance;
            } else {
                if (a.workerIndex != b.workerIndex) {
                    return a.workerIndex - b.workerIndex;
                }
                return a.bikeIndex - b.bikeIndex;
            }
        });
        int[] ans = new int[n];
        boolean[] visitedWorker = new boolean[n];
        boolean[] visitedBikes = new boolean[m];
        for (Node node : nodes) {
            if (visitedWorker[node.workerIndex] || visitedBikes[node.bikeIndex]) {
                continue;
            }
            visitedWorker[node.workerIndex] = visitedBikes[node.bikeIndex] = true;
            ans[node.workerIndex] = node.bikeIndex;
        }

        return ans;
    }

    class Node {
        int distance;
        int bikeIndex;
        int workerIndex;

        public Node(int distance, int bikeIndex, int workerIndex) {
            this.distance = distance;
            this.bikeIndex = bikeIndex;
            this.workerIndex = workerIndex;
        }
    }
}