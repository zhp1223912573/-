package 回溯;

/**
 * @author zhp
 * @date 2022-06-13 19:50
 * 1对应"A",2对应"B"....
 * 给定一个数字字符串，将该字符转化为字母字符串的方法有几种
 * 111--- "AAA","KA","AK",三种
 *
 */
public class ConvertToLetterString {

    /**
     *
     * @param chars 要转化的字符串数组
     * @param i 当前转换到的位数
     * @return
     */
    public static int convertToLetterString(char []chars,int i){
        if(i==chars.length){//成功到达字符数组的最后一位，说明当前字符串符合要求
            return 1;
        }

        if(chars[i]=='0'){//单独的字符‘0’没有对应的字符，说明当前的转换方式不可能获得一个字符串
            return 0;
        }

        //当前字符为‘1’或‘2’，则可能与后续字符组合和新的字符
        if(chars[i]=='1'){
            int res = convertToLetterString(chars,i+1);//取当前字符进行转换
            if(i+1<chars.length)
                 res += convertToLetterString(chars,i+2);//取当前字符和后续字符进行转换
            return res;
        }else if(chars[i]=='2'){
            int res = convertToLetterString(chars,i+1);
            if(i+1<chars.length && chars[i+1]<='6' ){
                res += convertToLetterString(chars,i+2);
            }
            return res;
        }

        //当前字符串为其他情况，只能单独成一位
        return convertToLetterString(chars,i+1);
    }

    public static  int process(String str){
        char[] chars = str.toCharArray();
        return convertToLetterString(chars,0);
    }

    public static void main(String[] args) {
        System.out.println(process("111"));
    }
}
