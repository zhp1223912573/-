package 数学;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhp
 * @date 2022-07-14 20:01
 * https://leetcode.cn/problems/count-primes/submissions/
 */
public class 计算质数_lc_204 {
    /**
     * 直接计算 会超时
     * @param n
     * @return
     */
    public static int countPrimes(int n) {
        int ans=0;
        for(int i=2;i<n;i++){
            if(isPrime(i)){
                ans++;
            }
        }

        return ans;
    }

    public static boolean isPrime(int n){
        if(n==1){
            return false;
        }

        if(n==2){
            return true;
        }

        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }

        return true;
    }

    /**埃氏筛
     * 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,一定不是质数，因此我们可以从这里入手。
     *
     * 我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。
     * 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），
     * 即 0，这样在运行结束的时候我们即能知道质数的个数。
     */
    public static int countPrimes1(int n){
        //记录坐标为i的数值是否为质数
        boolean nums[] = new boolean[n] ;

        int index = 2;
        while(index<n){
            int times = 2;
            if(nums[index]) {
                index++;
                continue;
            }
            while(times*index<n){
                nums[times*index] = true;
                times++;
            }
            index++;
        }

        int ans=0;
        for(int i=2;i<n;i++){
            if(nums[i]==false){
                ans++;
            }
        }

        return ans;

    }

    /**线性筛
     * 埃氏筛其实还是存在元余的标记操作，比如对于45这个数，它会同时被3,5两个数标记为合数，因此我们
     * 优化的目标是让每个合数只被标记一次，这样时间复杂度即能保证为0()，这就是我们接下来要介绍的线性
     * 筛。
     * 相较于埃氏筛，我们多维护一个primes数组表示当前得到的质数集合。我们从小到大遍历，如果当前的数x
     * 是质数，就将其加入primes数组。
     * 另一点与埃氏筛不同的是，「标记过程」不再仅当x为质数时才进行，而是对每个整数x都进行。对于整数
     * ,我们不再标记其所有的倍数x·x,x·(a+1),,而是只标记质数集合中的数与x相乘的数，即·
     * primeso,·primes1,.,且在发现x mod primes,=0的时候结束当前标记。
     * 核心点在于：如果x可以被primesi整除，那么对于合数y=·primesi+1而言，它一定在后面遍历到
     * primesi·primesi+1这个数的时候会被标记，其他同理，这保证了每个合数只会被其「最小的质因数」筛去，
     * 即每个合数教被标记一次
     */
    public static int countPrimes2(int n){
        //保存已有的质数
        List<Integer> primes = new ArrayList<>();
        int nums[] = new int[n];
        Arrays.fill(nums,1);

        for(int i=2;i<n;i++){
            if(nums[i]==1){
                primes.add(i);
            }
            for(int j=0;j<primes.size()&&i*primes.get(j)<n;j++){
                nums[i*primes.get(j)]=0;
                if(i%primes.get(j)==0){
                    break;
                }
            }
        }
        return primes.size();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(countPrimes(5000000));
        long mid = System.currentTimeMillis();
        System.out.println("方法1耗时："+(mid-start));
        System.out.println(countPrimes1(5000000));
        long end = System.currentTimeMillis();
        System.out.println("方法2耗时："+(end-mid));
        start = System.currentTimeMillis();
        System.out.println(countPrimes2(5000000));
        System.out.println("方法3耗时："+(start-end));


    }
}
