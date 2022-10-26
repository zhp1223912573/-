package 每日任务;

/**
 * @author zhp
 * @date 2022-10-24 12:51
 * https://leetcode.cn/problems/exchange-lcci/submissions/
 */
public class ms05_07_配对交换 {
    /**
     * 魔数
     * @param num
     * @return
     */
    public int exchangeBits(int num) {
        int a = 0x55555555;
        int b = 0xaaaaaaaa;
        int x =((num&a)<<1);
        int y =((num&b)>>1);
        return x|y;
    }
}
