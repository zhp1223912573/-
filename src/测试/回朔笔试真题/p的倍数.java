package 测试.回朔笔试真题;

import sun.swing.PrintingStatus;

import java.beans.Beans;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-30 13:49
 * p的倍数【美团】
 * 小红拿到了一个正整数n,她可以进行若干次操作，每次操作将选择一个数位,使其加1或者减1。
 * 不过有两条限制:
 * 1. 每个数位最多只能操作一次。
 * 2. 如果选择的是9，则无法进行加1操作。如果选择的是0,则无法进行减1操作。
 * 小红希望最终n成为p的倍数，你能帮小红输出操作结束后的整数n吗?
 *
 * 输入描述
 * 两个正整数n和p。1≤n,p≤10^13
 * 输出描述
 * 如果误解，请输出-1。假设有多解的时候，输出任意解即可。（如果操作包含前导零，请将前导零一起输出）
 */
public class p的倍数 {
    static long ans = -1;
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long p = scan.nextLong();
        long ans = -1;
        int len  = 0;
        long num =n;
        while(n!=0){
            n/=10;
            len++;
        }
        dfs(num,p,len,0);
        System.out.println(ans);
    }
    static boolean dfs(long cur, long p,int n, int index){
        if(cur%p==0){
            ans = cur;
            return true;
        }
        if(index==n) return false;

        if(getCurdigit(cur,index)=='0'){
            if(dfs(cur+(long)Math.pow(10,index),p,n,index+1)){
                return true;
            }
        }
        else if(getCurdigit(cur,index)=='9'){
            if( dfs(cur-(long)Math.pow(10,index),p,n,index+1)){
                return true;
            }
        }
        else{
            dfs(cur+(long)Math.pow(10,index),p,n,index+1);
            dfs(cur-(long)Math.pow(10,index),p,n,index+1);
        }

        return false;
    }

    static char getCurdigit(long cur,int index){
        String s = String.valueOf(cur);
        return s.charAt(index);
    }
}
