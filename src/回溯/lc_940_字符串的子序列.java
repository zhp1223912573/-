package 回溯;

import java.util.ArrayList;


/**
 * @author zhp
 * @date 2022-06-13 15:43
 *
 */
public class lc_940_字符串的子序列 {

    public static void func(String str){
        char [] chars = str.toCharArray();
        process(chars,0,new ArrayList<Character>());

    }

    /**
     * 二进制遍历，要和不要当前字符，直到字符串末尾，输出形成的字符串
     * @param chars
     * @param i
     * @param characters
     */
    private static void process(char[] chars, int i, ArrayList<Character> characters) {

        if(i==chars.length){
            for(Character character : characters){
                System.out.print(character);
            };
            System.out.println();
            return ;
        }

        ArrayList<Character> needCurChar =(ArrayList<Character>) characters.clone();
        needCurChar.add(chars[i]);
        process(chars,i+1,needCurChar);
        ArrayList<Character> noneedCurChar =(ArrayList<Character>) characters.clone();
        process(chars,i+1,noneedCurChar);
    }

    public static void main(String[] args) {
        func("123");
    }
}
