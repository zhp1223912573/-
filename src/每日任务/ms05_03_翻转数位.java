package 每日任务;

/**
 * @author zhp
 * @date 2022-10-23 12:19
 * https://leetcode.cn/problems/reverse-bits-lcci/submissions/
 */
public class ms05_03_翻转数位 {
    /**
     * 动规
     * cur记录当前连续1的长度
     * max记录最大连续1的长度
     * pre记录当一个0翻转成1时cur的长度
     *
     * 当访问到0时，我们都尝试去翻转该数值0，将其变为1。
     * 我们使用pre去记录翻转后的长度，便于我们再次遇到0时，可以减去先前翻转为1后构造出的连续1，
     * 所以cur需要减去pre。
     *
     * @param num
     * @return
     */
    public int reverseBits(int num) {
        int max = 0,pre =0,cur=0;
        for(int i=0;i<32;i++){
            if((num&1)==0){
                cur -= pre ;
                pre = cur+1;
            }
            cur++;
            max = Math.max(cur,max);
            num>>=1;
        }
        return max;
    }
}
