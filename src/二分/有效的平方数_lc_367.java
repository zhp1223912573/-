package 二分;

/**
 * @author zhp
 * @date 2022-07-21 0:24
 * https://leetcode.cn/problems/valid-perfect-square/submissions/
 */
public class 有效的平方数_lc_367 {
    /**
     * 二分
     * 关键点在于细节的把握，int型值求解平方和时容易数据溢出，需要转为为其他类型
     */
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while(left<=right){
            int mid = (right-left)/2+left;
            long test = (long)mid*mid;
            if(test==num){
                return true;
            }else if(test<num){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return false;
    }
    /**
     * 1 4=1+3 9=1+3+5 16=1+3+5+7以此类推，模仿它可以使用一个while循环，
     * 不断减去一个从1开始不断增大的奇数，若最终减成了0，说明是完全平方数，否则，不是。
     *
     * 作者：lu-guo-de-feng-2
     * 链接：https://leetcode.cn/problems/valid-perfect-square/solution/zhi-xing-yong-shi-0-ms-zai-suo-you-c-ti-jiao-zh-44/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isPerfectSquare1(int num) {
        int num1 = 1;
        while(num > 0)
        {
            num -= num1;
            num1 += 2;
        }
        return num == 0;
    }

    //下述两个方法参考官方解答

    /**
     * 暴力
     */

    /**
     * 牛顿迭代
     */
}
