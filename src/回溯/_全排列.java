package 回溯;

import java.util.ArrayList;

/**
 * @author zhp
 * @date 2022-06-13 15:57
 */
public class _全排列 {
    public static ArrayList<String> Permutation(String str){
        ArrayList<String> res = new ArrayList<>();
        if(str == null || str.length()==0){
            return res;
        }

        char [] chars = str.toCharArray();
         process(chars, 0,res);
        return res;
    }

    /**
     * chars[i..] i后的字符都可以放在i位置上
     * @param chars
     * @param i
     * @param res
     * @return
     */
    private static void process(char[] chars, int i, ArrayList<String> res) {
        if(i==chars.length){
            res.add(String.valueOf(chars));
        }

        for(int j=i;j<chars.length;j++){
            swap(chars,i,j);
            process(chars,i+1,res);
            swap(chars,i,j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] =  chars[j];
        chars[j] = temp;
    }
}
