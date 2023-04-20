package 字符串;

/**
 * @author zhp
 * @date 2023-04-03 13:43
 * https://leetcode.cn/problems/delete-operation-for-two-strings/submissions/
 */
public class 两个字符串的删除操作_lc_583 {
    /**
     *f(i,j)表示word1的0-i个字符与word2的0-j个字符中最小操作数
     * 当word1[i]==word2[j]时，无需进行删除操作，f(i,j)=f(i-1,j-1)
     * 不相等时，需要删除一个字符，为了得到最小操作数，f(i,j)=min(f(i-1,j),f(i,j-1))+1
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
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
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+1;
                }
            }
        }
        return dp[len1][len2];
    }
}
