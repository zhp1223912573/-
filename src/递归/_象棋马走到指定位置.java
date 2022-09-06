package 递归;

/**
 * @author zhp
 * @date 2022-06-16 16:11
 * 在一个9*10的象棋棋盘上指定一个目标位置（x,y),
 * 计算马从起始位置（0，0）在限定步数step内走到目标位置的方法数。
 */
public class _象棋马走到指定位置 {

    /**递归思路
     *
     * @param x
     * @param y
     * @param step
     * @return
     */
    public static int getWays(int x,int y,int step){
        if(x<0|| x>8 || y<0 || y>9 ){//超出棋盘范围
            return 0;
        }

        if(step==0){
            //步数走完时,是否走到了终点（0，0）
            return (x==0 && y==0) ? 1:0;
        }

        //计算马走日的8种走法
        return getWays(x+1,y+2,step-1)
                +getWays(x-1,y+2 ,step-1 )
                +getWays(x-2,y+1 ,step-1 )
                +getWays(x-2,y-1 ,step-1 )
                +getWays(x-1,y-2 ,step-1 )
                +getWays(x+1,y-2 ,step-1 )
                +getWays(x+2,y-1 ,step-1 )
                +getWays(x+2,y+1 ,step-1 );
    }

    /**dp
     *
     * 将递归过程理解为一个三位的数组
     * 长为x，宽为y，高为step；
     * 每当走完当前这一步，step就要--，来到下一层。
     * 并且，每层的遍历顺序不重要，因为能影响到当前位置的只有当前层step的下一层step-1
     *
     * 递归方程：
     * dp[x][y][step]=dp[a][b][step-1]
     * a,
     *
     * 我们的目标位置为（0，0，0），所以需要从最顶层向上递推，并且初始位置必须为1，保存其他位置才能增加
     * 初始条件：
     * dp[0][0][0] = 1
     *
     *
     * @param x
     * @param y
     * @param step
     * @return
     */
    public static int dpway(int x,int y,int step){
        if(x<0|| x>8 || y<0 || y>9 || step<0){//超出棋盘范围
            return 0;
        }
        int dp[][][] = new int[9][10][step+1];
        dp[0][0][0] = 1;

        for(int ceng = 1;ceng<=step;ceng++){
            for(int r=0;r<9;r++){
                for(int c=0;c<10;c++){
                    dp[r][c][ceng] += getValue(dp,r+1,c+2,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r-1,c+2 ,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r-2,c+1 ,ceng-1 );
                    dp[r][c][ceng] += getValue(dp,r-2,c-1 ,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r-1,c-2 ,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r+1,c-2 ,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r+2,c-1 ,ceng-1);
                    dp[r][c][ceng] += getValue(dp,r+2,c+1 ,ceng-1);
                }
            }
        }

        return dp[x][y][step];

    }

    /**保存获取dp数组时不会越界
     *
     * @param dp
     * @param row
     * @param col
     * @param height
     * @return
     */
    public static int getValue(int dp[][][],int row,int col,int height){
        if(row<0 || row>8 || col<0 || col>9){
            return 0;
        }
        return dp[row][col][height];
    }
    public static void main(String[] args) {
        System.out.println(getWays(7,4,5));
        System.out.println(dpway(7,4,5));

    }
}
