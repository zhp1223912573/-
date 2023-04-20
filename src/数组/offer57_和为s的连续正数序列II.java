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
        List<int[]> vec = new ArrayList<int[]>();
        for (int l = 1, r = 2; l < r;) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }
}
