package 数组;

import java.security.interfaces.DSAParams;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-27 21:15
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class 螺旋矩阵_lc_59 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int up =0,right = n-1,left = 0,down=m-1;
        List<Integer> ans = new ArrayList<>();
        while(true){
            for(int i=left;i<=right;i++) ans.add(matrix[up][i]);
            if(++up>down) break;
            for(int i=up;i<=down;i++) ans.add(matrix[i][right]);
            if(--right<left) break;
            for(int i=right;i>=left;i--) ans.add(matrix[down][i]);
            if(--down<up) break;
            for(int i=down;i>=up;i--) ans.add(matrix[i][left]);
            if(++left>right) break;
        }
        return ans;
    }
}
