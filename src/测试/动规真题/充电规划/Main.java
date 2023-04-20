package 测试.动规真题.充电规划;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-04-08 13:28
 * 充电规划【华为】难度：4
 * 张三购买了一辆续航里程数达1000公里的某自动驾驶新能源车。
 * 某天车辆充满电后,需从甲城出发前往距离D公里远的乙城,全程走高速。
 * 车载导航提示沿途有N个休息站均可提供充电服务，各休息站均可实时提供当前充电排队时间(小时)。
 * 请协助规划时间最优的休息站充电方案,返回最短的旅行用时。
 * 为方便计算,高速上的行驶速度固定为100公里/小时。
 * 规划时可不必考虑保留安全续航里程数，汽车可以将电完全用光,1000公里续航的汽车
 * 按100公里/小时,可以开10个小时。
 * 每次充电时间固定为1小时,完成后电量充满。
 * 各站点充电排队时间不会变化,充电排队过程不耗电。
 * 解答要求
 * 时间限制:C/C++1000ms,其他语言:2000ms
 * 内存限制:C/C++ 256MB,其他语言:512MB
 * 输入
 * 第一行表示甲乙两城的距离D,单位为公里;第二行表示沿途的休息站数量N;
 * 第三行起,每行2个数据,分别表示休息站距离甲城的距离，以及充电排队所需时间(小时),(各休息站按距离从近到远排序）
 * 0<=D<=1000000, D是100的整数倍0<=N=10000
 * 样例1
 * 输入:
 * 1500
 * 4
 * 300 2
 * 600 1
 * 1000 0
 * 1200 0
 * 输出:16
 * 解释:最佳方案:只在第3个休息站(位置1000)进行充电
 * 1500公里的行程耗时:15小时
 * 充电排队0小时,充电1小时最快旅程总计花费16小时
 * 其他方案:在第2个休息站(位置600)进行充电,总计花费17小时
 * 其他方案:在第2个休息站(位置600)和第4个休息站(位置1200)进行充电，总计花费19小时
 * 样例2
 * 输入:
 * 800
 * 2
 * 300 0
 * 600 0
 * 输出:8
 * 解释:最佳方案:不进任何休息站充电
 * 800公里的行程耗时:8小时
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int d = scan.nextInt();
        int n = scan.nextInt();
        int nums[][] = new int[n][2];
        for(int i=0;i<n;i++){
            nums[i][0] = scan.nextInt();
            nums[i][1] = scan.nextInt();
            //存在电站距离大于最大续航运行距离，直接放回
            if(i!=0){
                if(nums[i][0]-nums[i-1][0]>1000){
                    System.out.println(-1);
                    return;
                }
            }
        }

        //最后一个电站与终点距离过大，无法到达
        if(nums[n-1][0]-d>1000){
            System.out.println(-1);
            return;
        }

        //方法1 dfs
        System.out.println(dfs(0,0,10,0,d,nums));

        //方法2 dp
        System.out.println(reversedp(d,n,nums));


    }

    /**
     *
     * @param index 第index个电站
     * @param cur   当前所在位置
     * @param left  剩余电量
     * @param count 总计耗时
     * @param dest  目的地位置
     * @param nums 电站位置记等待时间
     * @return
     */
    static int dfs(int index,int cur,int left,int count,int dest,int nums[][]){
        //如果剩余电量可以直接到达终点，那么直接返回所需要的时间
        if(left*100+cur>=dest){
            return count+(dest-cur)/100;
        }

        // 如果剩余电量连下一个电站都无法到达，直接返回 || 如果到达了最后一个电站还没直接返回，说明当前的选择无法到达终点
        if(index<nums.length && nums[index][0]-cur>left*100 || index==nums.length){
            return Integer.MAX_VALUE;
        }

        //可以到达当前电站，那么我们分别尝试在当前站桩充与不充电

        //到达当前电站消耗的时间
        int time = (nums[index][0]-cur)/100;

        //充电
        int charge = dfs(index+1,nums[index][0],10,count+1+nums[index][1]+time,dest,nums);
        //不充电
        int nocharge = dfs(index+1,nums[index][0],left-time,count+time,dest,nums);

        return Math.min(charge,nocharge );

    }


    /**该方法思路不是自己想的！！！
     * 基于上述dfs，设置dp数组保存状态，从正向进行推导的思路没有理清，写不出来。
     * 下属解法是参考答案的。
     * 基于反方向的dp数组。
     * @param d
     * @param n
     * @param nums
     * @return
     */
    static int reversedp(int d,int n,int nums[][]){
        int dp[][] = new int[n+1][10001];

        //从最后的电站往前推导
        for(int j=0;j<=1000;j++){
            int dis = d-nums[n-1][0];
            if(j>=dis){//当前电池续航可以到达终点，直接到达
                dp[n][j] = dis/100;
            }else{//到不了最后面，需要在当前电站充电
                dp[n][j] = dis/100+1+nums[n-1][1];
            }
        }
        for(int i=n-1;i>=1;i--){
            //上一点站到当前电站的距离
            int dis = nums[i][0]-nums[i-1][0];
            for(int j=0;j<=1000;j++){
                if(j>=dis){//如果当前续航可以到达下一电站，考虑是否在当前电站充电
                    dp[i][j] = Math.min(dp[i+1][j-dis],dp[i+1][1000-dis]+1+nums[i-1][1])+dis/100;
                }else{//如果当前续航不能到达下一电站，只能充电
                    dp[i][j] = dp[i+1][1000-dis]+1+nums[i-1][1]+dis/100;
                }
            }
        }

//        //最后处理起始位置到达第一个电站的续航关系,与上述步骤一致，只不过我们没有设置第0个电站（也就是起始位置），所以没法在上面的循环进行推导
//        int dis = nums[0][0];
//        for(int j=0;j<=1000;j++){
//            //上一点站到当前电站的距离
//            if(j>=dis){//如果当前续航可以到达下一电站，考虑是否在当前电站充电
//                dp[0][j] = Math.min(dp[1][j-dis],dp[1][1000-dis]+1+0)+dis/100;
//            }else{//如果当前续航不能到达下一电站，只能充电
//                dp[0][j] = dp[1][1000-dis]+1+0+dis/100;
//            }
//        }

        //只需要考虑到达第一个站点时剩余对应续航即可
        return dp[0][1000] = dp[1][1000-nums[0][0]]+nums[0][0]/100;
    }


}
