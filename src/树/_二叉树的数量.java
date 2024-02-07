package 树;

/**
 * @author zhp
 * @date 2022-07-03 11:24
 * 给定n个树节点，判断总共有几种二叉树构成方式
 */
public class _二叉树的数量 {
    /**
     * 从普通情况进行分析：
     * 0个节点，1种树
     * 1个节点，1种树
     * 2个节点，2种树
     * 3个节点，5种树
     * 。。。。。。。
     * n个节点，分成一个根据点，左子树有i个节点，右子树右n-i-1个节点，
     * 穷举i的所有可能，就可以得到n个节点时，二叉树的种类
     */
    public static int process(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int res = 0;
        for (int leftTreenum = 0; leftTreenum <= n - 1; leftTreenum++) {
            int leftTreeType = process(leftTreenum);//左子树的种类
            int rightTreeType = process(n - leftTreenum - 1);//右子树的种类
            res += leftTreeType * rightTreeType;
        }

        return res;
    }

    /**
     * 转化为dp
     * dp[]数值表示当前节点数为i的二叉树种类有dp[i]种
     */
    public static int processBydp(int n) {
        if (n < 0) return 0;

        int dp[] = new int[n + 1];
        dp[0] = 1;//必须初始化节点数为0的情况
        for (int i = 1; i < n + 1; i++) {//节点数递增求解
            for (int j = 0; j < i; j++) {//左子树为j个，右子树为i-j-1
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("常规方法：" + process(i));
            System.out.println("dp方法：" + processBydp(i));

        }
    }
}
