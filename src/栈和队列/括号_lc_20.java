package 栈和队列;

import java.util.Stack;

/**
 * @author zhp
 * @date 2021-11-16 11:17
 *
 */
public class 括号_lc_20 {
    public boolean isValid(String s) {
        if(s.length()%2==1) return false;

        Stack<Character> stack= new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='{'){
                stack.push('}');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='('){
                stack.push(')');
            }else if(stack.empty()||c!=stack.pop()){
                return false;
            }
        }
        if(stack.empty()) return true;
        return false;
    }
}
