package 测试.动规真题.互为倍数的子集;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-09 13:11
 * 互为倍数的子集【米哈游】难度：4
 * 米小游拿到了一个集合（集合元素互不相等）。
 * 她想知道，该集合有多少元素数量大于1的子集，满足子集内的元素两两之间互为倍数关系？
 * 结果可能很大，请对109+7取模。
 * 输入描述
 * 第一行输入一个正整数n，代表集合大小。
 * 第二行输入n个正整数ai，代表集合元素。
 * 1≤n≤105
 * 1≤ai≤106
 * 输出描述
 * 一个整数，代表满足题意的子集的数量。
 *
 * 示例1
 * 输入
 * 5
 * 1 2 3 4 5
 * 输出
 * 6
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int nums[] = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = scan.nextInt();
        }
        System.out.println(dp(n,nums));
        System.out.println(dp1(n,nums));
    }

    /**
     * dp[i]保存以第i个元素结尾的子集的数量
     * dp[i]=1+sum(dp[j]) (nums[i]是nums[j]的倍数）
     * 时间复杂度为O(n^2)，显然超时
     * @param n
     * @param nums
     * @return
     */
    static int dp(int n,int nums[]){
        int dp[] = new int[n];
        //先排序
        Arrays.sort(nums);
        for(int i=1;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(nums[i]%nums[j]==0){
                    dp[i] += (1+dp[j]);
                }
            }
        }
        int ans = 0;
        for(int i=0;i<n;i++){
            ans += dp[i];
        }
        return ans;
    }

    /**该方法不是自己想的，看了别的解答才写出来。
     * 上述方法的问题在于需要遍历第i个元素前的所有元素，当他们互为倍数的时候才可以使倍数集合增加。
     * 如果我们提前对要遍历的元素nums[i]的所有因数进行获取，那么就可以避免第i个元素前的所有元素的遍历与判断。
     * 时间开销为O(n√n）
     */
    static int dp1(int n,int nums[]){
        Arrays.sort(nums);
        int dp[] = new int[nums[nums.length-1]+1];
        //第一个数没有比它更小的因子，直接置为1
        //暂时把一个元素构成的集合当成是符合条件的集合，后续再减去即可
        dp[nums[0]] = 1;
        for(int i=1;i<n;i++){
            int[] factors = getFactors(nums[i]);
            for(int factor:factors){
                if(factor!=nums[i]){
                    dp[nums[i]] += dp[factor];
                }
            }
            //把当前元素自己也当做一种集合
            dp[nums[i]]+=1;
        }

        int ans = 0;
        for(int num:dp){
            ans += num;
        }
        return ans-n;
    }

    static int[] getFactors(int num){
        List<Integer> facotors = new ArrayList<>();
        for(int i=1;i*i<=num;i++){
            if(num%i==0){
                facotors.add(i);
                if(num/i!=i){
                    facotors.add(num/i);
                }
            }
        }
        return facotors.stream().mapToInt(Integer::intValue).toArray();
    }
}
