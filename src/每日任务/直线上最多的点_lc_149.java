package 每日任务;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-31 16:18
 * https://leetcode.cn/problems/max-points-on-a-line/
 */
public class 直线上最多的点_lc_149 {
    /**
     * 计算每两个点之间的斜率（使用字符串来表示分数，并且需要约分，从而保证一致性）
     * 通过map保存斜率，得到最大斜率总和数+1就是直线上最多的点
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int m = points.length;
        int ans = 0;
        for(int i=0;i<m;i++){
            //记录当前点i出发的所有线的斜率及其出现次数
            Map<String,Integer> map = new HashMap<>();
            int max = 0;
            for(int j=i+1;j<m;j++){
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                int a = x1-x2;
                int b = y1-y2;
                int k = gcb(a,b);
                String key = (a/k)+"/"+(b/k);
                map.put(key,map.getOrDefault(key,0)+1);
                max = Math.max(max,map.get(key));
            }
            ans = Math.max(ans,max+1);
        }
        return ans;

    }

    private int gcb(int a, int b) {
        return b==0?a : gcb(b,a%b);
    }

}
