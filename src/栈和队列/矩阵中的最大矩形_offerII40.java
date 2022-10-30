package 栈和队列;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-10-19 11:15
 * http://bszb5511.com/?agent=1&app=lzgczljd
 * 本题和offerII39题思路相近，都是求最大矩形面积，下述解法中就是直接借鉴39题的单调栈解法
 */
public class 矩阵中的最大矩形_offerII40 {
    /**
     * 暴力法，直接循环查找最大的矩阵即可,时间复杂度高。
     * @param matrix
     * @return
     */
    public int maximalRectangle(String[] matrix) {
        return 1;
    }

    /**
     * 单调栈
     * 把矩阵的最大矩形看作一个旋转90度的直方图，我们想要得到最大矩阵
     * 首先获得每行的最长连续1的长度，以该长度作为直方图的高，
     * 题目就变成和39题一样，求解直方图最大矩形面积，
     * 接下来就可以以每个高为基准，找到该高和符合条件的底的最大长度，
     * 也就是列方向的单调栈问题。
     * 最后在寻找过程记录最大面积即可。
     */
    public int maximalRectangle1(String[] matrix){
            int m = matrix.length;
            int n = matrix[0].length();
            int left[][] = new int[m][n];
            int ret = 0;
            //计算连续1
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i].charAt(j)=='1'){
                        left[i][j]=(j==0?0:left[i][j-1])+1;
                    }
                }
            }

            //遍历每一列，单调栈求解每列中每行为高时，最长的底边

            for(int j=0;j<n;j++){
                int up[] = new int[m];
                int down[] = new int[m];
                Stack<Integer> stack = new Stack<>();

                //这里处理单调栈的方式和39题不一样，这里是保证栈中为小于当前要押入的数，弹出大于当前数的所有数
                for(int i=0;i<m;i++){
                    while(!stack.isEmpty()&&(left[stack.peek()][j])>=left[i][j]){
                        stack.pop();
                    }
                    up[i] = (stack.isEmpty()?-1:stack.peek());
                    stack.push(i);
                }

                stack.clear();
                for(int i=m-1;i>=0;i--){
                    while(!stack.isEmpty()&&(left[stack.peek()][j])>=left[i][j]){
                        stack.pop();
                    }
                    down[i] = (stack.isEmpty()?m:stack.peek());
                    stack.push(i);
                }
                for(int i=0;i<m;i++){
                    int height = down[i]-up[i]-1;
                    int area = height*left[i][j];
                    ret = Math.max(ret,area);
                }
            }

            return ret;
    }
}
