package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-24 23:22
 * https://leetcode.cn/problems/generate-parentheses/
 */
public class 括号生成_lc_22 {
    /**
     * 暴力法+回溯
     * 生成所有的括号序列，判断该序列是否合法，合法则加入返回答案啊
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        generate(new char[n * 2], 0, ans);
        return ans;

    }

    public void generate(char[] chars, int pos, List<String> ans) {
        if (pos == chars.length) {
            if (isValid(chars)) {
                ans.add(new String(chars));
            }
        } else {
            chars[pos] = '(';
            generate(chars, pos + 1, ans);
            chars[pos] = ')';
            generate(chars, pos + 1, ans);
        }
    }

    private boolean isValid(char[] chars) {
        int balance = 0;
        for (char ch : chars) {
            if (ch == '(') {
                balance++;
            }
            if (ch == ')') {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }

    /**
     * 回溯
     * 上述暴力法开销太大，此方法中通过对当前字符串的情况进行分析，来合理放置'(',')',
     * 使最后生成的序列一定是合法的.
     * <p>
     * 设置left,right表示左右括号数量，通过分析左右括号的数量，来决定当前应该放说明符号，
     * 如果当前左右符号一致，必须放置左括号，
     * 如果当前左符号小于右符号，则放置左还是右符号都可行，但是必须保证左符号数量足够
     */
    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        generate1(ans, n, n, "");
        return ans;
    }

    private void generate1(List<String> ans, int left, int right, String str) {
        // 左右括号使用完
        if (left == 0 && right == 0) {
            ans.add(str);
            return;
        }

        if (left == right) {
            generate1(ans, left - 1, right, str + "(");
        } else if (left < right) {
            if (left != 0) {
                generate1(ans, left - 1, right, str + "(");
            }

            generate1(ans, left, right - 1, str + ")");
        }


    }

}
