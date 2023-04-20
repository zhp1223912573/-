package 测试.百度面试.小红的排列;

import java.util.Scanner;
import java.util.zip.Checksum;

/**
 * @author zhp
 * @date 2023-03-27 22:40
 * 小红拿到了一个排列,她想知道有多少区间满足,区间内部的数构成一个排列?
 * 排列的定义:1到k，每个数都出现过且恰好出现1次,被称为长度为k的排列。例如[2,1,3],[4,3,2,1]都是排列。
 * 输入描述
 * 有多组数据，首先输入一个正整数T，表示有T组数据。
 * 每组数组的第一行输入一个正整数n，代表排列的大小。
 * 每组数据的第二行输入n个正整数ai，代表小红拿到的排列。
 * 1<=n <= 2*10^5
 * 保证所有的数组n的总和不超过2*10^5
 * 输出描述
 * 输出一个正整数，代表多少区间能构成一个排列。
 *
 * 示例1
 * 输入
 * 2
 * 5
 * 1 2 3 4 5
 * 5
 * 2 1 5 3 4
 * 输出
 * 5
 * 3
 * 说明
 * 第一组数据，[1],[1,2],[1,2,3],[1,2,3,4],[1,2,3,4,5]共有5个排列
 * 第二组数据，[2,1],[1],[2,1,5,3,4]共有3个排列
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for(int i=0;i<n;i++){
            int m = scan.nextInt();
            int nums[]  = new int[m];
            //记录数值1的位置
            int index = -1;
            for(int j=0;j<m;j++){
                nums[j] = scan.nextInt();
                if(nums[j]==1) index = j;
            }
            int count = 1;
            int sum = 1;
            int left = index;
            int right = index;
            for(int i1 = 0;i1<m-1;i1++){

                if(left-1>=0&&right+1<m){
                    int min = nums[right+1]>nums[left-1]?left-1:right+1;
                    sum+=nums[min];
                    int a = min==left-1?left--:right++;
                }
                else if(left-1>=0){
                    left--;
                    sum+=nums[left];
                }else if(right+1<m){
                    right++;
                    sum+=nums[right];
                }

                int len = right-left+1;
                int expected = (1+len)*len/2;

                if(sum==expected){
                    count++;
                }
            }

            System.out.println(count);

        }

    }

}
