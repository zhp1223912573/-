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

    /**位运算
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     *
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     *
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     *
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     *
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     *
     * 我们可以以100/3为例
     *
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     *
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     *
     * 所以一共是减去了33个3，所以商就是33
     *
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     *
     */
    public int divide1(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) <0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d= Math.abs((long) divisor);
        int result = 0;
        for (int i=31; i>=0;i--) {
            if ((t>>i)>=d) {//找出足够大的数2^n*divisor
                result+=1<<i;//将结果加上2^n
                t-=d<<i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    /**
     * 官方题解，针对边界情况进行了分析，并对题目解题思路有清楚的讲解，可以仔细看看。
     */
}
