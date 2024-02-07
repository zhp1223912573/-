package 字符串;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zhp
 * @date 2021-11-08 12:06
 */
/*https://leetcode-cn.com/problems/reverse-words-in-a-string/ */
public class lc_151 {
    @Test
    public void test(){
        String hello_world = reverseWords("hello world");
        System.out.println(hello_world);

    }
    public String reverseWords(String s) {
        /*直接利用Java内置API */
        //去除前置后置空白
        // s=s.trim();
        // // 正则匹配连续的空白字符作为分隔符分割
        // List<String> wordList=Arrays.asList(s.split("\\s+"));
        // Collections.reverse(wordList);
        // return String.join(" ",wordList);

        /*利用双端队列可以放置在队列前的特性 可以使后面的单词放在前面
          实现反转的效果*/
        //取出前置后置空白
        // int left=0,right=s.length()-1;
        // while(left<=right&&s.charAt(left)==' '){
        //     left++;
        // }
        // while(left<=right&&s.charAt(right)==' '){
        //     right--;
        // }

        // Deque<String> d=new ArrayDeque<>();
        // StringBuffer word=new StringBuffer();

        // while(left<=right){
        //     char c = s.charAt(left);
        //     if((word.length()!=0)&&c==' '){
        //         d.offerFirst(word.toString());
        //         word.setLength(0);
        //     }else if(c!=' '){
        //         word.append(c);
        //     }
        //     left++;
        // }

        // //把最后一个单词加入
        // d.offerFirst(word.toString());

        // return String.join(" ",d);

        /*从0构建 */
        StringBuilder sb=trimSpace(s);
        reverse(sb,0,s.length()-1);
        reverseWord(sb);
        return sb.toString();

    }

    //去除多余空格
    public StringBuilder trimSpace(String s){
        int left=0,right=s.length()-1;
        //去除前后空白
        while(left<=right&&s.charAt(left)==' '){
            left++;
        }
        while(left<=right&&s.charAt(right)==' '){
            right--;
        }

        StringBuilder sb = new StringBuilder();
        while(left<=right){
            if(s.charAt(left)!=' '){
                sb.append(s.charAt(left));
            }else if(sb.charAt(sb.length()-1)!=' '){ //去除单词间的多余空白
                sb.append(' ');
            }
            left++;
        }

        return sb;
    }

    //反转字符串
    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }





    //反转单词
    public void reverseWord(StringBuilder sb){
        int n=sb.length();
        int start=0,end=0;

        while(start<n){
            //找到当前单词的尾巴
            while(end<n&&sb.charAt(end)!=' '){
                end++;
            }

            reverse(sb,start,end-1);
            start=end+1;
            end++;
        }
    }
}
