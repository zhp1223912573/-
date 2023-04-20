package 测试.回朔笔试真题;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-30 13:21
 * 项目规划【华为】
 * H公司在做项目规划，当前3个团队(前端、后端、测试)共同规划完成M个项目，这时候给你3个团队各自人力总和值(XXX人月)。
 * 对于某个项目，项目都需要多个团队共同投入完成，每个项目会有一个预估的价值(XXX万元)，
 * 同时一个项目都需要多个团队共同投入完成(每个项目会有对三个团队的人力需求数量)。
 * 让你在多个项目中做项目规划，在人力允许的范围内，使得能够承接的所有项目的预估价值总和最大。
 * 备注:返回结果为能够承接的最大预估价值。如果人力无法承接任何项目,返回0;
 * 解答要求
 * 时间限制:C/C1000ms,其他语言:2000ms内存限制:CIC 256MB.其他语言:512MB
 * 输入
 * 项目个数:m (0<m<= 20);
 * 三个团队人力总和:S1,S2,S3 (0<SI <= 1000)
 * 每个项目预估价值:V1,V2. V3, ... Vn; (O<M<=1000000)
 * 每个项目所需人力:{ P11,P12, P13),(P21, P22,P23 )1. {P31, P32,P33.., (Pm1,Pm2, Pm3);(0<= Pxy <= 1000)
 * 输出
 * 最大价值:MaxValue
 * 样例1
 * 输入:2
 * 100 100 100
 * 10000 8000
 * 60 60 60
 * 60 60 60
 * 输出:10000
 * 解释:
 * 2:2个项目
 * 100 100 100:三个团队各自的人力总和
 * 10000,8000:项目的预估价值(1个项目一个输入,多个项目会有多个输入)
 * 60 60 60:第一个项目对于三个团队的人力需求
 * 60 60 60:第二个项目对于三个团队的人力需求
 * 10000:输出项目规划的结果
 */
public class 项目规划 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int nums[] = new int[3];
        for(int i=0;i<3;i++){
            nums[i] = scan.nextInt();
        }
        int value[] = new int[n];
        for(int i=0;i<n;i++){
            value[i] = scan.nextInt();
        }
        int [] project1 = new int[n];
        int [] project2 = new int[n];
        int [] project3 = new int[n];
        for(int i=0;i<n;i++){
            project1[i] = scan.nextInt();
            project2[i] = scan.nextInt();
            project3[i] = scan.nextInt();
        }

        System.out.println(dfs(0,nums[0],nums[1],nums[2],project1, project2,project3,value));
    }

    //选或不选当前项目
    static int dfs(int index,int l1,int l2,int l3,int []p1,int []p2,int[] p3,int []value){
        if(index==value.length) return 0;

        int nochoose = dfs(index+1,l1,l2,l3,p1,p2,p3,value);
        int choose = 0;

        int c1 = p1[index];
        int c2 = p2[index];
        int c3 = p3[index];
        //当前项目能够被完成
        if(c1<=l1&&c2<=l2&&c3<=l3){
            //选择当前项目后得到的金额
            choose = dfs(index+1,l1-c1,l2-c2,l3-c3,p1,p2,p3,value)+value[index];
        }
        return Math.max(nochoose,choose);
    }
}
