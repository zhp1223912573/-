package test;

/**
 * @author zhp
 * @date 2023-01-07 14:04
 *
 */
public class Solution {
    /**
     * n 个人一起玩狼人杀，按从 1 到 n 的顺序编号，据说，这群人当中有一个预言家。如果预言家真的存在，那么：
     *
     * 1）预言家不会信任任何人。
     *
     * 2）每个人（除了预言家）都信任预言家。
     *
     * 最多只有一个人同时满足 1 和 2 。
     *
     * 给定一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。如果预言家存在并且可以确定他的身份，请返回预言家的编号；否则，返回 -1 。
     *
     * 提示：
     *
     * 1 <= n <= 1000；0 <= trust.length <= 100；trust[i].length == 2；trust 中的所有trust[i] = [ai, bi] 互不相同；ai != bi；1 <= ai；bi <= n
     * @param n
     * @param trust
     * @return
     */
    public static int findProphet(int n, int[][] trust) {
            return trust[0].length!=0?trust[0][1]:-1;
    }

    /**
     * 【编程题】幼儿园有 N 个孩子玩游戏，随机围成了一个圈，老师最终想让所有男生排列到一起，所有女生排列到一起。每次老师可以命令两个孩子交换位置，求最小的命令次数：
     *
     * N<=100
     *
     * 语言限定：C++、Java、Golang
     *
     * i:
     * 3
     * FMF
     * o:
     * 0
     *
     * i:
     * 4
     * FMFM
     * o:
     * 1
     */
    public static int solution(int n, String s) {
        int countF = 0;
        int maxLength = 0;

        int length=0;
        int startindex=-1;
        int lastindex = -1;
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch=='F'){
                countF++;
                length++;
                if(maxLength<=length){
                    maxLength=length;
                    lastindex=i;
                    startindex=lastindex-maxLength+1;
                }

            }else{
                length=0;
            }
        }

        int i =0;
        int startlength =0;
        while(true){
            char ch = s.charAt(i);
            if(ch=='F'){
                startlength++;
                i++;
                if(i==n) break;
            }else{
                break;
            }

        }

        if(lastindex==n-1){
            return countF-startlength-maxLength;
        }

        if(startindex!=0){
            int left = countF-startlength-maxLength;
            if(n-lastindex-1>left){
                return startlength+left;
            }else{
                return left;
            }
        }else{
            return countF-maxLength;
        }
    }
    public static void main(String[] args) {
//        int[][] trust={{1,2}};
//        System.out.println(findProphet(1,trust));

        int change=solution(15,"FMMMMFMMFFFMMFF");
        System.out.println(change);

    }
}
