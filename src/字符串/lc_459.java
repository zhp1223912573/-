package 字符串;

import org.junit.Test;

/**
 * @author zhp
 * @date 2021-11-15 10:39
 * 重复的字符串
 * https://leetcode-cn.com/problems/repeated-substring-pattern/solution/zhong-fu-de-zi-zi-fu-chuan-by-leetcode-solution/
 */
public class lc_459 {

    @Test
    public void test(){
        System.out.println(repeatedSubstringPattern("acd"));
    }


    public boolean repeatedSubstringPattern(String s){
        /*方法一*/
        //return   meiju(s);
        /*方法二*/
        //return doubleStr(s);
        /*方法三*/
        //return KMP(s);

        return false;
    }

    /**
     *
     * @param s
     * @return false--匹配 true--不匹配
     * 直接枚举字符串s中以首字符开始的字符串
     * s由n'个s'字符串构成 则 s[i]=s[i-n']
     */
    public boolean meiju(String s){
        int n=s.length();
        for(int i=1;i*2<=n;++i){ //子字符串长度一定小于原字符串的一半
            if(n%i==0) {
                boolean match=true;
                for(int j=i;j<n;++j){
                    if(s.charAt(j)!=s.charAt(j-i)){
                        match=false;
                        break;
                    }

                }
                if(match){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param s
     * @return
     * 将两个字符串s进行拼接 去除第一个和最后一个字符 剩下的字符串中若包含s 则说明该字符串可有多个s’构成
     */
    public boolean doubleStr(String s){
        return (s+s).indexOf(s,1)!=s.length();
    }

    
    public boolean KMP(String s){
        //通过next数组找到最长相同前后缀长度
        int next[]=new int[s.length()];
        int j=-1;
        next[0]=-1;
        for(int i=1;i<s.length();++i){
            while(j>=0&&s.charAt(i)!=s.charAt(j+1)){
                j=next[j];
            }

            if(s.charAt(j+1)==s.charAt(i)){
                j++;
            }

            next[i]=j;
        }
        //用s.length-最长相同前后缀长度得到第一个子字符s’的长度 若该子字符串的长度可以被len取余得0 说明该字符串由若干个s‘构成
        if((s.length()%(s.length()-next[s.length()-1]))==0&&next[s.length()-1]!=-1){
            return true;
        }

        return false;
    }


}
