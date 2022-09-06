package 回溯;

/**
 * @author zhp
 * @date 2022-06-13 13:46
 * 经典的n皇后问题--回溯算法
 * 在一个n*n的棋盘上摆放n个皇后，保证所有皇后不同行，不同列，不在同一斜线上
 * 计算出所有摆放方法
 *
 *
 */
public class n皇后 {

    /**
     * 回溯函数：
     * 在每行上尝试在不同列位置上摆放皇后，如果当前已经摆放的皇后符合要求就继续向下一行摆放，
     * 不符合要求就重新挑选一个摆放在当前行，直到全部摆放完成
     * @param i 当前摆放的行
     * @param record  记录已经摆放好的情况
     * @param n 总共要摆放的皇后数量
     * @return 符合摆放要求的方法数量
     */
    public static int process1(int i,int[] record,int n){
        if(i==n){//全部摆好了，返回当前这种情况的数量
            return 1;
        }

        int res = 0;
        for(int j=0;j<n;j++){//尝试在当前行的所有列摆放皇后
            if(isValid(i,j,record)){//判断是否符合要求
                record[i] = j;
                res += process1(i+1,record,n);
            }
        }

        return res;
    }

    /**
     * 判断当前摆放是否符合要求
     * @param i 当前摆放的行
     * @param j 当前行的皇后摆放的列
     * @param record 已经摆放好的记录
     * @return 是否符合摆放要求
     */
    private static boolean isValid(int i, int j, int[] record) {
        for(int k=0;k<i;k++){
            //判断是否出现同列 或者 在同一斜线的情况
            if(record[k]==j || Math.abs(record[k] - j ) ==Math.abs(i-k)){
                return false;
            }
        }
        return true;
    }

    /**
     *方法1：经典的回溯算法
     * @param n
     * @return
     */
    public static int num1(int n){


        int record [] = new int[n];
        return process1(0,record,n);
    }

    /**
     * 方法2：利用位运算的特性取代record数组的遍历
     * @param n
     * @return
     */
    public static int num2(int n){
        if(n<1 || n>32){//限制小于32
            return 0;
        }

        int limit = n == 32?-1:(1<<n)-1;//获取所有限制位
        return process2(limit,0,0,0);
    }

    /**
     *
     * @param limit 整个棋盘的限制位，有多少个皇后就有多少位，递归过程中不改变
     * @param collimit 行限制位，为1的位置不能摆放棋子，为0的位置可以摆放棋子
     * @param leftlimit 左斜线限制位，同上
     * @param rightlimit 右斜线限制位，同上
     * @return
     */
    private static int process2(int limit, int collimit, int leftlimit, int rightlimit) {
        if(collimit == limit){//行限制位等于全部限制位，说明所有皇后已经摆放完了
            return 1;
        }
        int res = 0;//总数

        //获取当前所有可以摆放棋子的列位置
        int pos = limit & (~(collimit | leftlimit | rightlimit));
        while(pos!=0){//尝试在所有可以摆放棋子的列
            //可以摆放棋子的最右列
            int mostRightpos = pos & (~pos + 1);//小技巧（一个数与上自己取反加一的数可以得到最右位）
            pos -= mostRightpos;//放在当前位置上
            res+= process2(limit,
                    collimit | mostRightpos,
                    (leftlimit | mostRightpos) <<1,
                    (rightlimit | mostRightpos)>>1);
        }
        return res;
    }


    public static void main(String[] args) {
        int  n =15;


        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("计算时间"+(end- start)+"ms");


         start = System.currentTimeMillis();

        System.out.println(num1(n));
         end = System.currentTimeMillis();
        System.out.println("计算时间"+(end- start)+"ms");

    }
}


