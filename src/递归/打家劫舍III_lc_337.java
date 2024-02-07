package 递归;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-09-06 21:48
 * 树形dp问题
 */
public class 打家劫舍III_lc_337 {

   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    /**递归
     *偷或者不偷当前结点，递归所有情况，因为众多重复情形的出现，导致超时。
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return root.val;
        //偷当前结点
        int stealRoot = root.val;
        if(root.left!=null) stealRoot += rob(root.left.left)+rob(root.left.right);
        if(root.right!=null) stealRoot += rob(root.right.left)+rob(root.right.right);
        //不偷当前结点
        int noStealRoot = 0;
        noStealRoot += rob(root.left)+rob(root.right);
        return Math.max(noStealRoot,stealRoot);
    }

    /**记忆化递归
     *虽然优化了，但也同样超时
     * @param root
     * @return
     */
    public int rob1(TreeNode root){
        Map<TreeNode,Integer> memory = new HashMap<>();
        return rob1(root,memory);
    }
    public int rob1(TreeNode root, Map<TreeNode,Integer> memory){
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return root.val;
        //获取记录
        if(memory.get(root)!=null) return memory.get(root);
        //偷当前结点
        int stealRoot = root.val;
        if(root.left!=null) stealRoot += rob(root.left.left)+rob(root.left.right);
        if(root.right!=null) stealRoot += rob(root.right.left)+rob(root.right.right);
        //不偷当前结点
        int noStealRoot = 0;
        noStealRoot += rob(root.left)+rob(root.right);
        //记录
        memory.put(root,Math.max(noStealRoot,stealRoot));
        return Math.max(noStealRoot,stealRoot);
    }

    /**树形dp
     * 状态分析，
     * 每个结点只有被偷和不被偷的状态，而要找出最大的受益值，显然需要以整棵树的根节点判断整棵树的最大收益，
     * 所以采取后序遍历来获取最大收益值。
     * 而每个结点为根节点的最大收益取决于其左右子结点（也就是左右子树）的偷与不偷两种状态的最大收益，
     * 1.如果偷当前结点，那么该结点的最大收益为左右子结点不被偷的情况下的最大收益之和加上当前结点。
     * 2.如果不偷当前结点，那么该结点的最大收益为左右树的两者被偷或者不被偷的情况下的最大值之和。
     *
     */
        public int rob2(TreeNode root) {
            int[] ints = rob3(root);
            return Math.max(ints[0],ints[1]);
        }

        public int[] rob3(TreeNode root){
            int res[] = new int[2];
            if(root==null) return res;

            //获取当前结点的左右子树情况
            int left[] = rob3(root.left);
            int right[] = rob3(root.right);

            //第一种情况 偷当前结点
            res[0] = root.val+left[1]+right[1];
            //第二种情况 不偷当前结点
            res[1] = Math.max(left[0],left[1])+Math.max(right[0],right[1]);
            return res;
        }


    public int rob4(TreeNode root) {
        return dfs(root);
    }

    Map<TreeNode,Integer> map = new HashMap<>();
    public int dfs(TreeNode root){
        if(root==null) return 0;
        int choose = root.val;
        if(map.containsKey(root)) return map.get(root);
        if(root.left!=null){
            choose+=dfs(root.left.left)+dfs(root.left.right);
        }
        if(root.right!=null){
            choose+=dfs(root.right.left)+dfs(root.right.right);
        }
        int nochoose = dfs(root.left)+dfs(root.right);
        int value = Math.max(choose,nochoose);
        map.put(root,value);
        return value;
    }

    /**
     * @author zhp
     * @date 2022-07-21 16:06
     * https://leetcode.cn/problems/split-array-largest-sum/submissions/
     *
     *
     */
    public static class 分割数组的最大值_lc_410 {
        /**dp
         * 将数组分割为多个段...是动态规划的套路
         * 定义：
         *      dp[i][j]为数组0-i的位置上切割为j段的所有分组的最大值的最小值
         * 可能性分析:
         *        我们假定小于的i位置k可以分为j-1段，那么dp[i][j]等于max(dp[k][j-1],sub(k+1,i))
         *        sublist(k+1,i),表示k+1位置到i的前缀和，
         * 初始情况:
         *      dp[0][0]=0,
         *则dp[n][m]就是目标值
         *
         * 官方解答
         * https://leetcode.cn/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
         * @param nums
         * @param m
         * @return
         */
        public static int splitArray(int[] nums, int m) {
            int n = nums.length;
            int[][] f = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(f[i], Integer.MAX_VALUE);
            }
            int[] sub = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sub[i + 1] = sub[i] + nums[i];
            }
            f[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= Math.min(i, m); j++) {
                    for (int k = 0; k < i; k++) {
                        f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                    }
                }
            }
            return f[n][m];
        }


        /**贪心+二分
         * https://leetcode.cn/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
         *
         */
        public static int splitArray1(int[] nums, int m){

            int left = 0, right = 0;
            for (int i = 0; i < nums.length; i++) {
                right += nums[i];
                if (left < nums[i]) {
                    left = nums[i];
                }
            }
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (check(nums, mid, m)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public static boolean check(int[] nums, int x, int m) {
            int sum = 0;
            int cnt = 1;
            for (int i = 0; i < nums.length; i++) {
                if (sum + nums[i] > x) {
                    cnt++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            return cnt <= m;
        }

        /**
         * 二分
         * @param nums
         * @param k
         * @return
         */
        public int splitArray2(int[] nums, int k) {
            int l =0,r=(int)1e9;
            while(l<r){
                int mid = (r-l)/2+l;
                if(check1(nums,k,mid)) r = mid;
                else l=mid+1;
            }
            return r;
        }
        boolean check1(int nums[],int k,int mid){
            int cnt = 1;
            int cur = 0;
            for(int num:nums){
                if(num+cur<=mid){
                    cur+=num;
                }else{
                    if(num>mid) return false;
                    cnt++;
                    cur = num;
                }
            }
            return cnt<=k;

        }

        public static void main(String[] args) {
            int arr[] ={7,2,5,10,8};
            System.out.println(splitArray(arr,2));
        }
    }
}
