package 每日任务;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-30 13:51
 *  https://leetcode.cn/problems/wiggle-sort-ii/
 */
public class 摆动排序II_lc_324 {

    /**排序
     * 对数组nums排序后，将数组划分为A，B两个前后数组，将A，B数组的元素交叉放置即可得到目标数组。
     * 例：{1，4，2，1，5，3} -》{1，1，2，3，4，5}
     *      {1，1，2} 与{3，4，5}
     *      {1，3，1，2，4，5}
     *  但如果数组中出现某个元素出现次数等于数组个数一般时，上述方法失效
     * 例：     {1，1，2，2，2，3}
     *      {1，1，2}与{2，2，3}
     *      {1，2，1，2，2，3}
     *  出现了相邻元素相等的情况，不符合题目要求。
     *  经过分析发现，出现上述情况的原因是一个元素出现次数超过一半，则分成两部分数组时，
     *  该数必然位于前数组的最后一位，以及后数组的第一位.
     *  我们可以逆序两数组，再交叉放置就可以得到目标数组。
     *      逆序后：{2，1，1}与{3，2，2}
     *      {2，3，1，2，1，2}
     *
     *
     * @param nums
     * @return
     */
    public void  wiggleMaxLength(int[] nums) {
        int arr [] = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n+1)/2;
        for(int i=0,j=x-1,y=n-1;i<n;i+=2,j--,y--){
            nums[i] = arr[j];
            if(i+1<n){
                nums[i+1] = arr[y];
            }
        }
    }
}
