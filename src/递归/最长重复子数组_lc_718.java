package 递归;

/**
 * @author zhp
 * @date 2023-04-02 22:44
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/submissions/
 */
public class 最长重复子数组_lc_718 {
    /**
     * 设定f(i,j)表示以nums1[i]，以及nums2[j]字符结尾时的最长子字符数组
     * 则f(i,j)=
     * 1.f(i-1,j-1)+1 (nums1[i]=nums2[j])
     * 2.0
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        //将数组设置的更大一些，dp【0】【j】表示nums1没有字符时的长度，可以省去不必要的初始化
        int dp[][] = new int[len1+1][len2+1];
        int ans = 0 ;
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                    ans = Math.max(dp[i][j],ans);
                }
            }
        }
        return ans;
    }
}
