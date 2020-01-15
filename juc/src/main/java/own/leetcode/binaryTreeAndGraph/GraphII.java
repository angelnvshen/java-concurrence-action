package own.leetcode.binaryTreeAndGraph;

import java.util.*;

public class GraphII {
    /**
     * 178. 图是否是树
     * <p>
     * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
     * <p>
     * 样例
     * <p>
     * 输入: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
     * 输出: true.
     * <p>
     * 注意事项
     * 你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中。
     * <p>
     * 树的性质：顶点个数为n,边为n-1。各个顶点都是连接的。
     *
     * @param n
     * @param edges
     * @return
     */
    public static boolean validTree(int n, int[][] edges) {
        if (n == 0 || edges == null) {
            return false;
        }

        if (edges.length == 0 && n == 1) {
            return true;
        }

        int m = edges.length;
        if (n != m + 1) {
            return false;
        }

        // 将边转换为 邻接表
        Map<Integer, Set<Integer>> graph = new HashMap<>(n * 4 / 3 + 1);
        for (int i = 0; i <= m; i++) {
            graph.putIfAbsent(i, new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                graph.get(edges[i][0]).add(edges[i][1]);
                graph.get(edges[i][1]).add(edges[i][0]);
            }
        }

        // 宽搜
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);

        while (!queue.isEmpty()) {
            Integer ele = queue.poll();
            for (Integer neighbor : graph.get(ele)) {
                if (set.contains(neighbor)) {
                    continue;
                }
                set.add(neighbor);
                queue.add(neighbor);
            }
        }

