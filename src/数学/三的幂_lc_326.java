package 数学;

/**
 * @author zhp
 * @date 2022-07-14 20:45
 * https://leetcode.cn/problems/power-of-three/
 */
public class 三的幂_lc_326 {
    /**
     * 使用快速幂的思想解题
     */
    public static int quick(int base,int n){
        int ans=1;
        while(n>0){
            if((n&1)==1){
                ans*=base;
            }
            base*=base;
            n>>=1;
        }
        return ans;
    }

    /**
     * 试除法
     * 一直除3，除不尽就不对
     */
    boolean isPowerOfThree(int n) {
        while (n!=0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }


    public static void main(String[] args) {
        for(int i=1;i<20;i++){
            System.out.println(quick(3,i));
        }

        System.out.println( quick(3, -1));
    }

}
