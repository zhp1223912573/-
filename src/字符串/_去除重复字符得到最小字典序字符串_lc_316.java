package 字符串;


import java.util.Stack;

/**
 * @author zhp
 * @date 2022-07-08 20:33
 *
 *给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并让
 * 最终结果字符串的字典序最小
 * 【举例】
 * str = "acbc"，删掉第一个'c'，得到"abc"，是所有结果字符串中字典序最小的。
 * str = "dbcacbca"，删掉第一个'b'、第一个'c'、第二个'c'、第二个'a'，得到"dabc"，
 * 是所有结 果字符串中字典序最小的
 */
public class _去除重复字符得到最小字典序字符串_lc_316 {

    /**https://leetcode.cn/problems/remove-duplicate-letters/submissions/
     * 为了得到最小字典序的字符串，需要实现以下3个目标：
     * 1.去重
     * 2.不能打乱原本字符的相对顺序
     * 3.在符合第2点的条件下，得到最小字典序字符串
     *
     * 我们利用栈来实现整个算法
     * 首先，按顺序压入原本字符，如果已经在栈中的字符不需要重复压入栈，最后输出栈中元素。
     *      如：bcac 压入栈在输出得到：bca
     * 但是，很明显答案不符合字典序最小的要求，最小应为bac，
     *      所以每当我们压入一个字符时，如果栈顶元素大于压入元素，我们应该先弹出，再压入即将入栈的元素，
     *      让弹出的元素在后续的过程中再次入栈。
     * 然而，我们发现栈弹出并逆序得到的答案是ac，少了b这个字符，分析发现，b只出现过一次，不应该出栈，出栈
     *      会使我们丢失这个字符。
     * 所以，我们应该再添加一个记录数组，记录每个字符出现的次数，每遍历一次字符，就应该--，并且如果该
     *          字符只剩下一次，那我们无论如何都不应该弹出他。
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        if(s==null || s.length()<1){
            return "";
        }

        int count[] = new int[256];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)]++;
        }

        boolean inStack [] = new boolean[256];
        Stack<Character> stack  = new Stack<Character>() ;
        for(char ch : s.toCharArray()){
            count[ch] --;

            if(inStack[ch]) continue;

            while(!stack.isEmpty() && stack.peek()>ch){
                //只剩下一次，不应该弹出
                if(count[stack.peek()]==0){
                    break;
                }

                inStack[stack.pop()] = false;

            }
            inStack[ch] = true;
            stack.push(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }
}
