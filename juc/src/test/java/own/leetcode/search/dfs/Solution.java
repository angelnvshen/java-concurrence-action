package own.leetcode.search.dfs;

import own.jdk.ListNode;
import own.leetcode.TreeNode;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        /*solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.",
                new String[]{"hit"}
        );*/

        /*System.out.println(solution.mostCommonWord("abb",
                new String[]{}
        ));*/

        /*solution.peopleIndexes(Arrays.asList(
                Arrays.asList("nxaqhyoprhlhvhyojanr", "ovqdyfqmlpxapbjwtssm", "qmsbphxzmnvflrwyvxlc", "udfuxjdxkxwqnqvgjjsp", "yawoixzhsdkaaauramvg", "zycidpyopumzgdpamnty"),
                Arrays.asList("nxaqhyoprhlhvhyojanr", "ovqdyfqmlpxapbjwtssm", "udfuxjdxkxwqnqvgjjsp", "yawoixzhsdkaaauramvg", "zycidpyopumzgdpamnty"),
                Arrays.asList("ovqdyfqmlpxapbjwtssm", "qmsbphxzmnvflrwyvxlc", "udfuxjdxkxwqnqvgjjsp", "yawoixzhsdkaaauramvg", "zycidpyopumzgdpamnty")
                ));*/

//        System.out.println(solution.fourSum(new int[]{1,1,1, 0, -1, 0, -2, 2}, 0));


        /*System.out.println(solution.fourSum(new int[]{
                91277418, 66271374, 38763793, 4092006, 11415077, 60468277, 1122637, 72398035, -62267800, 22082642, 60359529, -16540633, 92671879, -64462734, -55855043, -40899846, 88007957, -57387813, -49552230, -96789394, 18318594, -3246760, -44346548, -21370279, 42493875, 25185969, 83216261, -70078020, -53687927, -76072023, -65863359, -61708176, -29175835, 85675811, -80575807, -92211746, 44755622, -23368379, 23619674, -749263, -40707953, -68966953, 72694581, -52328726, -78618474, 40958224, -2921736, -55902268, -74278762, 63342010, 29076029, 58781716, 56045007, -67966567, -79405127, -45778231, -47167435, 1586413, -58822903, -51277270, 87348634, -86955956, -47418266, 74884315, -36952674, -29067969, -98812826, -44893101, -22516153, -34522513, 34091871, -79583480, 47562301, 6154068, 87601405, -48859327, -2183204, 17736781, 31189878, -23814871, -35880166, 39204002, 93248899, -42067196, -49473145, -75235452, -61923200, 64824322, -88505198, 20903451, -80926102, 56089387, -58094433, 37743524, -71480010, -14975982, 19473982, 47085913, -90793462, -33520678, 70775566, -76347995, -16091435, 94700640, 17183454, 85735982, 90399615, -86251609, -68167910, -95327478, 90586275, -99524469, 16999817, 27815883, -88279865, 53092631, 75125438, 44270568, -23129316, -846252, -59608044, 90938699, 80923976, 3534451, 6218186, 41256179, -9165388, -11897463, 92423776, -38991231, -6082654, 92275443, 74040861, 77457712, -80549965, -42515693, 69918944, -95198414, 15677446, -52451179, -50111167, -23732840, 39520751, -90474508, -27860023, 65164540, 26582346, -20183515, 99018741, -2826130, -28461563, -24759460, -83828963, -1739800, 71207113, 26434787, 52931083, -33111208, 38314304, -29429107, -5567826, -5149750, 9582750, 85289753, 75490866, -93202942, -85974081, 7365682, -42953023, 21825824, 68329208, -87994788, 3460985, 18744871, -49724457, -12982362, -47800372, 39958829, -95981751, -71017359, -18397211, 27941418, -34699076, 74174334, 96928957, 44328607, 49293516, -39034828, 5945763, -47046163, 10986423, 63478877, 30677010, -21202664, -86235407, 3164123, 8956697, -9003909, -18929014, -73824245
        }, -236727523));*/

//        int[] nums = new int[]{1, 3, 2, 2, 3, 1};
        /*int[] nums = new int[]{1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums);
        Printer.print(nums);*/

//        System.out.println(solution.countOfAtoms("K4(ON(SO3)2)2"));
//        System.out.println(solution.countOfAtoms("Mg22(O10H10)20"));
//        System.out.println(solution.countOfAtoms("H2O"));

