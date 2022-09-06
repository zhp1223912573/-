package 二分;

/**
 * @author zhp
 * @date 2022-07-20 23:58
 * https://leetcode.cn/problems/powx-n/
 */
public class Pow_lc_50 {
    public double myPow(double x, int n) {
        long N = n;
        return n<0?1/quickPow(x,-n):quickPow(x,n);
    }

    /**
     * 快速幂
     *
     * @param base
     * @param n
     * @return
     */
    public  double quickPow(double base,long n){
        if(base==1||n==0) return 1;
        double ans = 1;
        while(n!=0){
            if((n&1)==1)
                ans *= base;
            base*=base;
            n>>=1;
        }
        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(quickPow(2,5));
    }
}
