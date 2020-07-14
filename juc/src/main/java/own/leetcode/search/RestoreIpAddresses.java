package own.leetcode.search;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    public static void main(String[] args) {
        RestoreIpAddresses addresses = new RestoreIpAddresses();
//        System.out.println(addresses.restoreIpAddresses("25525511135"));
        System.out.println(addresses.restoreIpAddresses("010010"));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null) {
            return ans;
        }
        int len = s.length();
        if (len < 4 || len > 12) {
            return ans;
        }

        helper(ans, s, new ArrayList<>(), 0, len);
        return ans;
    }

    private void helper(List<String> ans, String s, List<String> result, int index, int len) {
        if (index == len) {
            if (result.size() == 4) {
                ans.add(String.join(".", result));
            }
            return;
        }
        for (int i = index; i < index + 3; i++) {
            if (i >= len) {
                break;
            }
            int leftSegmentSize = 4 - result.size();
            if (leftSegmentSize * 3 < len - i) {
                continue;
            }
            String curSegment = s.substring(index, i + 1);

            if (legelIpSegement(curSegment)) {
                result.add(curSegment);
                helper(ans, s, result, i + 1, len);
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean legelIpSegement(String ip) {
        if (ip == null || ip.length() == 0) {
            return false;
        }
        int len = ip.length();
        if (len > 3) return false;
        // 是0的话，只有单独的0才是有效的片段
        return (ip.charAt(0) == '0') ? len == 1 : Integer.valueOf(ip) <= 255;
    }
}