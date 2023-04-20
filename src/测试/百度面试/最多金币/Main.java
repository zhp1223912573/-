package 测试.百度面试.最多金币;


import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-27 20:56
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();


        long coins[] = new long[n];
        long left = 0;
        long right = 0;
        for(int i=0;i<n;i++){
            coins[i] = scan.nextLong();

        }
        if(coins[k]==0){
            System.out.println(0);
        }
        //计算k位置左侧可到达的位置的总数和
        for(int i=k;i<n;i++){
            if(coins[i]==0)  break;

            left+=coins[i];

            if(coins[i]==1) break;

        }
        //计算k位置右侧可到达的位置的总数和
        for(int i=k-2;i>=0;i--){
            if(coins[i]==0) break;

            right+=coins[i];
            if(coins[i]==1) break;
        }
        //分类讨论：
        // 1.k位置数值为1则输出左右总数和的较大值，因为k为1只能选择一边移动
        if(coins[k-1]==1) {
            System.out.println(Math.max(left,right)+coins[k-1]);
            return;
        }
        //2.k位置数值为2直接返回左右总数和之和
        System.out.println(left+right+coins[k-1]);
    }
}
