package 位运算;

/**
 * @author zhp
 * @date 2022-10-23 12:25
 * https://leetcode.cn/problems/convert-integer-lcci/submissions/
 */
public class ms05_06 {

    /**
     * 计算两数需要变化多少位才能相等
     * 异或，计算1的个数即可
     * @param A
     * @param B
     * @return
     */
    public int convertInteger(int A, int B) {
        int xor = A^B;
        int cnt = 0;
        //这里用来一个小技巧，一个数与自己-1相与，直到自己为0，那么循环的次数就是1的个数
        while(xor!=0){
            xor&=(xor-1);
            cnt++;
        }
        return cnt;
    }
}
