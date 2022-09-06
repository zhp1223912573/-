package 栈和队列;

import java.awt.*;
import java.util.*;

/**
 * @author zhp
 * @date 2022-07-28 12:25
 * https://leetcode.cn/problems/basic-calculator/
 */
public class 基本计算器II_lc_227 {
    /**基于双栈的计算表达式实现
     *
     * 参考博客：
     * https://leetcode.cn/problems/basic-calculator-ii/solution/shi-yong-shuang-zhan-jie-jue-jiu-ji-biao-c65k/
     *
     * 下述代码是借鉴上述链接博客内的，可以解决带括号的加减乘除运算基本表达式，
     * 把此段代码看作模板，理解记忆下来。
     *
     */
    //对运算符号进行优先级记录
    Map<Character,Integer> map = new HashMap<Character, Integer>(){{
        put('+',1);
        put('-',1);
        put('*',2);
        put('/',2);
        put('%',2);
        put('^',3);
    }};
    public int calculate(String s) {
        //操作数栈
        Deque<Integer> nums = new ArrayDeque<>();
        //符号栈
        Deque<Character> ops = new ArrayDeque<>();
        //去除表达式内的所有空格
        s = s.replaceAll(" ", "");
        int n = s.length();
        char[] cs = s.toCharArray();
        nums.add(0);//先往操作数栈内添加0，避免第一个数就是负数的情况

        for(int i=0;i<n;i++){
            char ch = cs[i];
            if(ch=='('){  //左括号直接加入
                ops.add('(');
            }else if(ch==')'){  //右括号括号需要递归计算，知直到遇到左括号
                while(!ops.isEmpty()){
                    if(ops.peekLast()!='('){
                        cal(nums,ops);
                    }else{
                        //遇到了'('，要取出来
                        ops.pollLast();
                        break;
                    }
                }
            }else{//剩下的是数字和其他运算
                    if(isNumber(ch)){//读取数字并存入nums
                            int num =0;
                            int j = i;
                            while(j<n&&isNumber(cs[j])){
                                 num = num*10+cs[j++]-'0';
                            }
                            nums.add(num);
                            i = j-1;//移动的数字后
                    }else{//其他运算符

                        /*为防止 () 内出现的首个字符为运算符，将所有的空格去掉，
                         并将 (- 替换为 (0-，(+ 替换为 (0+
                         （当然也可以不进行这样的预处理，将这个处理逻辑放到循环里去做）*/
                        if(i>0&&(cs[i-1]=='('||cs[i-1]=='+'||cs[i-1]=='-')){
                            nums.add(0);
                        }

                        //如果当前ops栈顶的运算符优先级大于等于要押入栈的运算符，那么可以先进行计算
                        while(!ops.isEmpty()&&ops.peekLast()!='('){
                            char pre =  ops.peekLast();//得到栈顶运算符
                            if(map.get(pre)>=map.get(ch)){
                                cal(nums,ops);
                            }else{
                                break;
                            }
                        }
                        //最后加入该运算符
                        ops.addLast(ch);
                    }
            }
        }
        //计算没有算完的运算符
        while(!ops.isEmpty()) cal(nums,ops);
        //nums保存的最后数值就是我们要的值
        return nums.peekLast();
    }

    private boolean isNumber(char ch) {
        return Character.isDigit(ch);
    }

    /**
     * 具体计算过程
     * @param nums
     * @param ops
     */
    private void cal(Deque<Integer> nums, Deque<Character> ops) {
        //nums为1时，无法进行二元运算，直接返回
        if(nums.isEmpty()||nums.size()<2){
            return;
        }
        if(ops.isEmpty()){
            return ;
        }

        int b = nums.pollLast();
        int a= nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;

        if(op=='+'){
            ans = a+b;
        }else if(op=='-'){
            ans = a-b;
        }else if(op=='*'){
            ans = a*b;
        } else if(op=='/'){
            ans = a/b;
        }else if(op=='^'){
            ans = a^b;
        }else if(op=='%'){
            ans = a%b;
        }
        nums.addLast(ans);
    }

}
