package 位运算;

/**
 * @author zhp
 * @date 2022-10-23 12:31
 * https://leetcode.cn/problems/closed-number-lcci/comments/
 */
public class ms05_04_下一个数 {
    /**
     * 找到两个和当前数值二进制表示时1的个数一直，且最接近的两个数
     */

    /**
     * 暴力枚举
     * 以当前数为起点，分别向上向下求解。
     */
    public int[] findClosedNumbers(int num) {
        int up = num + 1;//向上枚举
        int down = num - 1;//向下枚举
        int count = findOneCount(num);//num的1的个数
        while (findOneCount(up) != count) {
            up++;
            if (up < 0) {//越界了那就是找不到，设置为-1
                up = -1;
                break;
            }
        }
        while (findOneCount(down) != count) {
            down--;
            if (down < 0) {//变为负数了那就是找不到了，设置为-1
                down = -1;
                break;
            }
        }
        return new int[]{up, down};
    }
    //求数的二进制1的个数
    private static int findOneCount(int num) {
        int count = 0;
        while (num != 0) {
            num &= num - 1;
            count++;
        }
        return count;
    }


}
