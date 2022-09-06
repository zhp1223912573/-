package 二分;

import com.sun.scenario.effect.Brightpass;

/**
 * @author zhp
 * @date 2022-07-27 1:22
 * https://leetcode.cn/problems/divide-two-integers/submissions/
 */
public class 两数相除_lc_29 {
    /**二分+快加
     *  答题的解题思路：通过二分查找，找到与除数相乘后尽可能接近被除数的数值，则该数值就是我们要得到商，
     *                  但是题目要求不能使用乘法运算，我们就使用与快速幂思想一致的快速加来实现乘的近似操作。
     * 此解答的解题思路与官解相近，但是通过对被除数与除数的类型转换（向上转型），
     * 避免了很多边界情况的讨论，我们只需吧相除的正负性记录下来，接着把除数与被除数都转化为正数进行正常运算即可。
     *
     * 宫水三叶：https://leetcode.cn/problems/divide-two-integers/solution/shua-chuan-lc-er-fen-bei-zeng-cheng-fa-j-m73b/
     *
     */
    public int divide(int a, int b) {
        //类型转换保持，避免众多溢出的情况
        long x = a;
        long y = b;
        //结果的正负性
        boolean isNeg = false;
        if((x<0&&y>0)||(x>0&&y<0)) isNeg = true;
        //将数值都转化为正数
        if(x<0) x = -x;
        if(y<0) y = -y;

        long l = 0;
        long r = x;
        while(l<r){
            long mid = (r-l+1)/2+l;
            if(mul(mid,y)<=x){
                l = mid;
            }else{
                r = mid-1;
            }
        }
        long ans = isNeg?-l:l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) return Integer.MAX_VALUE;

        return (int)ans;

    }

    private long mul(long mid, long y) {
        long ans = 0;
        while(y!=0){
            if((y&1)==1){
                ans +=mid;
            }
            mid += mid;
            y>>=1;
        }
        return ans;

    }


    /**
     * 官方题解，针对边界情况进行了分析，并对题目解题思路有清楚的讲解，可以仔细看看。
     */
}
