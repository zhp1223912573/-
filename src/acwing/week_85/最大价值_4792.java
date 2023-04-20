package acwing.week_85;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-01-08 5:18
 * https://www.acwing.com/problem/content/4795/
 */
public class 最大价值_4792 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.next();
        int k = scanner.nextInt();
        int weight[] = new int[26];
        int max = -1;
        for(int i=0;i<26;i++){
            weight[i]=scanner.nextInt();
            max = max<=weight[i]?weight[i]:max;
        }
        int val = 0;
        for (int i = 0; i <S.length() ; i++) {
            val+= (i+1)*weight[S.charAt(i)-'a'];
        }

        int count = (S.length()*2+1+k)*k;
        val += (count/2)*max;
        System.out.println(val);
    }
}
