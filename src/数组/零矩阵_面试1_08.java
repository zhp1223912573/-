package 数组;

/**
 * @author zhp
 * @date 2022-07-16 11:54
 * https://leetcode.cn/problems/zero-matrix-lcci/solution/
 */
public class 零矩阵_面试1_08 {
    /**
     * 该问题的关键在于如何使用更少的空间，时间复杂度始终取决于矩阵的规模，O(nm),
     *
     */

    /**
     * 辅组数组
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row [] = new boolean[m];
        boolean col [] = new boolean[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    row[i] = col[j] = true;
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(row[i] || col[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 使用matrix的第一行和第一列作为辅组数组，需要两个变量记录第一行第一列是否包含0
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
         boolean flagcol0  = false;
         boolean flagrow0  = false;

         for(int i=0;i<m;i++){
             if(matrix[i][0]==0){
                 flagcol0 = true;
             }
         }

         for(int j=0;j<n;j++){
             if(matrix[0][j]==0){
                 flagrow0 = true;
             }
         }

         for(int i=1;i<m;i++){
             for(int j=1;j<n;j++){
                 if(matrix[i][j]==0){
                     matrix[i][0] = matrix[0][j] = 0;
                 }
             }
         }

         for(int i=1;i<m;i++){
             for(int j=1;j<n;j++){
                 if(matrix[i][0]==0 || matrix[0][j]==0){
                     matrix[i][j] = 0;
                 }
             }
         }

         if(flagcol0){
             for(int i=0;i<m;i++){
                 matrix[i][0] = 0;
             }
         }

        if(flagrow0){
            for(int i=0;i<n;i++){
                matrix[0][i] = 0;
            }
        }


    }
}
