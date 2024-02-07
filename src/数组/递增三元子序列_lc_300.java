package 数组;


/**
 * @author zhp
 * @date 2022-07-24 19:59
 * https://leetcode.cn/problems/increasing-triplet-subsequence/solution/di-zeng-de-san-yuan-zi-xu-lie-by-leetcod-dp2r/
 */
public class 递增三元子序列_lc_300 {
    /**双向遍历
     * 判断数组是否存在三元组子序列使得a<b<c,假定存在数值num[i]位中心时的三元子序列，
     * 那么i位置左侧的最小元素一定小于num[i],同理，i位置右侧的最大元素大于num[i]
     * 则设定两个数组保存左侧最小值和右侧最大值
     */
    public boolean increasingTriplet(int[] nums) {
            int n = nums.length;
            int min[] = new int[n];
            int max[] = new int[n];
            int num = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                num = Math.min(num,nums[i]);
                min[i] = num;
            }

            num = Integer.MIN_VALUE;
            for(int i=n-1;i>=0;i--){
                num = Math.max(num,nums[i]);
                max[i]=num;
            }

            for(int i=1;i<n-1;i++){
                if(min[i-1]<nums[i]&&max[i+1]>nums[i]){
                    return true;
                }
            }

            return false;
    }

    /**贪心
     * 第二种方法用人话说就是：
     * 赋初始值的时候，已经满足second > first了，现在找第三个数third
     * (1) 如果third比second大，那就是找到了，直接返回true
     * (2) 如果third比second小，但是比first大，那就把second的值设为third，然后继续遍历找third
     * (3) 如果third比first还小，那就把first的值设为third，然后继续遍历找third
     * （这样的话first会跑到second的后边，但是不要紧，因为在second的前边，老first还是满足的
     */
    public boolean increasingTriplet1(int[] nums) {

        if(nums==null||nums.length==0){
            return false;
        }

        int first = nums[0];
        int second = Integer.MAX_VALUE;
        for(int i=1;i<nums.length-1;i++){
            if(nums[i]>second){
                return true;
            }
            else if(nums[i]>first){
                second = nums[i];
            }
            else {
                first = nums[i];
            }
        }
        return false;
    }


}
