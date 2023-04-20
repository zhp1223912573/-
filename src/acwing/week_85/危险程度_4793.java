package acwing.week_85;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-01-08 5:34
 * https://www.acwing.com/problem/content/4796/
 */
public class 危险程度_4793 {
    static int union[] = new int[100];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        for(int i=1;i<=n;i++){
            union[i]=i;
        }

        while((m--)>0){
            int a,b;
            a=scanner.nextInt();
            b=scanner.nextInt();
            union[find(a)]=find(b);
        }

        long res=1;
        for(int i=1;i<=n;i++){
            if(union[i]!=i) res*=2;
        }
        System.out.println(res);
    }

    private static int find(int b) {
        if(union[b]!=b)
            union[b] = find(union[b]);
        return union[b];
    }
}
