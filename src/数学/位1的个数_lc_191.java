package 数学;

/**
 * @author zhp
 * @date 2022-07-14 21:30
 * https://leetcode.cn/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode-solution-jnwf/
 *
 * 下述三种解法的解析：
 * https://www.cnblogs.com/maples7/archive/2015/05/02/4472208.html
 */
public class 位1的个数_lc_191 {
    /**
     * 循环与
     * @param n
     * @return
     */
    public int bitCount(int n){
        int ans = 0;
        for(int i=0;i<32;i++){
            if((n&1)==1){
                ans++;
            }
            n>>=1;
        }
        return ans;
    }

    /**
     * n&(n-1)
     *
     */
    public int bitCount1(int n){
        int ans = 0;
        while(n>0){
            n&=(n-1);
            ans++;
        }
        return ans;
    }

    public int bitCount3(int n){
        n = (n &0x55555555) + ((n >>1) &0x55555555) ;
        n = (n &0x33333333) + ((n >>2) &0x33333333) ;
        n = (n &0x0f0f0f0f) + ((n >>4) &0x0f0f0f0f) ;
        n = (n &0x00ff00ff) + ((n >>8) &0x00ff00ff) ;
        n = (n &0x0000ffff) + ((n >>16) &0x0000ffff) ;

        return n ;
    }

}