//        System.out.println(solution.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
//        System.out.println(solution.numSubarraysWithSum(new int[]{0, 0}, 0));
//        System.out.println(solution.numSubarraysWithSum(new int[]{0, 1, 1, 0, 1}, 2));

//        System.out.println(solution.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));

//        System.out.println(solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
//        System.out.println(solution.numSubarrayProductLessThanK(new int[]{1, 2, 3},  0));

//        System.out.println(solution.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
//        System.out.println(solution.isNStraightHand(new int[]{1, 2, 3, 4, 7, 8}, 3));


//        System.out.println(solution.isUnique("abc"));
//        System.out.println(solution.isUnique("leetcode"));
//        System.out.println(solution.isUnique("abbc"));

//        System.out.println((char)(1 + 'a'));

//        System.out.println(solution.compressString("aabcccccaaa"));

        /*ListNode node = new ListNode(1);
        node.next(new ListNode(1)).next(new ListNode(1)).
                next(new ListNode(2)).next(new ListNode(2));
        ListNode.print(solution.removeDuplicateNodes(node));*/

//        System.out.println((2 << 1));
//        System.out.println(solution.reverseBits(Integer.MIN_VALUE));
//        System.out.println(solution.reverseBits(0));
//        System.out.println(solution.reverseBits(7));
//        System.out.println(solution.reverseBits(1775));
//        System.out.println(Integer.MIN_VALUE);

//        System.out.println(solution.add(10, 22));

        /*solution.pondSizes(new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1},
        });*/

        /*System.out.println(solution.calculate("3+2*2"));
        System.out.println(solution.calculate(" 3/2 "));
        System.out.println(solution.calculate(" 3+5 / 2 "));
        System.out.println(solution.calculate("42"));
        System.out.println(solution.calculate("1-1+1"));
        System.out.println(solution.calculate("1+1-1"));
        System.out.println(solution.calculate("0-2147483647"));*/

