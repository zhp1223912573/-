package 递归;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 *@date 2022-07-06 21:09
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class _最长递增子序列 {
    /**dp
     * dp[i]表示以字符arr[i]结尾的最长递增子序列长度
     *为了求解每一位的最长递增子序列长度，需要向前找到小于当前的arr[i]数值，且dp[x]最大的值。
     * 所以转台方程为
     * dp[i] = dp[x]+1,(arr[i]>arr[x],arr[x]是小于arr[i]的数中dp[i]最大的）
     *
     */
    public static int LengthOfLIS(int [] nums){
        if(nums ==null || nums.length<1){
            return 0;
        }
        int []dp = new int[nums.length];
        dp[0] = 1;
        int ans=1;
        for(int i=1;i<nums.length;i++){
            //以每个字符结尾时的最短递增序列就是他自己，必须预先设置，避免后序数字使用当前数字的dp位时出现错误
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    /**dp优化
     * 上述解法就是经典的dp，但是在判断最长递增序列得到过程中需要循环查找已经出现的数字，找到小于当前数值的数，使算法复杂度增加了。
     *
     * 我们可以通过构造一个单调递增的数组，其中包含了构成最长递增子序列的最小结尾数，这样就避免了循环查找的开销。
     * 这里引入一个新的数组end，end[i]表示长度为i的子序列的最小结尾值。
     *
     *
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                /*二分模板1，虽然该模板常用查找某个具体的数，但是像下述的步骤中添加一个遍历记录遍历过程的需要的值，也是可行的*/
//                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
//                while (l <= r) {
//                    int mid = (l + r) >> 1;
//                    if (d[mid] < nums[i]) {
//                        pos = mid;
//                        l = mid + 1;
//                    } else {
//                        r = mid - 1;
//                    }
//                }
//                d[pos + 1] = nums[i];
                int l = 1;
                int r = len;
                while (l < r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {

                        l = mid+1 ;
                    } else {
                        r = mid ;
                    }
                }
                d[l] = nums[i];
            }
        }
        return len;
    }

    /**
     *使用list维护一个递增子序列，将加入list，并始终保持递增，
     * 最后计算list个数，就是最长递增子序列的长度。
     */
    public int lengthOfLIS1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num:nums){
            int l=0;
            int r = list.size();
            while(l<r){
                int mid = (r-l)/2+l;
                if(list.get(mid)>=num){
                    r = mid;
                }else{
                    l = mid+1;
                }
            }
            if(r==list.size()) list.add(num);
            else list.set(r,num);
        }
        return list.size();
    }
}
