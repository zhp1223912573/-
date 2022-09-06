package 数组;

/**
 * @author zhp
 * @date 2022-07-15 23:08
 *
 * https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
 *
 */
public class 旋转图像_lc_48 {
    /**
     * 看看官方详细解答吧
     * https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     */

    /**
     * 列举矩阵反转后，发现以下规律：
     * row = col
     * col = n-row-1
     * 左侧为矩阵中某位置初始坐标，右侧为旋转90度后的坐标位置，
     * 根据上述规律可以退出每次旋转的四个位置的下一位置坐标，因为覆盖的时候会使原数据丢失，
     * 所以我们可以使用temp临时遍历保存
     * 推导可得：
     * row = n-row-1
     * col = n-col-1
     *
     * row = n-col-1
     * col = row
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<(n/2);i++){
            for(int j=0;j<(n+1)/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

    /**
     * 将矩阵水平反转，再沿对角线反转，就能实现图像的90度旋转
     * @param matrix
     */
    public void rotate1(int[][] matrix){
        int n = matrix.length;
        for(int i=0;i<(n/2);i++){
            for(int j=0;j<n;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-i-1][j];
                matrix[n-i-1][j] = temp;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
