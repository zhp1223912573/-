package 数组;

/**
 * @author zhp
 * @date 2023-03-26 22:38
 *
 */
public class leetcode_1208尽可能使字符串相等 {
    /**
     * 滑动窗口
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int left =0;
        int right =0;
        int n=s.length();
        int ans = 0;
        int cost = 0;
        while(right<n){
            cost += Math.abs(s.charAt(right)-t.charAt(right));

            while(cost>maxCost){
                cost -= Math.abs(s.charAt(left)-t.charAt(left));
                left++;
            }

            ans = Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }

    public int equalSubstring1(String s, String t, int maxCost) {
        int ans = 0;
        int cost = 0;
        for(int l=0,r=0;r<s.length();r++){
            cost+=Math.abs(s.charAt(r)-t.charAt(r));
            while(cost>maxCost){
                cost-=Math.abs(s.charAt(l)-t.charAt(l));
                l++;
            }
            ans = Math.max(ans,r-l+1);
        }
        return ans;
    }
}
