package 字符串;

/**
 * @author zhp
 * @date 2022-07-14 12:19
 * https://leetcode.cn/problems/string-to-integer-atoi/solution/
 */
public class 字符串转数字_lc_8 {

    /**
     * 此题目不涉及复杂的算法，但是是一个具体详细的业务逻辑题。
     * 在做此类题时需要分析号题目要求，罗列需要考虑的点，再coding。
     *
     *   //curchar-'0'>-(Integer.MIN_VALUE%10）'-'号要放在外面，不然会出现转换丢失，
     *
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int index = 0;//遍历字符串所有字符

        //1.取出前导空格
        while(chars[index]==' '){
            index++;
        }

        int sign = 1;
        //2.判断符号
        if(chars[index]=='+'){
            index++;
        }else if(chars[index]=='-'){
            sign = -1;
            index++;
        }

        //3.开始计算具体数值
        int ans = 0;
        while(index<len){
            char curchar = chars[index];
            //当前字符非0-9,直接退出
            if(chars[index]<'0'||chars[index]>'9'){
                break;
            }

            //题目要求的数字必须使用int类型来保存，那么我们需要考虑溢出出现的情况
            if(ans>Integer.MAX_VALUE/10 ||(ans==Integer.MAX_VALUE/10 && ((curchar-'0')>Integer.MAX_VALUE%10))){
                return Integer.MAX_VALUE;
            }//curchar-'0'>-(Integer.MIN_VALUE%10）'-'号要放在外面，不然会出现转换丢失，
            else if(ans<Integer.MIN_VALUE/10 ||(ans==Integer.MIN_VALUE/10 && (curchar-'0'>-(Integer.MIN_VALUE%10)))){
                return Integer.MIN_VALUE;
            }

            ans *= 10;
            ans+=sign*(curchar-'0');

            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(Integer.MIN_VALUE));
        System.out.println(myAtoi("  -2147483647   "));
    }
}
