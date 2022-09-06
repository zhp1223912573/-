package 字符串;

/**
 * @author zhp
 * @date 2022-07-11 14:52
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，求两个字符串的最长公
 * 共子串
 *
 */
public class _最长公共子串 {
    /**
     * dp
     * dp[i][j]二维数组表示str1以i结尾，str2以j结尾时最长公共子串
     * 列表观察可得，除去第一行，
     * 剩下所有位置的值取决与其左上角的dp数组值
     * dp[i][j] = dp[i-1][j-1]+1(str1[i]==str2[j])
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        //记录最长公共子串的字符坐标
        int end = 0;
        int max = 0;
        for (int i = 0; i < chs1.length; i++) {
            for (int j = 0; j < chs2.length; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = 1;
            }
        }
        for (int j = 1; j < str2.length; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    /**
     * dp空间压缩优化
     * 观察方法1得到的图表可以发现，我们只需要使用有限个变量，从数组的右上角开始沿着斜线遍历，
     * 既可以遍历计算所有值，也可以节省空间。
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String lcst2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = 0;
        int col = chs2.length - 1;
        int max = 0;
        int end = 0;
        while (row < chs1.length) {
            int i = row;
            int j = col;
            int len = 0;
            //该循环进行斜线遍历
            while (i < chs1.length && j < chs2.length) {
                if (chs1[i] != chs2[j]) {
                    len = 0;
                } else {
                    len++;
                }

                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            //如果遍历到最左列开始向下遍历时，需要使row++
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));

    }
}
