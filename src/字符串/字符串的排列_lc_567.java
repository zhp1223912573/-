package 字符串;

/**
 * @author zhp
 * @date 2023-04-14 14:05
 */
public class 字符串的排列_lc_567 {

    /**
     * 经典滑动窗口模板
     */
    int c1[] = new int[26];
    int c2[] = new int[26];
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        for(int i=0;i<s1.length();i++){
            c1[s1.charAt(i)-'a']++;
            c2[s2.charAt(i)-'a']++;
        }
        if(check()) return true;
        for(int l=0,r=s1.length();r<s2.length();r++,l++){
            c2[s2.charAt(r)-'a']++;
            c2[s2.charAt(l)-'a']--;
            if(check()) return true;
        }
        return false;
    }

    boolean check(){
        for(int i=0;i<26;i++){
            if(c1[i]>c2[i]) return false;
        }
        return true;
    }
}
