package 数学;

/**
 * @author zhp
 * @date 2022-10-22 10:44
 * https://leetcode.cn/problems/qiu-12n-lcof/submissions/
 */
public class offer_64_求1_n和 {
    /**
     * 要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句
     * 那就只能在递归中完成计算，并利用短路运算符&&
     * @param n
     * @return
     */
    public int sumNums(int n) {
        boolean flag = n>1&& (n+=sumNums(n-1))>0;
        return n;
    }
}
