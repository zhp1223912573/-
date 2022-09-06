package 位运算;

/**
 * @author zhp
 * @date 2022-06-15 20:52
 * 给顶两个int型变量a，b，不使用比较符合的情况下得到较大值
 */
public class _比较a与b大小 {
    /**反转1为0，0为1
     *
     * @param n 为1或为0
     * @return
     */
    public static int flip(int n){
        return n^1;
    }

    /**得到数字的符号位，负数为0，正数为1
     *
     * @param n
     * @return
     */
    public static int sign(int n){
        return flip( (n>>31)&1);
    }

    /**获得两数较大值
     *
     * @param a
     * @param b
     * @return
     */
    public int getMax(int a,int b){
        int c =a-b;
        int signA = sign(c);//a-b为负，signA为0，为非负，signA为1
        int signB = flip(signA);//signA为1，signB为0，反之亦然
        return a*signA + b*signB;
    }


}
