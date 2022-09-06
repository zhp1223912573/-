package 字符串;

import javax.swing.text.Keymap;
import java.util.*;

/**
 * @author zhp
 * @date 2022-07-18 15:44
 * https://leetcode.cn/problems/group-anagrams/
 */
public class 字母异位词分组_lc_49 {
    /**
     * 两单词排序后如果字符一致，那么就是异位词。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> value = map.getOrDefault(sortedStr, new ArrayList<>());
            value.add(str);
            map.put(sortedStr,value);
        }
        return new ArrayList<>(map.values());
    }

    /**
     *异位词的所有字符一致，且出现次数一致，仅仅排序不一致。
     * 利用这一特性，记录所有字符出现次数，将次数和字符结合成一个字符串，作为识别不同异位词的标志key
     */
    public List<List<String>> groupAnagrams1(String[] strs){
        Map<String,List<String>> map = new HashMap<>();
        for(String string:strs){
            int count[] = new int[26];
            for(int i=0;i<string.length();i++){
                count[string.charAt(i)-'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<26;i++){
                if(count[i]!=0){
                    sb.append((char)(i+'a'));
                    sb.append(count[i]);
                }
            }
            String str = sb.toString();
            List<String> list = map.getOrDefault(str, new ArrayList<>());
            list.add(string);
            map.put(str,list);
        }
        return new ArrayList<>(map.values());
    }

}
