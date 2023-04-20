package 测试;


import java.util.Scanner;

/**
 * @author zhp
 * @date 2023-03-27 20:54
 */
public class test {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
        test test = new test();
        for(int i=2;i<10;i++){
            int fun = test.fun(3);

            System.out.println(fun);
            System.out.println(test.dp(i));
        }


    }

    int nums[];
    int sum;
    public int fun (int n) {
        // write code here
        nums = new int[]{0,1,2};
        sum=0;
        for(int i=0;i<3;i++){
            dfs(1,nums[i],0,n);
        }

        return sum;
    }
    void dfs(int index,int pre,int preVal,int n){
        sum+=preVal;
        if(index==n){
            return ;
        }

        for(int i=0;i<=2;i++){
            if(pre==nums[i]) continue;

            dfs(index+1,nums[i],preVal+Math.abs(i-pre),n);
        }
    }

    int dp[][];
    int dp(int n){
        dp = new int[2][3];
        for(int i=2;i<=n;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(j==k) continue;
                    if(i%2==0)
                        dp[1][j] += (dp[0][k]%1000000007 + Math.abs(j-k)*(i-1))%1000000007;
                    else
                        dp[0][j] += (dp[1][k]%1000000007 + Math.abs(j-k)*(i-1))%1000000007;
                }
            }
        }
        int index = 0;
        if(n%2!=0){
            index=1;
        }
        int sum =0;
        for(int i=0;i<3;i++){
            sum= (sum+dp[1-index][i])% 1000000007;
        }
        return sum;
    }



    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
          this.val = val;
        }
      }

    class Info{
        int val;
        TreeNode node;

        Info(){};
        Info(int val,TreeNode node){
            this.val=val;
            this.node = node;
        }
    }
    public TreeNode valueOfTree (TreeNode root) {
        // write code here
        Info info  = getNewTree(root);
        return info.node;
    }
    Info getNewTree(TreeNode root){
        if(root==null){
            return new Info(-1,null);
        }
        Info left = getNewTree(root.left);
        Info right = getNewTree(root.right);

        TreeNode newRoot = new TreeNode(-1);
        newRoot.left = left.node;
        newRoot.right = right.node;

        int val = ((left.node!=null?left.val:1)*(right.node!=null?right.val:1)*root.val)%1000000007;
        int zero = getZero(val);
        newRoot.val=zero+left.val+right.val;
        if(zero!=0) val%=(zero*10);
        return new Info(val,newRoot);
    }
    int getZero(int num){
        int zero=0;
        while(num%10==0){
            num/=10;
            zero++;
        }
        return zero;
    }

    
}
