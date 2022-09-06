package 递归;

/**
 * @author zhp
 * @date 2022-07-08 14:53
 * 给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成
 * 的字符串express，再给定一个布尔值 desired。返回express能有多少种组合
 * 方式，可以达到desired的结果。
 * 【举例】
 * express="1^0|0|1"，desired=false
 * 只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。
 * express="1"，desired=false
 * 无组合则可以得到false，返回
 */
public class _多种符号组合方式 {
    /**
     * 假定每个逻辑符号都是最后进行运算，则如果想要得到desired,
     * 就需要得到其当前逻辑符号左右符合结果的可能性，相乘再计算累计和，就是答案。
     *
     * 如&符号，如果desired为true，为了达到目的结果其&左右的运算结果都必须为true
     * 所以总数=左边为true*右边为true
     * 如果desired为false，
     * 总数+=左边为true+右边为false
     * 总数+=左边为false+右边为false
     * 总数+=左边为false+右边为true
     */
    public static int num(String express,boolean desired){
        if(express==null || express.length() <1){
            return 0;
        }

        char[] chars = express.toCharArray();
        //判读给的express合不合法
        if(!isValid(chars)){
            return 0;
        }

        return p(chars,desired,0,chars.length-1);
    }

    /**
     *
     * @param chars
     * @param desired
     * @param l 运算式左边界
     * @param r 运算式右边界
     * @return
     */
    private static int p(char[] chars, boolean desired, int l, int r) {
        //终止条件，当指向当个字符，该字符一定为0或1，则根据desired返回方法种类
        if (l == r) {
            if (chars[l] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }

        int res = 0;
        //将所有的逻辑符号作为最后进心结合的符号
        for (int i = l + 1; i < r; i += 2) {
            if (desired) {//要求为true;
                if (chars[i] == '&') {
                    res += p(chars, true, l, i - 1) * p(chars, true, i + 1, r);
                } else if (chars[i] == '|') {
                    res += p(chars, true, l, i - 1) * p(chars, true, i + 1, r);
                    res += p(chars, false, l, i - 1) * p(chars, true, i + 1, r);
                    res += p(chars, true, l, i - 1) * p(chars, false, i + 1, r);
                } else if (chars[i] == '^') {
                    res += p(chars, false, l, i - 1) * p(chars, true, i + 1, r);
                    res += p(chars, true, l, i - 1) * p(chars, false, i + 1, r);
                }
            } else {
                if (chars[i] == '&') {
                    res += p(chars, false, l, i - 1) * p(chars, true, i + 1, r);
                    res += p(chars, false, l, i - 1) * p(chars, false, i + 1, r);
                    res += p(chars, true, l, i - 1) * p(chars, false, i + 1, r);
                } else if (chars[i] == '|') {
                    res += p(chars, false, l, i - 1) * p(chars, false, i + 1, r);
                } else if (chars[i] == '^') {
                    res += p(chars, false, l, i - 1) * p(chars, false, i + 1, r);
                    res += p(chars, true, l, i - 1) * p(chars, true, i + 1, r);
                }
            }
        }
        return res;
    }

    private static boolean isValid(char[] exp) {
        //必须为奇数个
        if ((exp.length & 1) == 0) {
            return false;
        }
        //偶数位必须位0或1
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }
        //奇数位必须位为逻辑运算符
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }
        return true;
    }


    /**
     * dp
     *
     * @param express
     * @param desired
     * @return
     */
    public static int num2(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for (int i = 2; i < exp.length; i += 2) {
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }


    public static void main(String[] args) {
        System.out.println(num("1^0|0|1",false));
    }
}
