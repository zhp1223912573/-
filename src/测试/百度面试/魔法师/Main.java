package 测试.百度面试.魔法师;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-28 11:00
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int nums[] = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = scan.nextInt();
        }

        int dp1[] = new int[n];
        int dp2[] = new int[n];

        dp1[0] = nums[0]>0?1:0;
        dp2[0] = nums[0]<0?1:0;

        int w = dp1[0];
        int b = dp2[0];

        for(int i=1;i<n;i++){
            if(nums[i]<0){
                dp1[i] = dp2[i-1];
                dp2[i] = dp1[i-1]+1;
            }else if(nums[i]>0){
                dp1[i] = dp1[i-1]+1;
                dp2[i] = dp2[i-1];
            }
            w+=dp1[i];
            b+=dp2[i];
        }
        System.out.println(w+" "+b);
    }
}
