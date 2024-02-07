package 字符串;

/**
 * @author zhp
 * @date 2022-07-08 15:51
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * 例如：
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 * bbbbb，答案是b，长度为1
 * pwwkew，答案是wke，长度是3
 * 要求：答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。
 */
public class _最长不重复字符串 {
    /**
     * 使用数组表示以当前字符结尾时，最长的字符串长度
     * 加入当前字符后，需要找出当前字符的上一次出现位置，
     * 以及当前字符的前一个字符结尾时的最长子字符串的长度，得到与该字符串重复的字符的位置
     * 比较上述的两个字符位置，根据较大的位置设置当前新子字符串的位置。
     */
    public static int process(String str){
        if(str==null || str.length()<1){
            return 0;
        }

        char[] chars = str.toCharArray();

        //设置一个数组映射字符的上一次出现位置
        int [] map = new int[256];
        for(int i=0;i<chars.length;i++){
            map[i] = -1;
        }
        int pre = -1;//代表当前字符前一个字符的上一次出现的位置
        int cur = 0;//当前长度
        int len = 0;//最大长度
        for(int i=0;i<chars.length;i++){
            pre = Math.max(pre,map[chars[i]]);//当前字符上一次出现的位置
            cur = i-pre;//计算以当前字符结尾时的最大长度
            len = Math.max(len,cur);
            map[chars[i]] = i;//更新位置
        }
        return len;
    }
}
