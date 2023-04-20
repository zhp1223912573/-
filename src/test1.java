import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-25 20:31
 */

public class test1{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int chocolate[] = new int[n];

        for(int i=0;i<n;i++){
            int num = scan.nextInt();
            chocolate[i] = num;
        }

        Arrays.sort(chocolate);

        int pre = 0;
        for(int i=0;i<n;i++){
            int num = chocolate[i];
            chocolate[i] = num*num+pre;
            pre = chocolate[i];
            System.out.print(chocolate[i]);
        }

        // for(int i=0;i<m;i++){
        //   long num = scan.nextLong();
        //   for(int j =0;j<n;i++){
        //     if(chocolate[j]>num){
        //       if(j!=0){
        //         System.out.print(j-1);
        //       }else{
        //         System.out.print(0);
        //       }
        //     }
        //   }
        // }


    }

}