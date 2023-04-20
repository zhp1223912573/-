package 二分;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-21 16:06
 * https://leetcode.cn/problems/split-array-largest-sum/submissions/
 *
 *
 */
public class 分割数组的最大值_lc_410 {
    /**dp
     * 将数组分割为多个段...是动态规划的套路
     * 定义：
     *      dp[i][j]为数组0-i的位置上切割为j段的所有分组的最大值的最小值
     * 可能性分析:
     *        我们假定小于的i位置k可以分为j-1段，那么dp[i][j]等于max(dp[k][j-1],sub(k+1,i))
     *        sublist(k+1,i),表示k+1位置到i的前缀和，
     * 初始情况:
     *      dp[0][0]=0,
     *则dp[n][m]就是目标值
     *
     * 官方解答
     * https://leetcode.cn/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
     * @param nums
     * @param m
     * @return
     */
    public static int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }


    /**贪心+二分
     * https://leetcode.cn/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
     *
     */
    public static int splitArray1(int[] nums, int m){

        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            right += nums[i];
            if (left < nums[i]) {
                left = nums[i];
            }
        }
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[] nums, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > x) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return cnt <= m;
    }

    /**
     * 二分
     * @param nums
     * @param k
     * @return
     */
    public int splitArray2(int[] nums, int k) {
        int l =0,r=(int)1e9;
        while(l<r){
            int mid = (r-l)/2+l;
            if(check1(nums,k,mid)) r = mid;
            else l=mid+1;
        }
        return r;
    }
    boolean check1(int nums[],int k,int mid){
        int cnt = 1;
        int cur = 0;
        for(int num:nums){
            if(num+cur<=mid){
                cur+=num;
            }else{
                if(num>mid) return false;
                cnt++;
                cur = num;
            }
        }
        return cnt<=k;

    }

    public static void main(String[] args) {
        int arr[] ={7,2,5,10,8};
        System.out.println(splitArray(arr,2));
    }
}
