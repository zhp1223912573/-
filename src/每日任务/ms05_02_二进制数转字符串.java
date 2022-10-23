package 每日任务;

/**
 * @author zhp
 * @date 2022-10-23 11:04
 * https://leetcode.cn/problems/bianry-number-to-string-lcci/submissions/
 */
public class ms05_02_二进制数转字符串 {
    /**
     * 将一个小于0的小数转化为二进制，按照如下步骤进行
     * 0.625
     * 将0.625*2得到1.25；
     * 则数值1就是第一位二级制位，
     * 将1.25-1得到0.25，继续作为新的数，
     * 将0.25*2得到0.5，
     * 则数值0就是第二位二进制位，
     * 得到0.5*2得到1，
     * 则数值1就是第三位二进制位，
     * 当小数点后数值为0，说明该数已经完全转化为二进制形式
     * @param num
     * @return
     */
    public String printBin(double num) {
        int i=0;
        StringBuilder sb = new StringBuilder("0.");
        while(num!=0&&i<32){
            double ans = num*2;
            sb.append((int)ans);
            num = ans>=1?ans-1:ans;
            i++;
        }

        return num==0?sb.toString():"ERROR";
    }
}