        return set.size() == n;
    }

    /**
     * 137. 克隆图
     * <p>
     * 克隆一张无向图. 无向图的每个节点包含一个 label 和一个列表 neighbors. 保证每个节点的 label 互不相同.
     * <p>
     * 你的程序需要返回一个经过深度拷贝的新图. 新图和原图具有同样的结构, 并且对新图的任何改动不会对原图造成任何影响.
     * <p>
     * 样例
     * <p>
     * 输入:
     * {1,2,4#2,1,4#4,1,2}
     * 输出:
     * {1,2,4#2,1,4#4,1,2}
     *
     * @param node
     * @return
     */
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

        if (node == null) {
            return null;
        }

        // find all nodes;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        queue.add(node);

        List<UndirectedGraphNode> nodeList = new ArrayList<>();

        while (!queue.isEmpty()) {
            UndirectedGraphNode graphNode = queue.poll();
            if (visited.contains(graphNode)) {
                continue;
            } else {
                visited.add(graphNode);
            }
            nodeList.add(graphNode);
            for (UndirectedGraphNode n : graphNode.neighbors) {
                queue.add(n);
            }
        }

        // copy nodes;
        Map<UndirectedGraphNode, UndirectedGraphNode> oldMapNew = new HashMap<>();
        for (UndirectedGraphNode graphNode : nodeList) {
            oldMapNew.put(graphNode, new UndirectedGraphNode(graphNode.label));
        }

        // copy edges;
        for (UndirectedGraphNode oldNode : nodeList) {
            UndirectedGraphNode newNode = oldMapNew.get(oldNode);
            for (UndirectedGraphNode n : oldNode.neighbors) {
                newNode.neighbors.add(oldMapNew.get(n));
            }
        }

        return oldMapNew.get(node);
    }

    /**
     * 127. 拓扑排序
     * 中文English
     * 给定一个有向图，图节点的拓扑排序定义如下:
     * <p>
     * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
     * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
     * 针对给定的有向图找到任意一种拓扑排序的顺序.
     * <p>
     * <p>
     * bfs
     * 先找到入度为0的顶点，从这些顶点 bfs遍历 ，之后判断是否所有节点都进入拓扑排序结果
     *
     * @param graph
     * @return
     */
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();

        if (graph == null) {
            return result;
        }

        // in-degree count
        Map<DirectedGraphNode, Integer> degreeMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            degreeMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                degreeMap.put(neighbor, degreeMap.get(neighbor) + 1);
            }
        }

        // start node (in-degree == 0)
        Queue<DirectedGraphNode> startNode = new LinkedList<>();
        degreeMap.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) {
                startNode.add(entry.getKey());
            }
        });

        //bfs
        while (!startNode.isEmpty()) {
            DirectedGraphNode node = startNode.poll();
            result.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                Integer degree = degreeMap.get(neighbor);
                if (degree > 0) {
                    degree -= 1;
                    degreeMap.put(neighbor, degree);
                    if (degree == 0) {
                        startNode.add(neighbor);
                    }
                } else if (degree == 0) {
                    startNode.add(neighbor);
                }
            }
        }

        if (result.size() == graph.size()) {
            return result;
        }

        return null;
    }

    /**
     * 615. 课程表
     * <p>
     * 现在你总共有 n 门课需要选，记为 0 到 n - 1.
     * 一些课程在修之前需要先修另外的一些课程，比如要学习课程 0 你需要先学习课程 1 ，表示为[0,1]
     * 给定n门课以及他们的先决条件，判断是否可能完成所有课程？
     * <p>
     * 样例
     * <p>
     * 输入: n = 2, prerequisites = [[1,0]]
     * 输出: true
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return false;
        }
        if (prerequisites.length == 1 && prerequisites[0].length == 0) {
            return true;
        }
        // 每一门课的入度
        int degree[] = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int startNode = prerequisites[i][0];
            int endNode = prerequisites[i][1];

            Set<Integer> neighbors = graph.get(startNode);
            if (neighbors.contains(endNode)) {
                continue;
            }
            neighbors.add(endNode);

            degree[endNode]++;
        }

        // 入度为0的科目
        Queue<Integer> startNode = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                startNode.add(i);
            }
        }

        int count = 0;
        while (!startNode.isEmpty()) {
            Integer start = startNode.poll();
            count++;
            Set<Integer> neighbors = graph.get(start);
            for (Integer n : neighbors) {
                degree[n]--; // 核心 入度为0的相邻顶点的入度 减 1
                if (degree[n] == 0) {
                    startNode.add(n);
                }
            }
        }

        return count == numCourses;
    }

    /**
     * 210. 课程表 II
     * <p>
     * 找出一种拓扑排序的结果
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return null;
        }
        int[] result = new int[numCourses];
        if (prerequisites.length == 1 && prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        // 每一门课的入度
        int degree[] = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int startNode = prerequisites[i][1];
            int endNode = prerequisites[i][0];

            Set<Integer> neighbors = graph.get(startNode);
            if (neighbors.contains(endNode)) {
                continue;
            }
            neighbors.add(endNode);

            degree[endNode]++;
        }

        // 入度为0的科目
        Queue<Integer> startNode = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                startNode.add(i);
            }
        }

        int count = 0;
        while (!startNode.isEmpty()) {

            Integer start = startNode.poll();
            result[count] = start;
            count++;

            Set<Integer> neighbors = graph.get(start);
            for (Integer n : neighbors) {
                degree[n]--; // 核心 入度为0的相邻顶点的入度 减 1
                if (degree[n] == 0) {
                    startNode.add(n);
                }
            }
        }

        return count == numCourses ? result : new int[]{};
    }

    /**
     * 135. 数字组合
     * 中文English
     * 给定一个候选数字的集合 candidates 和一个目标值 target. 找到 candidates 中所有的和为 target 的组合.
     * <p>
     * 在同一个组合中, candidates 中的某个数字不限次数地出现.
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: candidates = [2, 3, 6, 7], target = 7
     * 输出: [[7], [2, 2, 3]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }
        if (candidates.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        Arrays.sort(candidates);
        recursionCombination(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }

    private static void recursionCombination(int[] candidates, int startIndex, int curTarget,
                                             List<Integer> combination, List<List<Integer>> result) {

        if (curTarget == 0) {
            result.add(new ArrayList<>(combination)); // 注意 clone 数组
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {

            if (i > 0 && candidates[i] == candidates[i - 1]) { // 过滤 输入的重复值
                continue;
            }

            if (curTarget < candidates[i]) {
                continue;
            }

            combination.add(candidates[i]);

            // core 结果集中可以选择重复的元素
            recursionCombination(candidates, i, curTarget - candidates[i], combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    /**
     * 153. 数字组合 II
     * <p>
     * 给定一个数组 num 和一个整数 target. 找到 num 中所有的数字之和为 target 的组合.
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: num = [7,1,2,5,1,6,10], target = 8
     * 输出: [[1,1,6],[1,2,5],[1,7],[2,6]]
     * 样例 2:
     * <p>
     * 输入: num = [1,1,1], target = 2
     * 输出: [[1,1]]
     * <p>
     * 解释: 解集不能包含重复的组合
     * 注意事项
     * 在同一个组合中, num 中的每一个数字仅能被使用一次.
     * 所有数值 (包括 target ) 都是正整数.
     * 返回的每一个组合内的数字必须是非降序的.
     * 返回的所有组合之间可以是任意顺序.
     * 解集不能包含重复的组合.
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) {
            return result;
        }
        if (candidates.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        Arrays.sort(candidates);
        recursionCombinationII(candidates, 0, target, new ArrayList<>(), result);

        return result;
    }

    private static void recursionCombinationII(int[] candidates, int startIndex, int curTarget,
                                               List<Integer> combination, List<List<Integer>> result) {

        if (curTarget == 0) {
            result.add(new ArrayList<>(combination)); // 注意 clone 数组
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {

            if (curTarget < candidates[i]) {
                continue;
            }

            // 取出重复结果
            if (i != startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            combination.add(candidates[i]);

            // core 结果集中不可以选择重复的元素 故 startIndex = i + 1
            recursionCombinationII(candidates, i + 1, curTarget - candidates[i], combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    /**
     * 17. 子集
     * <p>
     * 给定一个含不同整数的集合，返回其所有的子集。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入：[0]
     * 输出：
     * [
     * [],
     * [0]
     * ]
     * 样例 2：
     * <p>
     * 输入：[1,2,3]
     * 输出：
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     * <p>
     * dfs 深度优先搜索
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        result.add(new ArrayList<>());
        recursionSubSet(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private static void recursionSubSet(int[] nums, int startIndex, List<Integer> subSet, List<List<Integer>> result) {
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            subSet.add(nums[i]);
            result.add(new ArrayList<>(subSet));
            recursionSubSet(nums, i + 1, subSet, result);
            subSet.remove(subSet.size() - 1);
        }
    }

    /**
     * 18. 子集 II
     * <p>
     * 给定一个可能具有重复数字的列表，返回其所有可能的子集。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入：[0]
     * 输出：
     * [
     * [],
     * [0]
     * ]
     * 样例 2：
     * <p>
     * 输入：[1,2,2]
     * 输出：
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        Arrays.sort(nums);

        result.add(new ArrayList<>()); //空集

        recursionWithDup(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private static void recursionWithDup(int[] nums, int startIndex, List<Integer> subSet, List<List<Integer>> result) {
        if (startIndex >= nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {

            // 解集不能包含重复的子集 因为nums 排完序后，如{0,1,1} 子集 ：{ 0，1（1）}和{0, 1（2）}是一个结果
            if (i != startIndex && nums[i] == nums[i - 1]) { // core  ***
                continue;
            }

            subSet.add(nums[i]);
            result.add(new ArrayList<>(subSet));
            recursionWithDup(nums, i + 1, subSet, result);
            subSet.remove(subSet.size() - 1);
        }
    }

    /**
     * 136. 分割回文串
     * <p>
     * 给定字符串 s, 需要将它分割成一些子串, 使得每个子串都是回文串.
     * <p>
     * 返回所有可能的分割方案.
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: "a"
     * 输出: [["a"]]
     * 解释: 字符串里只有一个字符, 也就只有一种分割方式 (就是它本身)
     * 样例 2:
     * <p>
     * 输入: "aab"
     * 输出: [["aa", "b"], ["a", "a", "b"]]
     * 解释: 有两种分割的方式.
     * 1. 将 "aab" 分割成 "aa" 和 "b", 它们都是回文的.
     * 2. 将 "aab" 分割成 "a", "a" 和 "b", 它们全都是回文的.
     *
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        if (s == null) {
            return result;
        }

        if (s.length() == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        recursionPartition(s, 0, new ArrayList<>(), result);

//        boolean[][] palin = isPalin(s);
//        recursionPartition_2(s, 0, palin, new ArrayList<>(), result);

        return result;
    }

    private static void recursionPartition_2(String source, int startIndex, boolean[][] palin, List<String> partition, List<List<String>> result) {
        if (startIndex == source.length()) {
            result.add(new ArrayList<>(partition));
            return;
        }

        for (int i = startIndex; i < source.length(); i++) {
            if (!palin[startIndex][i]) {
                continue;
            }
            String temp = source.substring(startIndex, i + 1);
            partition.add(temp);
            recursionPartition_2(source, i + 1, palin, partition, result);
            partition.remove(partition.size() - 1);
        }
    }

    private static void recursionPartition(String source, int startIndex, List<String> partition, List<List<String>> result) {

        if (startIndex >= source.length()) {
            result.add(new ArrayList<>(partition));
            return;
        }

        for (int i = startIndex; i < source.length(); i++) {
            String substring = source.substring(startIndex, i + 1);
            if (!isPalinSimple(substring)) {
                continue;
            }

            partition.add(substring);
            recursionPartition(source, i + 1, partition, result);
            partition.remove(partition.size() - 1);
        }
    }

    private static boolean isPalinSimple(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // s[i][j]表示 s[i..j-1]是否是回文串，以生成 回文串的方式来判断
    private static boolean[][] isPalin(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        boolean pa[][] = new boolean[n][n];

        int i, j;
        for (int c = 0; c < n; c++) {// s的子串长度为偶数
            i = j = c;
            while (i >= 0 && j < n && chars[i] == chars[j]) {
                pa[i][j] = true;
                i--;
                j++;
            }
        }

        for (int c = 0; c < n - 1; c++) {// s的子串长度为奇数，表示以字符中间竖线来向两头扩展
            i = c;
            j = c + 1;
            while (i >= 0 && j < n && chars[i] == chars[j]) {
                pa[i][j] = true;
                i--;
                j++;
            }
        }

        return pa;
    }

    public static void main(String[] args) {
        // ========================================
        /*int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(validTree(5, edges));

        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}};
        System.out.println(validTree(5, edges));

        int[][] edges = new int[][]{{}};
        System.out.println(validTree(1, edges));*/

        // ========================================
        /*// {1,2,4#2,1,4#4,1,2}
        String serializedStr = "1,2,4#2,1,4#4,1,2";
        UndirectedGraphNode node = stringToGraphNode(serializedStr);
        UndirectedGraphNode cloneGraph = cloneGraph(node);
        System.out.println(graphNodeToString(cloneGraph));*/

        // ========================================
        /*ArrayList<DirectedGraphNode> directedGraphNodes = geneDirectedGraphNode("0,1,2,3,4#1,3,4#2,1,4#3,4#4");
        ArrayList<DirectedGraphNode> topSort = topSort(directedGraphNodes);
        if (topSort != null)
            topSort.stream().forEach(node -> System.out.println(node.label));*/

        // ========================================
        /*int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println(canFinish(2, prerequisites));
        System.out.println(findOrder(2, prerequisites));*/

        /*prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(canFinish(2, prerequisites));

        int[][] prerequisites = new int[][]{{}};
        System.out.println(canFinish(1, prerequisites));
        System.out.println(canFinish(6, prerequisites));

        int[][] prerequisites = new int[][]{{5, 8}, {3, 5}, {1, 9}, {4, 5}, {0, 2}, {1, 9}, {7, 8}, {4, 9}};
        System.out.println(canFinish(10, prerequisites));*/

        // ========================================
        /*List<List<Integer>> subsets = subsets(new int[]{0});
        List<List<Integer>> subsets = subsets(new int[]{1, 2});
        subsets.forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + ", "));
            System.out.println();
        });*/
        // ----------------------------------------
        /*List<List<Integer>> subsets = subsetsWithDup(new int[]{1, 2, 2});
        subsets.forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + ", "));
            System.out.println();
        });*/

        // ========================================
        /*List<List<Integer>> subsets = combinationSum(new int[]{2, 3, 6, 7}, 7);
        List<List<Integer>> subsets = combinationSum(new int[]{2, 2, 3}, 5);
        subsets.forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + ", "));
            System.out.println();
        });*/
        // ----------------------------------------
        /*List<List<Integer>> subsets = combinationSum2(new int[]{7, 1, 2, 5, 1, 6, 10}, 8);
        subsets.forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + ", "));
            System.out.println();
        });*/

        // ========================================
        List<List<String>> partition = partition("cbbbcc");
        partition.forEach(list -> {
            list.stream().forEach(ele -> System.out.print(ele + ", "));
            System.out.println();
        });
    }

    /**
     * "1,2,4#2,1,4#4,1,2";
     * <p>
     * decode
     *
     * @param serializedStr
     * @return
     */
    private static UndirectedGraphNode stringToGraphNode(String serializedStr) {
        int firstNodeValue = -1;

        firstNodeValue = Integer.valueOf(serializedStr.charAt(0) + "");

        String[] split = serializedStr.split("#");
        Map<Integer, UndirectedGraphNode> nodeMap = new HashMap<>();
        for (String str : split) {

            //1,2,4 顶点1的临结顶点 2，4
            String[] vertexes = str.split(",");
            Integer v = Integer.valueOf(vertexes[0]);

            UndirectedGraphNode node = nodeMap.get(v);
            if (node == null) {
                node = new UndirectedGraphNode(v);
                nodeMap.put(v, node);
            }

            nodeMap.putIfAbsent(v, node);
            if (vertexes.length <= 1) {
                continue;
            }

            for (int i = 1; i < vertexes.length; i++) {
                Integer v_temp = Integer.valueOf(vertexes[i]);
                UndirectedGraphNode node_temp = nodeMap.get(v_temp);
                if (node_temp == null) {
                    node_temp = new UndirectedGraphNode(v_temp);
                    nodeMap.put(v_temp, node_temp);
                }

                node.neighbors.add(node_temp);
            }
        }

        return nodeMap.get(firstNodeValue);
    }

    /**
     * encode
     *
     * @param node
     * @return
     */
    private static String graphNodeToString(UndirectedGraphNode node) {
        if (node == null) {
            return "";
        }

        StringBuffer result = new StringBuffer();

        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        queue.addLast(node);

        while (!queue.isEmpty()) {

            UndirectedGraphNode last = queue.pollLast();
            if (visited.contains(last)) {
                continue;
            } else {
                visited.add(last);
            }
            result.append(last.label);

            for (UndirectedGraphNode n : last.neighbors) {
                result.append(",");
                result.append(n.label);
                queue.addFirst(n);
            }
            result.append("#");
        }

        return result.toString();
    }

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    static class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    private static ArrayList<DirectedGraphNode> geneDirectedGraphNode(String strs) {
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        if (strs == null || strs.length() == 0) {
            return result;
        }

        Map<Integer, DirectedGraphNode> map = new HashMap<>();
        //{0,1,2,3,4#1,3,4#2,1,4#3,4#4}
        for (String str : strs.split("#")) {

            String[] values = str.split(",");

            Integer label = Integer.valueOf(values[0]);
            DirectedGraphNode node = map.get(label);
            if (node == null) {
                node = new DirectedGraphNode(label);
                map.put(label, node);
            }

            for (int i = 1; i < values.length; i++) {
                Integer label_neibor = Integer.valueOf(values[i]);
                DirectedGraphNode neibor = map.get(label_neibor);
                if (neibor == null) {
                    neibor = new DirectedGraphNode(label_neibor);
                    map.put(label_neibor, neibor);
                }
                node.neighbors.add(neibor);
            }
            result.add(node);
        }

        return result;
    }
}
