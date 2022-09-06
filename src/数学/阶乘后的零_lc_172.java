package 数学;

/**
 * @author zhp
 * @date 2022-07-26 23:58
 * https://leetcode.cn/problems/factorial-trailing-zeroes/
 */
public class 阶乘后的零_lc_172 {
    public int trailingZeroes(int n) {

        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;


    }
}
