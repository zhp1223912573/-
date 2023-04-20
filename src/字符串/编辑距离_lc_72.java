package 字符串;

/**
 * @author zhp
 * @date 2022-07-08 19:33
 *https://leetcode.cn/problems/edit-distance/
 *
 */
public class 编辑距离_lc_72 {

    /**
     * 题目：
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *  
     *
     * 示例 1：
     *
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/edit-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**dp
     * 题目：
     * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删
     * 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
     * 【举例】
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=2
     * 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=100
     * 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
     * str1="abc"，str2="abc"，ic=5，dc=3，rc=2
     * 不用编辑了，本来就是一样的字符串，所以返回0
     *
     * 该题是每年必考题，困难难度，需要好好掌握。
     *
     * 直接dp。
     * 使用dp二维数组,dp[i][j]表示原始字符i位及以前的字符构成目标字符j位及以前的最小代价啊。
     * 通过举例分析，有以下四种情况。
     * 1.
     * 原始：abcd 目标：abcde
     * 先构成目标字符的前四个，再加上一个ic插入操作的开销
     * 即为：dp[i][j-1]+ins;
     * 2.
     * 原始：aibcd 目标：abc
     * 先构成目标字符的三个字符，再加上一个de删除操作的开销
     * 即为：dp[i-1][j]+del
     * 3.
     * 原始：abcd 目标：abce
     * 先构成目标字符的前三个字符，再加上一个re替换操作的开销
     * 即为：dp[i-1][j-1]+re
     * 4.
     * 原始：abcd 目标：abcd
     * 两者一致，无须添加开销
     *即为：dp[i-1][j-1]
     */
    public static int minCost(String source,String dest,int ic,int dc,int rc){
        if(source==null || dest==null){
            return 0;
        }
        char[] char1 = source.toCharArray();
        char[] char2 = dest.toCharArray();

        int dp[][] = new int[char1.length+1][char2.length+1];

        //初始化
        //dp[0][0]表示空串，要从空串构成目标字符串，需要插入字符
        for(int i=0;i<char2.length+1;i++){
            dp[0][i] = ic*i;
        }
        //要从原串变成目标串（空串）需要删除字符
        for(int i=0;i<char1.length;i++){
            dp[i][0] = dc*i;
        }

        //开始状态转换
        for(int i=1;i<char1.length+1;i++){
            for(int j=1;j<char2.length+1;j++){
                //先进行3，4情况判定
                if(char1[i]==char2[j]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //尾字符不同需要取代
                    dp[i][j] = dp[i-1][j-1]+rc;
                }
                //在进行1，2条件的判断
                dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+dc);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1]+ic);
            }

        }
        return dp[char1.length][char2.length];
    }

    public int minDistance3(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int dp[][] = new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++) dp[i][0] = i;
        for(int j=0;j<=len2;j++) dp[0][j] = j;
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[len1][len2];
    }
}
