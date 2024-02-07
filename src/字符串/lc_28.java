package 字符串;

import org.junit.Test;

/**
 * @author zhp
 * @date 2021-11-09 11:41
 */
//
public class lc_28 {
    @Test
    public void test1(){

        int strStr = strStr("ababcaababcaabc" ,
                "ababcaabc");
        System.out.println(strStr);
    }
    public int strStr(String haystack, String needle) {
        if(haystack==null||haystack.length()==0
                ||haystack.length()<needle.length())
            return -1;

        if(needle==null||needle.length()==0)
            return 0;

        int []next=new int[needle.length()];
        KMP.getNext(next,needle);
        char[] s = haystack.toCharArray();
        char[] t = needle.toCharArray();

        int j=-1;

        for (int i = 0; i <haystack.length(); i++) {
            while(j>=0&&s[i]!=t[j+1]){
                j=next[j];
            }
            if(s[i]==t[j+1]){
                j++;
            }
            if(j==t.length-1){
                return (i-t.length+1);
            }
        }

        return -1;
    }
}
