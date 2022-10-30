package 递归;

/**
 * @author zhp
 * @date 2022-10-28 13:30
 * https://leetcode.cn/problems/arithmetic-slices/submissions/
 */
public class 等差数列划分_lc_413 {
    /**
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if(n<=1) return 0;
        int ans = 0;
        int len = 0;
        int d = nums[0]-nums[1];
        for(int i=2;i<n;i++){
            if(nums[i-1]-nums[i]==d){
                len++;
            }else{
                len =0;
                d = nums[i-1]-nums[i];
            }
            ans += len;
        }
        return ans;
    }
}
