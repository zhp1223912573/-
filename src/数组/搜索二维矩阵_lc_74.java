package 数组;

/**
 * @author zhp
 * @date 2022-07-26 0:02
 * https://leetcode.cn/problems/search-a-2d-matrix/submissions/
 */
public class 搜索二维矩阵_lc_74 {
    /**
     * 范围缩小
     * 利用矩阵从左往右递增，从上往下递增的性质，比较当前遍历到的数值与target，
     * 移动坐标。
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


    /**两次二分
     *利用行列递增特性，先二分查找第一列最后一个不大于target的值，再在该行二分查找target值。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int row = binarySearchFirstCol(matrix,target);
        if(row<0||row>=m) return false;

        return  binarySearchRow(matrix[row],target);


    }
    private boolean   binarySearchRow(int[] matrix, int target) {
        int left = 0;
        int right = matrix.length-1;
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
    private int binarySearchFirstCol(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length-1;
        int ans = -1;
        while(left<=right){
            int mid = (right-left)/2+left;

            if(matrix[mid][0]<=target){
                ans = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    /**一次二分
     *整个矩阵按从左往右，从上到下的顺序是递增的，我们将二维坐标转化为一维坐标，再进行二分即可。
     */
    public boolean searchMatrix2(int[][] matrix, int target){
            int m = matrix.length;
            int n = matrix[0].length;

            int low = 0;
            int high = m*n-1;
            while(low<=high){
                int mid = (high-low)/2+low;
                int cur =  matrix[mid/n][mid%n];
                if(cur==target){
                    return true;
                }else if(cur<target){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
            return false;
    }



    public static void main(String[] args) {
        搜索二维矩阵_lc_74 t = new 搜索二维矩阵_lc_74();
        int matrix[][]={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        boolean b = t.searchMatrix1(matrix, 3);
        System.out.println(b);
    }
}