//        String[] strs = solution.findLongestSubarray(new String[]{"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"});
        String[] strs = solution.findLongestSubarray(new String[]{"A", "M"});
        System.out.println(Arrays.asList(strs).toString());
    }

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> ans = new ArrayList<>();
        if (favoriteCompanies == null || favoriteCompanies.size() == 0) {
            return ans;
        }

        int n = favoriteCompanies.size();
        Map<Integer, String> companyMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> company = favoriteCompanies.get(i);
            Collections.sort(company);
            companyMap.put(i, String.join("#", company));
        }
        boolean[] users = new boolean[n];
        for (int i = 0; i < n; i++) {
            String ci = companyMap.get(i);
            for (int j = i + 1; j < n; j++) {
                if (users[j]) continue;
                String cj = companyMap.get(j);
                if (ci.length() < cj.length()) {
                    if (cj.indexOf(ci) >= 0) {
                        users[i] = true;
                        continue;
                    }
                } else if (ci.length() > cj.length()) {
                    if (ci.indexOf(cj) >= 0) {
                        users[j] = true;
                        continue;
                    }
                } else {
                    if (ci.equals(cj)) {
                        users[i] = true;
                        users[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!users[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0 || banned == null) {
            return "";
        }
        paragraph = paragraph.toLowerCase();
        Set<String> except = new HashSet<>();
        for (String s : banned) {
            except.add(s);
        }

        char[] cs = paragraph.toCharArray();
        int n = paragraph.length();
        int l = -1;
        int r = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (notWordSet.contains(cs[i])) {

                if (l + 1 <= r) {
                    String tmp = paragraph.substring(l + 1, r + 1);
                    if (!except.contains(tmp))
                        map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                }

                l = i;
                continue;
            } else {
                r = i;
            }
        }
        if (!notWordSet.contains(cs[n - 1])) {
            String tmp = paragraph.substring(l + 1, r + 1);
            if (!except.contains(tmp))
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        if (map.size() == 0) return "";
        int maxLen = 0;
        String ans = "";
        for (Map.Entry<String, Integer> en : map.entrySet()) {
            if (maxLen < en.getValue()) {
                maxLen = en.getValue();
                ans = en.getKey();
            }
        }
        return ans;
    }

    private static char[] notWords = "!?',;. ".toCharArray();
    private static Set<Character> notWordSet = new HashSet<>();

    {
        for (char c : notWords) {
            notWordSet.add(c);
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);

        this.target = target;
        this.nums = nums;
        helper(ans, 0, 0, new ArrayList<>());

        return ans;
    }

    private int[] nums;
    private int target;
    private static int COUNT = 4;

    private void helper(List<List<Integer>> ans, int sum, int index, List<Integer> result) {
        if (result.size() == COUNT) {
            if (sum == target) {
                ans.add(new ArrayList<>(result));
            }
            return;
        }
        int leftNum = COUNT - result.size();
        int last = nums.length - leftNum + 1;
        for (int i = index; i < last; i++) {
            // 除去重复
            if (i > index && nums[i] == nums[i - 1]) continue;

            result.add(nums[i]);
            helper(ans, sum + nums[i], i + 1, result);
            result.remove(result.size() - 1);
        }
    }

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(entry.getKey());
        }

        int even = 0;
        int odd = 1;
        while (!heap.isEmpty()) {
            int num = heap.poll();
            int count = map.get(num);
            for (int i = 0; i < count; i++) {
                if (odd < n) {
                    nums[odd] = num;
                    odd += 2;
                } else {
                    nums[even] = num;
                    even += 2;
                }
            }
        }
    }

    public String countOfAtoms(String formula) {
        if (formula == null || formula.length() == 0) return formula;

        char[] cs = formula.toCharArray();
        int n = formula.length();

        Deque<Map<String, Integer>> stack = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        String atom = null;
        int times = 0;
        int right = 0;
        int i = 0;
        while (i < n) {
            right = i;

            if (isStartOfAtom(cs[i])) {
                right = endIndexOfAtom(cs, i);
                atom = formula.substring(i, right + 1);

                // 如果原子后边接着的不是数字，那么它的次数就是1
                if ((right + 1 < n && !isStartOfNum(cs[right + 1])) || right + 1 == n) {
                    map.put(atom, map.getOrDefault(atom, 0) + 1);
                    times = 0;
                }
                i = right + 1;

            } else if (isStartOfNum(cs[i])) {

                // 1 ： 数字前是 ‘）’时，需要将之前的map内容进行 乘以相应的次数
                right = endIndexOfNum(cs, i);
                times = Integer.valueOf(formula.substring(i, right + 1));

                if (i - 1 >= 0 && cs[i - 1] == ')') {
                    Map<String, Integer> tmp = stack.pop();

                    map = stack.pop();
                    merge(map, tmp, times);

                } else {
                    map.put(atom, map.getOrDefault(atom, 0) + times);
                    atom = null;
                }
                times = 0;
                i = right + 1;

            } else if (cs[i] == '(') {
                stack.push(map);
                map = new HashMap<>();
                i += 1;
            } else if (cs[i] == ')') {
                stack.push(map);
                map = null;
                i += 1;
            }
        }

        if (!stack.isEmpty()) {
            Map<String, Integer> tmp = stack.pop();
            merge(map, tmp, 1);
        }

        StringBuilder sb = new StringBuilder();
        PriorityQueue<String> heap = new PriorityQueue<>();
        for (Map.Entry<String, Integer> en : map.entrySet()) {
            heap.add(en.getKey());
        }

        while (!heap.isEmpty()) {
            String atomStr = heap.poll();
            sb.append(atomStr);
            int t = map.get(atomStr);
            if (t > 1) {
                sb.append(t);
            }
        }
        return sb.toString();
    }

    private void merge(Map<String, Integer> map, Map<String, Integer> tmp, int times) {
        if (map == null) {
            for (Map.Entry<String, Integer> en : tmp.entrySet()) {
                String strAtom = en.getKey();
                int t = en.getValue() * times;
                tmp.put(strAtom, t);
            }
        } else {
            for (Map.Entry<String, Integer> en : tmp.entrySet()) {
                String strAtom = en.getKey();
                int t = en.getValue() * times;
                map.put(strAtom, map.getOrDefault(strAtom, 0) + t);
            }
        }
    }

    private int endIndexOfNum(char[] cs, int startIndex) {
        int endIndex = startIndex;
        if (Character.isDigit(cs[endIndex])) {

            while (endIndex + 1 < cs.length) {
                if (Character.isDigit(cs[endIndex + 1])) {
                    endIndex += 1;
                } else {
                    break;
                }
            }
            return endIndex;
        } else {
            return -1;
        }
    }

    private boolean isStartOfNum(char c) {
        return Character.isDigit(c);
    }

    private boolean isStartOfAtom(char c) {
        int accii = c - 'A';
        return accii >= 0 && accii <= 25;
    }

    private int endIndexOfAtom(char[] cs, int startIndex) {
        int endIndex = startIndex;
        if (isStartOfAtom(cs[endIndex])) {

            while (endIndex + 1 < cs.length) {
                if (Character.isLowerCase(cs[endIndex + 1])) {
                    endIndex += 1;
                } else {
                    break;
                }
            }
            return endIndex;
        } else {
            return -1;
        }
    }

    public int numSubarraysWithSum(int[] A, int S) {
        /* 滑动窗口*/

        if (A == null || A.length == 0 || S < 0) {
            return 0;
        }

        int n = A.length;
        Integer sum = null;

        int ans = 0;

        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        int preSum = 0;
        for (int i = 0; i < n; i++) {
            preSum += A[i];
            ans += countMap.getOrDefault(preSum - S, 0);
            countMap.put(preSum, countMap.getOrDefault(preSum, 0) + 1);
        }

        return ans;
    }

    /**
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     * <p>
     * 示例：
     * <p>
     * 输入：A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 解释：
     * 有 7 个子数组满足其元素之和可被 K = 5 整除：
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {

        if (A == null || A.length == 0 || K <= 1) {
            return 0;
        }

        int ans = 0;
        /*
        前缀和，然后遍历
        * */
        int n = A.length;
        int[] pre = new int[n + 1];
        pre[0] = 0;
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + A[i - 1];
        }

        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if ((pre[j] - pre[i]) % K == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        int ans = 0;
        int n = nums.length;
        int prod = 1;
        int l = 0;
        for (int r = 0; r < n; r++) {
            prod *= nums[r];
            while (prod >= k) {
                prod /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0 || W <= 0) {
            return false;
        }

        int n = hand.length;
        if (n % 3 != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int card : hand) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        while (!map.isEmpty()) {
            int card = map.firstKey();
            for (int i = card; i < card + W; i++) {
                Integer cur = map.get(i); // 当前排的个数
                if (cur == null) return false;

                if (cur == 1) map.remove(i);
                else map.put(i, cur - 1);
            }
        }

        return map.isEmpty();
    }

    public boolean isUnique(String astr) {
        if (astr == null) {
            return false;
        }

        int n = astr.length();
        if (n == 0) return true;

        char[] cs = astr.toCharArray();

        long loMark = 0L;
        long hiMark = 0L;
        long bit = 0l;
        for (char c : cs) {
            if (c >= 64) {
                bit = (1 << c - 64);
                if ((hiMark & bit) > 0) return false;

                hiMark |= bit;
            } else {
                bit = (1 << c);
                if ((loMark & bit) > 0) return false;

                loMark |= bit;
            }
        }

        return true;
    }

    public String compressString(String S) {
        if (S == null || S.length() <= 1) {
            return S;
        }
        int[] map = new int[26];
        for (char c : S.toCharArray()) {
            map[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0) continue;
            sb.append((char) (i + 'a')).append(map[i]);
        }
        System.out.println(sb.toString());
        if (sb.length() < S.length()) return sb.toString();
        return S;

    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode begin = dummyHead.next;
        while (begin != null) {
            ListNode cur = begin;
            int val = cur.val;
            while (cur != null) {
                ListNode next = cur.next;
                if (next == null) break;
                if (val == next.val) {
                    cur.next = next.next;
                } else {
                    cur = cur.next;
                }
            }
            begin = begin.next;
        }
        return dummyHead.next;
    }

    public int reverseBits(int num) {

        if (num == 0) return 1;

        int preLen = 0, curLen = 0;
        int preLastIndex = -1, curLastIndex = -1;

        int index = 0;

        int ans = 0;
        /* 总计由0分割成的每一段 1的个数*/
        while (index < 32) {
            if (valueOne(num, index)) {
                int l = index;
                while (index + 1 < 32
                        && valueOne(num, index + 1)) {
                    index += 1;
                }
                curLen = index - l + 1;
                curLastIndex = index;

                // 表示可以和前一段1来进行比较，看是否可以拼在一起
                if (preLastIndex != -1 && l - 2 == preLastIndex) {
                    ans = Math.max(ans, preLen + curLen + 1);
                } else {
                    ans = Math.max(ans, curLen + 1);
                }

            } else {// 遇到0
                preLen = curLen;
                preLastIndex = curLastIndex;
                curLen = 0;
                curLastIndex = -1;
            }
            index += 1;
        }
        return ans;
    }

    private boolean valueOne(int num, int index) {
        int bit = 1 << index;
        return (num & bit) != 0;
    }

    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return root;
        }

//        TreeNode right = convertBiNode(root.right);
//
//        TreeNode rightest = findRightest(root.left);
//
//        TreeNode left = convertBiNode(root.left);
        return null;
    }

    private TreeNode findRightest(TreeNode node) {
        if (node == null || node.right == null) return node;
        TreeNode rightest = node;
        while (rightest.right != null) {
            rightest = rightest.right;
        }
        return rightest;
    }

    public int add(int a, int b) {
        int sum = 0;
        int carry = 0;

        System.out.println(a + " [a] " + Integer.toBinaryString(a));
        System.out.println(b + " [b] " + Integer.toBinaryString(b));
        while (b != 0) {
            System.out.println(" ---------- ");
            sum = a ^ b;
            carry = (a & b) << 1;// 左移一位

            a = sum;
            b = carry;
            System.out.println(a + " [a] " + Integer.toBinaryString(a));
            System.out.println(b + " [b] " + Integer.toBinaryString(b));
        }
        return a;
    }

    public int[] pondSizes(int[][] land) {
        if (land == null || land.length == 0 || land[0].length == 0) {
            return new int[0];
        }
        this.land = land;
        this.row = land.length;
        this.col = land[0].length;

        this.visited = new boolean[row][col];

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] || land[i][j] != 0) {
                    continue;
                }
                visited[i][j] = true;
                int count = bfs(i, j);
                map.put(count, map.getOrDefault(count, 0) + 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            for (int i = 0; i < en.getValue(); i++) {
                list.add(en.getKey());
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private int bfs(int r, int c) {
        System.out.println(" ==== " + r + ", " + c);
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            count += 1;
            for (int d = 0; d < dx.length; d++) {
                int x = dx[d] + pair[0];
                int y = dy[d] + pair[1];
                if (x < 0 || y < 0 || x >= row || y >= col || visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                if (land[x][y] == 0) {
                    System.out.println(x + ", " + y);
                    queue.add(new int[]{x, y});
                }
            }
        }
        return count;
    }

    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    private int[][] land;
    private boolean[][] visited;
    private int row;
    private int col;

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] cs = s.toCharArray();

        Deque<Integer> data = new LinkedList<>();
        Deque<Character> operator = new LinkedList<>();
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = cs[i];
            if (c == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                //可能有多位数字
                int r = i;
                while (r + 1 < n && Character.isDigit(cs[r + 1])) {
                    r += 1;
                }
                int num = Integer.valueOf(s.substring(i, r + 1));

                i = r + 1;

                // 如果之前的操作数是 + - 先将数字push到stack
                // 如果是 * / 先计算，再push
                if (!operator.isEmpty()) {
                    char op = operator.peek();
                    if (op == '*' || op == '/') {
                        operator.pop();
                        int firstNum = data.pop();
                        int val = 0;
                        if (op == '*') {
                            val = firstNum * (num);
                        } else {
                            val = firstNum / (num);
                        }
                        data.push(val);
                        continue;
                    }
                }
                data.push(num);
            } else {
                operator.push(c);
                i++;
            }
        }

        if (data.size() == 1) return data.peek();

        while (data.size() > 1) {
            char op = operator.pollLast();
            int first = data.pollLast();
            int sec = data.pollLast();
            if (op == '+') {
                data.addLast(sec + first);
            } else {
                data.addLast(first - sec);
            }
        }

        return data.peek();
    }

    public String[] findLongestSubarray(String[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int n = array.length;

        /*
        想象字母是1， 数字是 -1，求前缀和是0的最大间距即可
        */

        // value : index;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // base
        int preSum = 0;
        int ans = 0;
        int l = -1;
        int r = -1;
        for (int i = 0; i < n; i++) {
            String c = array[i];
            if (isNum(c)) {
                preSum -= 1;
            } else {
                preSum += 1;
            }

            Integer lastSameIndex = map.get(preSum);
            if (lastSameIndex != null) {
                if (ans < i - lastSameIndex) {
                    l = lastSameIndex;
                    r = i;
                    ans = i - lastSameIndex;
                }
            } else {
                map.put(preSum, i);
            }
        }

        return Arrays.copyOfRange(array, l + 1, r + 1);
    }

    private boolean isNum(String s) {
        try {
            Integer.valueOf(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}