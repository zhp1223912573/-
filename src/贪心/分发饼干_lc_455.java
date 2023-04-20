package 贪心;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2023-04-15 14:21
 * https://leetcode.cn/problems/assign-cookies/submissions/
 */
public class 分发饼干_lc_455 {
    /**
     * 重排序饼干和每个孩子的胃口，尽可能的让胃口最小的孩子吃上饼干
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        for(int kid=0,biscuit=0;biscuit<s.length;biscuit++){
            if(s[biscuit]>=g[kid]) {
                kid++;
                ans++;
            }
            if(kid>=g.length) break;
        }
        return ans;
    }
}
