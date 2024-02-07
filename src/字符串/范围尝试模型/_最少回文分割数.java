package 字符串.范围尝试模型;

/**
 * @author zhp
 * @date 2022-07-11 20:28
 * <p>
 * 给定一个字符串str，返回把str全部切成回文子串的最小分割数。
 * 【举例】
 * str="ABA"。
 * 不需要切割，str本身就是回文串，所以返回0。
 * str="ACDCDCDAD"。
 * 最少需要切2次变成3个回文子串，比如"A"、"CDCDC"和"DAD"，所以返回2。
 */
public class _最少回文分割数 {
    /**
     * 单调与范围的结合
     * 设置dp数组，dp[i]表示i-end的子字符串构成最少切割回文时的数量
     * 则dp【0】就是我们需要的答案，因此需要从数组右侧向左推导。
     * 如果i-j的字符串是回文字符串，那么dp[i] = min(dp[i],dp[j+1]+1)
     * 既需要遍历整个数组，遍历每个字符时有需要向后找到能构成回文字符串的子数组，同时还需要判断该子数组是否为回文字符串。
     * 综上，时间复杂度为O(n^3)
     * <p>
     * 我们可以通过范围尝试模型牺牲一定空间，从而使判断字符串为回文字符串的过程降低为O(1)
     * 即通过boolean二维数组record，记录record[i][j]，字符i-j是否为回文字符串。
     * 初始情况：
     * 只有一个字符时必定为真，两个字符一致时为真。
     * 可能性分析：
     * 对于字符串{i...j}来说，仅当i==j，并且{i+1...j-1}字符为真时，{i...j}才为真
     */
    public static int minCut(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        dp[len] = 0;
        dp[len - 1] = 1;//最后一个字符要形成一个回文字符串肯定需要砍一次
        //得到字符串chars的范围回文数组
        boolean[][] record = getRecord(chars);
        for (int i = len - 2; i >= 0; i--) {
            //此处需要初始化为i-end字符串的最大切割数，保证下面迭代带过程能读入更小值
            //这里设置为i-end的字符个数，是假定每个字符都进行切割
            dp[i] = chars.length - i;
            for (int j = i; j < len; j++) {
                if (record[i][j])//是回文字符串，才进行更新
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
            }
        }
        return dp[0];
    }

    //返回一个字符串的范围回文字符数组
    private static boolean[][] getRecord(char[] chars) {
        boolean record[][] = new boolean[chars.length][chars.length];
        //单个字符必定为回文
        record[chars.length - 1][chars.length - 1] = true;
        for (int i = 0; i < chars.length - 1; i++) {
            //主对角线全为单个字符，必定为回文字符串
            record[i][i] = true;
            //次对角线为两个字符，需要比较是否一致才赋值
            record[i][i + 1] = chars[i] == chars[i + 1];
        }

        for (int i = chars.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < chars.length; j++) {
                // 对于字符串{i...j}来说，仅当i==j，并且{i+1...j-1}字符为真时，{i...j}才为真
                record[i][j] = chars[i] == chars[j] && record[i + 1][j - 1];
            }
        }

        return record;
    }

    /**
     * 思路与上面的一致，只不过将回文字符串的判断放在求解最长切割数求解过程
     *
     * @param str
     * @return
     */
    public static int minCut1(String str) {
        int len = str.length();
        int dp[] = new int[len + 1];
        dp[len] = -1;
        boolean p[][] = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                if (str.charAt(i) == str.charAt(j) && (i - j < 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }
        return dp[0];
    }
}
