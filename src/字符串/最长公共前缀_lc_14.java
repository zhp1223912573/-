package 字符串;

import java.util.Arrays;

/**
 * @author zhp
 * @date 2022-07-16 15:15
 * https://leetcode.cn/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
 */
public class 最长公共前缀_lc_14 {
    /**
     * 纵向比较
     * 比较每列的字符，一致就继续，不一致就返回
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length<1){
            return null;
        }

        for(int i=0;i<strs[0].length();i++){
            char ch = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++){
                if(i==strs[j].length() || ch!=strs[j].charAt(i)){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    /**
     * 排序，比较首尾字符串
     */
    public String longestCommonPrefix1(String[] strs){
        if(strs == null || strs.length<1){
            return null;
        }

        Arrays.sort(strs);
        String first = strs[0];
        String end = strs[strs.length-1];
        int min = Math.min(first.length(),end.length());
        int i;
        for( i=0;i<min;i++){
            if(first.charAt(i)!=end.charAt(i)){
                break;
            }
        }
        return first.substring(0,i);
    }

    /**
     * 分而治之
     * 将求解最长公共前缀的问题分解为小问题，在合并起来求解
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs){
        if(strs ==null || strs.length==0){
            return "";
        }else{
            return longestCommonPrefix2(strs, 0,strs.length-1);
        }
    }
    /**
     * 分
     */
    private String longestCommonPrefix2(String[] strs, int left, int right) {
        if(left==right){
            return strs[left];
        }else{
            int mid = (right-left)/2+left;
            String leftlcp = longestCommonPrefix2(strs,left,mid);
            String rightlcp = longestCommonPrefix2(strs,mid+1,right);
            return commnPriex(leftlcp,rightlcp);

        }
    }
    /**
     * 查找相同字符前缀
     */
    private String commnPriex(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    /**
     * 二分查找
     * 最长公共前缀的长度一定小于等于最短字符串的长度，设该长度为min
     * 我们将二分查找最短字符串，并与其他所有字符串比较，如果一致那说明可能还存在更长的字符串，
     * 向右查找，反之向左查找
     * @param strs
     * @return
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String strs[] = {"abc","b"};
        Arrays.sort(strs);
        for(String string : strs){
            System.out.println(string);
        }
    }
}
