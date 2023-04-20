package acwing.week_85;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-01-08 5:18
 * https://www.acwing.com/problem/content/4794/
 */
public class 死或生_4791 {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int arr[] = new int[3];
        for(int i=0;i<n;i++){
            int t = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            arr[t] += x-y;
        }

        for(int i=1;i<=2;i++){
            if(arr[i]>=0) System.out.println("LIVE");
            else System.out.println("DEAD");
        }
    }
}
