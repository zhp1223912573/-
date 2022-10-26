package 每日任务;

/**
 * @author zhp
 * @date 2022-10-24 12:45
 * https://leetcode.cn/problems/magic-index-lcci/
 */
public class ms08_03_魔术索引 {
    /**
     * 跳跃性优化
     * 顺寻遍历，如果当前数等于下标，直接返回。
     * 如果不等于，进行跳跃。
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        int index = 0;
        while(index<nums.length){
            if(index==nums[index]) return index;
            index = Math.max(index+1,nums[index]);
        }
        return -1;
    }

    /**
     *分治
     * @param nums
     * @return
     */
    public int findMagicIndex1(int[] nums) {
        return divide(nums,0,nums.length-1);
    }

    int divide (int[] nums,int left,int right){
        if(left>right){
            return -1;
        }

        int mid = (right-left)/2+left;
        int l = divide(nums,left,mid-1);
        if(l!=-1){
            return l;
        }else if(nums[mid]==mid){
            return mid;
        }

        return divide(nums,mid+1,right);
    }
}
