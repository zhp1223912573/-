package 每日任务;

/**
 * @author zhp
 * @date 2022-10-21 10:10
 * https://leetcode.cn/problems/string-rotation-lcci/?favorite=xb9lfcwi
 */
public class ms01_09_字符串轮转 {
    /**
     * 模拟法，尝试所有的轮转可能，如果存在某种轮转成立，则返回true
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if(s1.length()!=s2.length()) return false;
        for(int i=0;i<s1.length();i++){
            char[] chars = s1.toCharArray();
            String reverse1 = reverse(s1.substring(0, i).toCharArray());
            String reverse2 = reverse(s1.substring(i, s1.length()).toCharArray());
            String reverse = reverse((reverse1+reverse2).toCharArray());
            if(s2.equals(reverse)) return true;
        }
        return false;
    }
    public String reverse(char[] str){
        int i=0,j=str.length-1;
        while(i<j){
            char c = str[i];
            str[i] = str[j];
            str[j] = c;
            i++;
            j--;
        }
        return new String(str);
    }

    /**
     * 模拟法
     * 如果两个字符串可以通过轮转得到，那么
     * ​假设s1轮转i位，如果s2的（i+j)mod(s.length)的字符不一致，说明当前轮次数肯定不成立

     */
    public boolean isFlipedString1(String s1, String s2){
        if(s1.length()!=s2.length()) return false;
        if(s1.length()==0) return true;

        for(int i=0;i<s1.length();i++){
            boolean flag = true;
            for(int j=0;j<s2.length();j++){
                if(s1.charAt((i+j)%s1.length())!=s2.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }

    /**
     * 轮转意味着某一字符串重复拼接一次，内部的一定存在另一字符串
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString2(String s1, String s2) {
        return s1.length()==s2.length()&&(s1+s1).contains(s2);
    }
    public static void main(String[] args) {
        ms01_09_字符串轮转 a =new ms01_09_字符串轮转();
        System.out.println(a.isFlipedString("waterbottle","erbottlewat"));
    }
}
