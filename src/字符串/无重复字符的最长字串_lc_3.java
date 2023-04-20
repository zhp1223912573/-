package 字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhp
 * @date 2022-07-18 19:55
 */
public class 无重复字符的最长字串_lc_3 {
    /**dp
     * map记录某一字符上次出现位置，顺序访问数组，根据map保存的同一字符上次出现位置来
     * 计算以当前字符结尾时的最长无重复字符串。dp[i]保存以i字符结尾的字符串最大长度。
     *
     *  如果上一次出现的位置大于当前字符的以前一个字符的为结尾的字符串的首字符位置，
     *      为了避免当前字符出现重复，则以当前字符结尾的字符串最大长度为i-index
     *  如果上一次出现的位置小于当前字符的以前一个字符为结尾的字符串的首字符位置，
     *      为了不让前一个字符出现重复，则以当前字符结尾的字符串最大长度为dp[i-1]+1;
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()<1){
            return 0;
        }

        int dp [] = new int[s.length()+1];
        Map<Character,Integer> map = new HashMap<>();
        int ans = 1;

        for(int i=0;i<s.length();i++){
            //上一次出现的位置
            Integer lastIndex = map.getOrDefault(s.charAt(i), -1);
            if(lastIndex>=i-dp[i]){
                dp[i+1] = i-lastIndex;

            }else{
                dp[i+1] = dp[i]+1;
            }

            map.put(s.charAt(i),i);
            ans = Math.max(ans,dp[i+1]);
        }

        return ans;
    }

    /**滑动窗口
     * 左右指针构成一个窗口，窗口内的字符串就是我们要找的不重复最长子串。
     * 该窗口表示以左指针指向的字符开始的最长不重复子串。
     * 如果出现了重复字符串，则说明当前窗口的做指针需要右移，反之我们不断右移右指针扩充窗口。
     *
     */
    public static int lengthOfLongestSubstring1(String s){
        Set<Character> set = new HashSet<>();
        //右指针赋值为-1，表示窗口内无字符
        int right = -1;
        int ans = 0;
        int n = s.length();
        int left = 0;
        for(left=0;left<n;left++){
            if(left!=0){
                //从第二个字符开始，要移除左指针上次移除的字符，窗口才能正常移动
                set.remove(s.charAt(left-1));
            }

            //右指针不超出边界，并且指向的新字符不是重复字符
            while(right+1<n && !set.contains(s.charAt(right+1))){
                //不断移动右指针
                set.add(s.charAt(right+1));
                right ++;
            }
            //窗口不再移动，此时判断是否出现了最大窗口。
            ans = Math.max(ans,right-left+1);
        }
        return ans;
    }


    /**
     * 使用map表示以当前字符结尾时，最长的字符串长度
     * 加入当前字符后，需要找出当前字符的上一次出现位置，
     * 以及当前字符的前一个字符结尾时的最长子字符串的长度，得到与该字符串重复的字符的位置
     * 比较上述的两个字符位置，根据较大的位置设置当前新子字符串的位置。
     */
    public int lengthOfLongestSubstring2(String str) {
        if(str==null || str.length()<1){
            return 0;
        }

        char[] chars = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        //设置一个数组映射字符的上一次出现位置

        for(int i=0;i<chars.length;i++){
            map.put(chars[i],-1) ;
        }
        int pre = -1;//
        int cur = 0;//当前长度
        int len = 0;//最大长度
        for(int i=0;i<chars.length;i++){
            pre = Math.max(pre,map.get(chars[i]));//当前字符上一次出现的位置
            cur = i-pre;//计算以当前字符结尾时的最大长度
            len = Math.max(len,cur);
            map.put(chars[i],i);//更新位置
        }
        return len;
    }


    public int lengthOfLongestSubstring3(String str) {
        boolean occ[] = new boolean[128];
        int res = 0;
        for(int l=0,r=0;r<str.length();r++){
            while(l<=r&&occ[str.charAt(r)]) occ[str.charAt(l++)]=false;
            occ[str.charAt(r)]=true;
            res = Math.max(res,r-l+1);
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("au"));
    }
}
