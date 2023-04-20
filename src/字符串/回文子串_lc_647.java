package 字符串;

/**
 * @author zhp
 * @date 2023-04-03 14:09
 * https://leetcode.cn/problems/palindromic-substrings/
 */
public class 回文子串_lc_647 {
    /**
     * f(i,j)表示字符串取件【i..j】的字符串是否为回文字符串，
     * 则i<=j
     * 当s[i]==s[j]且f(i+1，j-1）为true时，f(i,j)为true
     * 所以i+2<=j
     * 当i==j时，f(i,j)直接为true
     * 当i+1==j时，f(i,j) = s[i]==s[j]
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int cnt = 0;
        for(int j=0;j<n;j++){
            for(int i=j;i>=0;i--){
                if(i==j) dp[i][j] = true;
                else if(i+1==j) dp[i][j] = s.charAt(i)==s.charAt(j);
                else dp[i][j] = s.charAt(i)==s.charAt(j)&&dp[i+1][j-1];
                if(dp[i][j] ) cnt++;
            }
        }
        return cnt;
    }

    /**
     * 中心扩散
     */
    int num = 0;
    public int countSubstrings1(String s) {
        for (int i=0; i < s.length(); i++){
            count(s, i, i);//回文串长度为奇数
            count(s, i, i+1);//回文串长度为偶数
        }
        return num;
    }

    public void count(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            num++;
            start--;
            end++;
        }
    }
}
