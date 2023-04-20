package 测试.动规真题.九的倍数的子序列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhp
 * @date 2023-04-08 19:25
 *  9的倍数的子序列【携程】难度：5
 * 游游拿到了一个数字串，她想取一个该数字串的子序列(子序列在原串中可以不连续)，使得该子序列是9的倍数。子序列可以包含前导零。
 * 游游想知道,一共能取多少个合法的子序列?答案请对10^9＋7取模。我们定义，若两个子序列在原串中的位置不同，则认为它们不同。
 * 输入描述:
 * 一个长度不超过200000的，仅由'0'~'9’十种字符组成的字符串。
 * 输出描述:
 * 子序列是9的倍数的数量。答案请对10^9+7取模。
 * 示例1输入输出示例仅供调试,后台判题数据一般不包含示例
 * 输入
 * 1188
 * 输出
 * 5
 * 说明
 * 共可以取4个不同的"18"子序列,和一个“1188”子序列,都是9的倍数
 */
public class Main {
    //状态定义：dp[i][j] 考虑前i个字符，凑出子序列的值 模9后的余数为j的 数量。
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            char[] cs = br.readLine().toCharArray();
            int[][] dp = new int[cs.length + 1][10];
            //选与不选当前字符
            dp[0][0] = 1;
            for (int i = 1; i <= cs.length; i++) {
                for (int j = 0; j < 10; j++) {
                    //不选当签字符 + 选当前字符（需要找到先前上一个值加入后的余数，所以要减去当前字符数值，+9避免负数出现）
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][(j - (cs[i - 1] - '0') + 9) % 9];
                }
            }

            System.out.println(dp[cs.length][0] - 1);

    }
}
