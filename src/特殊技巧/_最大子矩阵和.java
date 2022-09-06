package 特殊技巧;


/**
 * @author zhp
 * @date 2022-07-06 15:12
 * 给定一个整型矩阵，返回子矩阵的最大累计和
 * 该问题是在一位数组求解最大累计和的扩展
 */
public class _最大子矩阵和 {
    /**
     * 求解最大子矩阵和
     * -10 1 5 3
     * -12 9 3 5
     * 8   4 -1 6
     * 求解0-0，0-1，0-n，1-1，1-2，1-n,2-2,2-n构成的矩阵和的最大值
     * @param m
     * @return
     */
    public static int maxSum(int[][] m ){
        if(m==null || m.length==0  || m[0].length==0){
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s;
        for(int i=0;i<m.length;i++){//开始前的行号
            s=new int[m[0].length];
            for(int j=i;j<m.length;j++){
                cur = 0;
                for(int k=0;k<s.length;k++){
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max,cur);
                    cur = cur<0? 0:cur;
                }
            }
        }
        return max;
    }
}
