package 每日任务;

/**
 * @author zhp
 * @date 2022-10-24 12:57
 * https://leetcode.cn/problems/recursive-mulitply-lcci/submissions/
 */
public class ms08_05 {
    /**
     * 递归乘法
     * @param A
     * @param B
     * @return
     */
    public int multiply(int A, int B) {
        return B==0?0:A+multiply(A,B-1);
    }
}
