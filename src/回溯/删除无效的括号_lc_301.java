package 回溯;

import com.sun.corba.se.spi.ior.iiop.RequestPartitioningComponent;

import java.security.interfaces.DSAParams;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-29 22:00
 * https://leetcode.cn/problems/remove-invalid-parentheses
 */
public class 删除无效的括号_lc_301 {
    /**回溯+剪枝
     * 为了得到字符串s删除最少括号后的有效字符串，我们首先需要找出在s中多出的左右括号是多少个，
     *      接着在回溯过程中尝试取清除多出的左右括号，当多出的左右括号都清除掉了，我们需要验证剩下的字符串是否合法，
     *      合法则加入答案中，等待返回。
     * 其中有一个小问题，就是在回溯过程中可能多次尝试删除相邻的括号时，
     *      生成的有效字符串重复，此处用两个解决方案：
     *      1，使用set自动对加入的答案去重
     *      2.每次进行搜索时，如果遇到连续相同的括号我们只需要搜索一次即可，
     *      比如当前遇到的字符串为 "(((())"，去掉前四个左括号中的任意一个，生成的字符串是一样的
     *
     * @param s
     * @return
     */
    List<String> ans = new ArrayList<>();
    public List<String> removeInvalidParentheses(String s) {
        int leftRemove =0;
        int rightRemove = 0;
        int n = s.length();
        //1.得到多余的左右括号
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                leftRemove ++;
            }else if(s.charAt(i)==')'){
                if(leftRemove>0){//存在一个左括号，则可以与当前右括号匹配
                    leftRemove--;
                }else{//不存在匹配的左括号，说明当前右括号是多余的
                    rightRemove++;
                }
            }
        }
        //2.回溯尝试删除不同位置的符号
        backtract(s,0,leftRemove,rightRemove);

        //3.返回答案
        return ans;
    }
    private void backtract(String s, int start, int leftRemove, int rightRemove) {
        //多余的括号都被删除掉了
        if(leftRemove==0&&rightRemove==0){
            //还需要检验此次删除括号后剩下的字符串是否合法
            if(isValid(s)){
                ans.add(s);
            }
            return ;
        }

        for(int i=start;i<s.length();i++){
            //首先检查是否出现连续多个相同序号出现，是的话，跳过这些
            if(i>start&&s.charAt(i)==s.charAt(i-1)){
                continue;
            }

            //剪枝， 如果要删除的括号数大于剩下的字符串数，那么没必要继续删除
            if(leftRemove+rightRemove>s.length()-i){
                return ;
            }
            //开始尝试删除
            if(leftRemove>0&&s.charAt(i)=='('){
                backtract(s.substring(0,i)+s.substring(i+1),i,leftRemove-1,rightRemove);
            }
            if(rightRemove>0&&s.charAt(i)==')'){
                backtract(s.substring(0,i)+s.substring(i+1),i,leftRemove,rightRemove-1);
            }
        }
    }

    /**广搜
     * 题目要求找出字符串s的最少删除字符下的有效字符串的答案组和，这与广搜的思路不谋而和，
     *   通过尝试对出现过的括号进行删除，在最少的伦次下得到的有效字符串就是最小的删除次数的有效字符串组合。
     */
    public List<String> removeInvalidParentheses1(String s){
        List<String> ans = new ArrayList<>();
        //保存当前轮次下的字符串
        Set<String> curSet = new HashSet<>();
        curSet.add(s);

        while(true){
            for(String str: curSet){
                if(isValid(str)){
                    ans.add(str);
                }
            }
            if(ans.size()>0){
                return ans;
            }
            //保存下一轮的字符串
            Set<String> nextSet = new HashSet<>();
            for(String str : curSet){
                for(int i=0;i<str.length();i++){
                    //跳过掉连续重复括号的删除步骤，原因同第一个方法一致
                    if(i>0&&str.charAt(i)==str.charAt(i-1)) continue;

                    //开始删除
                    if(s.charAt(i)=='('||s.charAt(i)==')'){
                        nextSet.add(str.substring(0,i)+str.substring(i+1));
                    }
                }
            }
            curSet = nextSet;
        }

    }


    /**
     * 检验当前字符串是否合法
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        int count =0;
        for(int i =0;i<s.length();i++){
            if(s.charAt(i)=='('){
                count++;
            }else if(s.charAt(i)==')'){
                count--;
                if(count<0) return  false;
            }
        }

        return  count==0;
    }




}
