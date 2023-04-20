package 数组;

import jdk.nashorn.internal.ir.LiteralNode;
import shiyan.In;

import java.lang.instrument.ClassDefinition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-16 12:54
 * https://leetcode.cn/problems/diagonal-traverse/
 */
public class 对角线遍历_lc_498 {
    /**规律查找
     * https://leetcode.cn/problems/diagonal-traverse/solution/dui-jiao-xian-bian-li-fen-xi-ti-mu-zhao-zhun-gui-l/
     */
    public static int[]  findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        if(m==0){
            return  null;
        }
        int n = mat[0].length;
        if(n==0){
            return null;
        }

        int ans [] = new int[m*n];
        int count =0;
        boolean flag = true;//运动方向的鉴别
        //i是x+y的值，通过我们发现的规律，一个矩阵运动的斜线数量等于m+n
        for(int i=0;i<m+n;i++){
            //每个i代表这着一条斜线的运动，
            // 为了区别上下运动方向，我们需要flag来标识，同时也需要他在斜线运动完成之后转换方向

            //上限值，根据运动的方向改变
            int pm = flag ? m : n;
            int pn = flag ? n : m;
            //如果x大于上限值，则令x等于上限值-1，同时y为i-x，保证i为x+y之和
            int x = (i<pm)?i : pm-1;
            int y = i-x;

            while(x>=0&&y<pn){
                ans[count++] = flag? mat[x][y]:mat[y][x];
                x--;
                y++;
            }

            flag = !flag;//反转运动方向
        }
        return ans;
    }

    /**
     * 换一种思路，我们可以将以第一行和最后一列作为开头的所有斜线保存起来，
     * 最后根据运动方向，将保存的斜线以正序或者逆序读入数组，即可以得到正确答案，
     * 这个方法虽然需要额外的空间，但是不需要考虑边界问题的处理。
     * @param mat
     * @return
     */
    public static int[]  findDiagonalOrder1(int[][] mat){
       List<List<Integer>> allList = new ArrayList<>();
       int m = mat.length;
       int n = mat[0].length;

       //第一行的所有斜线
       for(int j=0;j<n;j++){
           int row = 0;
           int col = j;
           List<Integer> list = new ArrayList<>();
           while(row<=m-1&&col>=0){
               list.add(mat[row][col]);
               row++;
               col--;
           }
           allList.add(list);
       }

       //倒数第一列的所有斜线
        for(int i=1;i<m;i++){
            int row = i;
            int col = n-1;
            List<Integer> list = new ArrayList<>();
            while(row<=m-1&&col>=0){
                list.add(mat[row][col]);
                row++;
                col--;
            }
            allList.add(list);
        }
        boolean flag = false;
       int ans[] = new int[m*n];
       int count = 0;
        for(List<Integer> list :allList){
            if(flag){
                for(int i=0;i<list.size();i++){
                    ans[count++] = list.get(i);
                }
            } else{
                for(int i=list.size()-1;i>=0;i--){
                    ans[count++] = list.get(i);
                }
            }
            flag = !flag;
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/diagonal-traverse/solution/dui-jiao-xian-bian-li-by-leetcode-soluti-plz7/
     * @param mat
     * @return
     */
    public int[] findDiagonalOrder2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int ans[] = new int [n*m];
        int index = 0;
        for(int i=0;i<m+n-1;i++){
            if(i%2==1){
                int x = i<n? 0:i-n+1;
                int y = i<n? i:n-1;
                while(x<m&&y>=0){
                    ans[index++] = mat[x][y];
                    x++;
                    y--;
                }

            }else{
                int x = i<m? i : m-1;
                int y = i<m? 0: i-m+1;
                while (x >= 0 && y < n) {
                    ans[index++] = mat[x][y];
                    x--;
                    y++;
                }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int mat [][] = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(findDiagonalOrder(mat));
    }
}
