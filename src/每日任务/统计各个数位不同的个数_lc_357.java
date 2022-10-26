package 每日任务;

/**
 * @author zhp
 * @date 2022-10-25 14:02
 *https://leetcode.cn/problems/count-numbers-with-unique-digits/submissions/
 */
public class 统计各个数位不同的个数_lc_357 {
    /**
     * 规律题
     * 观察n为1，2，3...时分别为多少，总结规律即可。365
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0) return 1;
        if(n==1) return 10;
        int res = 10;
        int start =9;
        int available = 9;
        while(n-->1&&available>0){
            start = start*available;
            res += start;
            available--;
        }
        return res;
    }
}
