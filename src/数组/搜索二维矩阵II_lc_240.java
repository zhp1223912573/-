package 数组;


import sun.util.resources.cldr.yav.CalendarData_yav_CM;

import java.util.ArrayList;

/**
 * @author zhp
 * @date 2022-07-26 0:49
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 */
public class 搜索二维矩阵II_lc_240 {
    /**缩小范围
     * 与搜索二维矩阵I_lc_74的缩小范围解法一致
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n-1;

        while(row<m&&col>=0){
            int num = matrix[row][col];
            if(num==target) return true;
            else if(num>target){
                col--;
            }else if(num<target){
                row++;
            }
        }
        return false;
    }

    /**每行二分
     * 由于该题与搜索二维矩阵I_lc_74的题目条件存在不一致地方，不能使用那道题的两次二分和一次二分的思路，
     * 但是此题也可以使用二分，既然每行都有序，那就对每行进行二分查找。
     */
    public boolean searchMatrix1(int[][] matrix, int target){
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i=0;i<m;i++){
            boolean isExist =  binarySearch(matrix[i],target);
            if(isExist) return true;
        }
        return false;

    }

    private boolean binarySearch(int[] matrix, int target) {
        int left = 0;
        int right = matrix.length;
        while(left<=right){
            int mid = (right-left)/2+left;
            if(matrix[mid]==target){
                return true;
            }else if(matrix[mid]<target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return false;
    }

    /**行列二分
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray1(int[][] matrix, int target){
        return binarySearch(matrix,0,matrix[0].length-1,0,matrix.length-1,target);
    }
    private boolean binarySearch(int[][] matrix, int x1,int x2,int y1,int y2,int target){
        if(x2<x1||y2<y1) return false;
        int mid1 = (x2-x1)/2+x1;
        int mid2 = (y2-y1)/2+y1;
        int cur = matrix[mid1][mid2];
        if(cur==target) return true;
        else if(cur>target){
            return binarySearch(matrix,x1,x2,y1,mid2,target)||binarySearch(matrix, x1,mid1, y1,mid2,target);
        }else{
            return binarySearch(matrix,x1,x2,mid2+1,y2,target)||binarySearch(matrix,mid1+1,x2,mid2+1,y2,target);
        }
    }



    /**线性探测
     * 从矩阵左下角起步，target大于当前坐标值，向上移动；target小于当前坐标值，向右移动；等于则返回
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        int x = m-1,y=0;
        while(x>=0&&y<matrix[0].length){
            if(matrix[x][y]==target) return true;
            else if(matrix[x][y]<target) y++;
            else if(matrix[x][y]>target) x--;
        }
        return false;
    }
}
