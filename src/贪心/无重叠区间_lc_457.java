package 贪心;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2023-04-15 14:19
 * https://leetcode.cn/problems/non-overlapping-intervals/submissions/
 */
public class 无重叠区间_lc_457 {
    /**
     * 重排数组，让右边界更小的在前面，
     * 尽可能使不重叠的区间更大，最后使用区间总数减去不重叠的最大区间数即可。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int cnt = 1;
        Arrays.sort(intervals,(a, b)->a[1]-b[1]);
        int pre = intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]>=pre){
                cnt++;
                pre = intervals[i][1];
            }
        }
        return intervals.length-cnt;
    }

}
