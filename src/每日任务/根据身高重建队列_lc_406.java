package 每日任务;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-31 22:26
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 */
public class 根据身高重建队列_lc_406 {
    /**
     * 按身高从高到矮排序
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o2[0]-o1[0];
                }else{
                    return o1[1]-o2[1];
                }
            }
        });

        List<int []> ans  = new ArrayList<>();
        for(int [] person : people){
                ans.add(person[1],person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
