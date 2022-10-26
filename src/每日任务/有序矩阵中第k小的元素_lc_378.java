package 每日任务;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhp
 * @date 2022-07-30 15:34
 * https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class 有序矩阵中第k小的元素_lc_378 {
    /**优先队列排序
     *
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int m = matrix.length;
        for(int i=0;i<m;i++){
            pq.add(new int[]{matrix[i][0],i,0});//把每行首个元素及其坐标放入
        }

        for(int i=0;i<k-1;i++){
            int[] poll = pq.poll();
            if(poll[2]!=m-1){
                pq.add(new int[]{matrix[poll[1]][poll[2]+1],poll[1],poll[2]+1});
            }
        }
        return pq.poll()[0];
    }

    /**双重二分法
     * 数组左上角为最小值，右下角为最大值。
     * 选定这两个值为左右边界，二分查找第k小的数值。
     *
     */
    public int kthSmallest1(int[][] matrix, int k){
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while(left<right){
            int mid = (right-left)>>1+left;
            if(check(matrix,n,k,mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * z形搜索，从左下角开始，统计大于mid的数量
     * @param matrix
     * @param n
     * @param k
     * @param mid
     * @return
     */
    private boolean check(int[][] matrix, int n, int k, int mid) {
        int i = n-1;
        int j = 0;
        int num =0;//找到小于mid的数值的数量
        while(i>=0&&j<n){
            if(matrix[i][j]<=mid){
                num += i+1;
                j++;
            }else{
                i--;
            }
        }
        return num>=k;
    }
}
