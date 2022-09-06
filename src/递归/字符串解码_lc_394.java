package 递归;

import java.util.LinkedList;

/**
 * @author zhp
 * @date 2022-07-20 1:31
 * https://leetcode.cn/problems/decode-string/
 */
public class 字符串解码_lc_394 {
    /**递归
     * 遇到'['就递归，遇到']'就回收。
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        return decode(s);
    }
    public static String decode(String s){

        String ret = "";
        int times = 0;
        for(int i=0;i<s.length();i++){
            //是数字
            if(Character.isDigit(s.charAt(i))){
                times *=10 ;
                times+=(s.charAt(i)-'0');
            }
            //发现左括号递归求解
            else if(s.charAt(i)=='['){
                //得到括号内的字符串
                String inside=decode(s.substring(i+1));
                //根据括号前的次数进行转码
                for(int j=0;j<times;j++){
                    //得到真正的num[str]转码的字符串
                    ret +=inside;
                }

                //移动当前指针到'['对应的']'后
                int count = -1;
                while(count!=0){
                   char ch =  s.charAt(++i);
                    if(ch==']'){
                        count++;
                    }else if(ch=='['){
                        count--;
                    }
                }

                times=0;


            }else if(s.charAt(i)==']'){
                return ret;
            }else{//普通字符直接加入字符串
                ret+=s.charAt(i);
            }
        }
        return  ret;
    }

    /**
     * 官方题解递归
     * @param s
     * @return
     */
    static int ptr;//全局指针
    static String src;//遍历的字符串
    public static String decodeString1(String s){
        ptr = 0;
        src = s;
        return getString();
    }
    public static String  getString(){
        if(src.length()==ptr||src.charAt(ptr)==']'){
            return "";
        }

        char cur = src.charAt(ptr);
        int repT = 1;
        String ret = "";
        if(Character.isDigit(cur)){
            repT = getDigit();
            //跨过左括号
            ptr++;
            String string = getString();
            //跨过右括号
            ptr++;
            while(repT>0){
                ret += string;
            }
        }else if(Character.isLetter(cur)){
            ret = String.valueOf(src.charAt(ptr++));
        }
        return  ret + getString();
    }
    public static int getDigit(){
        int ret = 0;
        while(ptr<src.length()&&Character.isDigit(src.charAt(ptr))){
            ret = ret*10 + src.charAt(ptr++)-'0';
        }
        return ret;
    }

    /**
     * 辅组栈
     */
    public String decodeString2(String s){
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()){
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }else if(c==']'){
                int cur_multi = stack_multi.removeLast();
                StringBuilder tmp = new StringBuilder();
                for(int i=0;i<cur_multi;i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }else if(c>='0'&&c<='9'){
                multi = multi*10 + Integer.parseInt(c+"");
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = decodeString("3[a]2[bc]");
        System.out.println(s);
    }

}
