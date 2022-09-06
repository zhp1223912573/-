package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-15 21:59
 * https://leetcode.cn/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
 *
 */
public class 合并区间_lc_56 {
    /**
     * 首先我们将所有区间按照左端点进行排序，然后加入一个merged数组内，按顺序检查每个区间：
     * 1.如果当前检查的区间的左端点在merged最后一个区间的右端点后，那么这两个区间不重合，
     *   则当前区间可以作为新出现的区间加入merged
     * 2.反之，当前遍历的区间必定和merged内最后一个区间有重合，我们检查当前区间的右端点和merged数组
     *   最后一个区间的右端点，保存较大值。
     */
    public int[][] merge(int [][] intervals){
        if(intervals.length==0){
            return new int[0][2];
        }
        List<int []> merged = new ArrayList<>();

        Arrays.sort(intervals, new Comparator<int[]>() {
            //按照左端点从小到大排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });


        for(int i=0;i<intervals.length;i++){
            int L = intervals[i][0],R = intervals[i][1];
            if(merged.size()==0 || merged.get(merged.size()-1)[1]<L){
                merged.add(new int[] {L,R});
            }else{
                merged.get(merged.size()-1)[1]=Math.max(R,merged.get(merged.size()-1)[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
