package 数组;

/**
 * @author zhp
 * @date 2022-07-15 19:38
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class 盛最多水的容器_lc_11 {
    /**
     * 暴力法，枚举所有可能，选择值最大的两边
     */
    public int maxArea(int[] height) {
        int max = 0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                max = Math.max(Math.min(height[i],height[j])*(j-i),max);
            }
        }
        return max;
    }

    /**
     * 数组.双指针
     * 本题是经典的双指针问题，但是一开始不一定能想到双指针方法解题。
     * 盛水的容量=左右边界的较小值*左右边界差值绝对值，
     *为了使盛水量尽可能大，需要的边界值较大，所以双指针指向数组首尾，
     * 计算盛水量，移动较小的那一个指针，因为取决于较小值，就算另一边更大，也受限与较小的那一条边。
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while(left<right){
            max = Math.max(Math.min(height[left],height[right])*(right-left),max);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }


}
