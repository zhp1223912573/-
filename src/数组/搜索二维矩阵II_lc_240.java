package 数组;


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

    /**二分
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

}
