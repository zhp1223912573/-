package 字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-10-20 20:35
 * https://leetcode.cn/problems/palindrome-permutation-lcci/submissions/
 * 回文排列
 */
public class ms01_04_回文排列 {
    /**
     * 只要一个字符串中的出现奇数次的字符只有一个，那么该字符串就必定能变成回文字符串
     *
     * 基于map
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> count = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            int times = count.getOrDefault(s.charAt(i),0 ) + 1;
            count.put(s.charAt(i), times);
        }
        int res = 0;
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if ((entry.getValue() % 2) == 1) res++;
            if (res >= 2) return false;
        }
        return true;
    }
    //基于set
    public boolean canPermutePalindrome1(String s){
        Set<Character> set = new HashSet<>();
        for(char ch : s.toCharArray()){
            if(set.contains(ch)){
                set.remove(ch);
            }else{
                set.add(ch);
            }
        }
        return set.size()<=1;
    }

    public static void main(String[] args) {
        ms01_04_回文排列 a = new ms01_04_回文排列();
        System.out.println(a.canPermutePalindrome("code"));
    }
}


