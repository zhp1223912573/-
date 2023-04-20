package acwing.寒假daily;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-01-09 0:37
 * https://www.acwing.com/problem/content/4655/
 */
public class 纸张尺寸_4652 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        int num= s.charAt(1)-'0';
        int a = 1189;
        int b = 841;
        for(int i=0;i<num;i++){
            int c = Math.max(a,b);
            int d = Math.min(a,b);
            c/=2;
            a=Math.max(c,d);
            b=Math.min(c,d);
        }

        System.out.println(a);
        System.out.println(b);
    }
}
