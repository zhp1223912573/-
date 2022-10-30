package 栈和队列;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-10-26 12:23
 * https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/
 *
 */
public class 查找和最小的k个数字_lc_373 {
    /**
     * 多路归并问题
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(k,(a,b)->{
            return nums1[a[0]]+nums1[a[1]]-nums2[b[0]]-nums2[b[1]];
        });

        for(int i=0;i<Math.min(m,k);i++){
            pq.add(new int[]{i,0});
        }

        while(k-->0 && !pq.isEmpty()){
            int[] poll = pq.poll();
            int a = poll[0];
            int b = poll[1];
            List<Integer> index = new ArrayList<>();
            index.add(nums1[a]);
            index.add(nums2[b]);
            ans.add(index);
            if(b+1<n){
                pq.add(new int[]{a,b+1});
            }
        }
        return ans;
    }
}
