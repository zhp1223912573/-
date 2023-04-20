package 测试;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-28 19:53
 */
public class Main {
    static int min =Integer.MAX_VALUE;
    public static void main(String args[]){
        int a = 1;
        int b = ++a;
//        int c =
        System.out.println(b);
        System.out.println(a);
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long k = scan.nextInt();
        long height[] = new long[n];
        long reward[] = new long[n];
        for(int i=0;i<n;i++){
            height[i] = scan.nextLong();
        }
        for(int i=0;i<n;i++){
            reward[i] = scan.nextLong();
        }
        dfs(0,height,reward,k,0);
        System.out.print(min==Integer.MAX_VALUE?-1:min);
    }

    static void dfs(int index,long height[],long reward[],long curK,int count){
        if(index==height.length){
            min = Math.min(count,min);
            return;
        }
        if(curK<height[index]) return;

        dfs(index+1,height,reward,curK,count);
        dfs(index+1,height,reward,curK+=reward[index],++count);
    }
}
