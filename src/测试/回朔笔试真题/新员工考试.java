package 测试.回朔笔试真题;

import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-30 14:23
 * 小聪入职新公司,参加线上的新员工必备考试,考试共25题，依次是10个判断题(每题2分)、10个单选题(每题4分)和5个多选题(每题8分)，总分100分。
 * 考题只能顺序作答,答对题目获得相应的分数，答错题目获得0分,考试系统不提示作答是否正确，答题过程中如果累积有3题答错,直接中止考试并计算考试分数。
 * 小聪考试结果是N分(0<=N<=100)，请根据小聪的分数,算出所有可能的答题情况的个数。
 * 解答要求
 * 时间限制: CIC1000ms,其他语言:2000ms
 * 内存限制: C/C256MB,其他语言:512MB
 * 输入
 * 整数,表示小聪的考试得分N,N为偶数0<=N<=100(N不会是不可能考出来的分数)。
 * 输出
 * 整数表示所有可能的答题情况的个数
 * 样例1
 * 输入:94
 * 输出:100
 * 解释:有1道判断题和1道单选题答错,其余的题都答对,所有可能的答题情况的个数为100,
 * 样例2
 * 输入:100输出:1
 * 解程:所有题目全部答对,答题情况的个数为1.
 */
public class 新员工考试 {
    static int  count=0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        dfs(0,100,n,0);
        System.out.println(count);
    }

    static void dfs(int index,int score,int finalScore,int error){
        if(error==3) return;
        if(index==25){
            if(score==finalScore){
                count++;
            }
            return ;
        }


        if(index<10){
            dfs(index+1,score,finalScore,error);
            if(score-2<finalScore) return;
            dfs(index+1,score-2,finalScore,error+1);
        }else if(index<20){
            dfs(index+1,score,finalScore,error);
            if(score-4<finalScore) return;
            dfs(index+1,score-4,finalScore,error+1);
        }else{
            dfs(index+1,score,finalScore,error);
            if(score-8<finalScore) return;
            dfs(index+1,score-8,finalScore,error);
        }
    }


}
