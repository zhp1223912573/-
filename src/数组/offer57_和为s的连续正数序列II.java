package 数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-10-14 11:00
 * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/solution/jian-zhi-offer-57-ii-he-wei-s-de-lian-xu-t85z/
 */
public class offer57_和为s的连续正数序列II {
    /**
     * 数学推导，设定i和j，i为连续正数序列的左边界，j为连续正数序列的右边界
     * 根据求和公式得到j的求解公式，循环查找即可。
     */
    class Solution {
        public int[][] findContinuousSequence(int target) {
            int i = 1;
            double j = 2.0;
            List<int[]> res = new ArrayList<>();
            while(i < j) {
                j = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) i * i - i))) / 2;
                if(i < j && j == (int)j) {
                    int[] ans = new int[(int)j - i + 1];
                    for(int k = i; k <= (int)j; k++)
                        ans[k - i] = k;
                    res.add(ans);
                }
                i++;
            }
            return res.toArray(new int[0][]);
        }
    }


    /**滑动窗口
     *
     * 双指针限定窗口，窗口总和为sum
     * sum等于target，记录范围数组；
     * sum小于target，移动右指针，扩大sum；
     * sum小于targte，移动左指针，缩小sum；
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int i =1;
        int j = 1;
        int sum = 0;
        List<int[]> ans = new ArrayList<>();
        while(i<=target/2){
            if(sum==target){
                int res[] = new int[j-i];
                for(int k =i;k<j;k++){
                    res[k-i] = k;
                }
                ans.add(res);
                sum-=i;
                i++;
            }else if(sum<target){
                sum+=j;
                j++;
            }else{
                sum-=i;
                i++;
            }
        }
        return ans.toArray(new int[0][]);
    }
}
