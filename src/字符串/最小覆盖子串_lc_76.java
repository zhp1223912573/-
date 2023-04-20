package 字符串;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-28 15:31
 * https://leetcode.cn/problems/minimum-window-substring/
 */
public class 最小覆盖子串_lc_76 {
    //记录t中对应字符出现次数
    Map<Character,Integer> ori = new HashMap<>();
    //记录当前滑动窗口内包含的t中的字符的出现次数
    Map<Character,Integer> cnt = new HashMap<>();
    public String minWindow(String s, String t) {
        char[] chars = t.toCharArray();
        //记录t中字符出现次数
        for(char ch :chars){
            ori.put(ch,ori.getOrDefault(ch,0)+1);
        }

        int l = 0,r=-1;//左右指针
        int slen = s.length();
        int len = Integer.MAX_VALUE;//出现的包含t中所有字符的最短字符串
        int ansl = -1,ansr=-1;//答案字符串的左右边界
        while(r<slen){
            r++;
            //当前右指针未超出边界，并且指向的字符为t中出现的字符，记录在cnt内
            if(r<slen&&ori.containsKey(s.charAt(r))){
                cnt.put(s.charAt(r),cnt.getOrDefault(s.charAt(r),0)+1);
            }

            //检查当前滑动窗口是否符合t中的所有字符
            while(check()&&l<=r){
                if(r-l+1<len){
                    len = r-l+1;
                    ansl = l;
                    ansr = l+len;
                }

                if( ori.containsKey(s.charAt(l))){
                    cnt.put(s.charAt(l),cnt.getOrDefault(s.charAt(l),0)-1 );
                }

                l++;//缩小滑动窗口，检查是否存在更小的长度
            }
        }

        return ansl ==-1?"":s.substring(ansl,ansr);
    }
    private boolean check() {
        Iterator<Map.Entry<Character, Integer>> iterator =ori.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            Character key = next.getKey();
            Integer value = next.getValue();
            if(cnt.getOrDefault(key,0)<value){
                return false;
            }
        }
        return true;
    }


    class solution{
        int sc[] = new int[128];
        int tc[] = new int[128];
        public String minWindow(String s, String t) {
            for(char ch:t.toCharArray()){
                tc[ch] ++;
            }
            int minLen = 1000010;
            int start = -1;
            char cs[] = s.toCharArray();
            for(int l=0,r=0;r<cs.length;r++){
                sc[cs[r]]++;
                while(l<=r&&check()) {
                    if(minLen>r-l+1){
                        minLen = r-l+1;
                        start = l;
                    }
                    sc[cs[l++]]--;
                }
            }
            return start==-1?"":s.substring(start,start+minLen);
        }

        boolean check(){
            for(int i=0;i<128;i++){
                if(tc[i]>sc[i]) return false;

            }
            return true;
        }
    }
}
