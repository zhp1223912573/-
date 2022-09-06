package 字符串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-16 20:22
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class 颠倒字符串里的单词_lc_151 {
    /**
     * 读取字符串到栈，再弹出即可。
     */


    /**
     * API
     */
    public String reverseWords(String s) {
        //省取前置，后置空格
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));//去除多个空格
        Collections.reverse(list);

        return String.join(" ",list);
    }
}
