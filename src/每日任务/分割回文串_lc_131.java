package 每日任务;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-29 20:54
 * https://leetcode.cn/problems/palindrome-partitioning/
 *
 */
public class 分割回文串_lc_131 {

    List<List<String>> ans = new ArrayList<>();
    List<String> path = new ArrayList<>();
    int n;
    public List<List<String>> partition(String s) {
        //方法1
//        backtract(s,0);
        //方法2
//        n=s.length();
//        f = new boolean[n][n];
//        for(int i=0;i<n;i++){
//                Arrays.fill(f[i],true);
//        }
//        //字符检查
//        for(int i=n-1;i>=0;i--){
//            for(int j=i+1;j<n;j++){
//                f[i][j] = (s.charAt(i)==s.charAt(j)&&f[i+1][j-1]);
//            }
//        }
        //方法3
        int n = s.length();
        k = new int[n][n];
        backtract2(s,0);

        return ans;
    }

    /**
     * 常规回溯
     * @param s
     * @param start
     */
    private void backtract(String s, int start) {
        if(start==s.length()){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int end = start;end<s.length();end++){
            if(isHuiwen(s.substring(start,end+1))){
                path.add(s.substring(start,end+1));
                backtract(s,end+1);
                path.remove(path.size()-1);
            }
        }
    }
    public boolean isHuiwen(String str){
        int l = 0;
        int r = str.length()-1;
        while(l<=r){
            if(str.charAt(l)!=str.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }


    /**回溯+动规
     * 使用f来记录i-j字符串是否为回文串，避免回溯过程中反复对同一子字符串是否为回文字符串进行查询。
     * @param s
     * @param start
     */
    boolean f[][] ;
    private void backtract1(String s, int start) {
        if(start==s.length()){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int end = start;end<s.length();end++){
            if(f[start][end]){
                path.add(s.substring(start,end+1));
                backtract(s,end+1);
                path.remove(path.size()-1);
            }
        }
    }


    /**
     * 再次进阶，将求解范围字符串是否为回文串的过程也转化为动规
     *
     * // 记忆化搜索中，k[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
     */
    int k [][] ;
    public int  isPalindrome(String s, int i, int j) {
        if(k[i][j]!=0){
            return k[i][j];
        }

        if(i>=j){
            k[i][j] =  1;
        }else if(s.charAt(i)==s.charAt(j)){
            k[i][j] =isPalindrome(s,i+1,j-1);
        }else{
             k[i][j] =-1;
        }
        return k[i][j];

    }
    private void backtract2(String s, int start) {
        if(start==s.length()){
            ans.add(new ArrayList<>(path));
            return ;
        }

        for(int end = start;end<s.length();end++){
            if(isPalindrome(s,start,end)==1){
                path.add(s.substring(start,end+1));
                backtract(s,end+1);
                path.remove(path.size()-1);
            }
        }
    }
}
