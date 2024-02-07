package 接雨水;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhp
 * @date 2022-07-04 16:07
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * https://leetcode.cn/problems/trapping-rain-water/
 */
public class _接雨水1_lc_42 {
    /**题意分析
     *      * 可得：
     *      *  1.数组的最左和最右的上面都不可能容纳雨水，因为其左边和右边不存在屏障为他们保存雨水，
     *      *      所以我们只需要考虑除最左和最右位置上的存水
     *      *  2.分析数组每位上的存水量，需要考虑当前位置的左边最高屏障，以及右边最高屏障，
     *      *      只有依靠左右屏障才能在当前位置存储雨水
     *      *    可以得到如下公式
     *      *      左右最高中的最小值-当前位置的高度=当前位置上的存水量
     *
     *      依照上述题意有两种解题思路：
     *      1.dp,直接使用两个数组保存每个位置的左右最高屏障，这样的空间开销是O(n)
     *      2.数组.双指针，直接使用两个变量保存每个位置的左右最高屏障，在动态的移动过程中更新左右最高值，空间开销是O(1)
     *
     *      两种方法的时间开销都是O（n）
     */

    /**双指针解法
     *
     * 数组.双指针：
     * 左右指针L，R分别指向第二和倒数第二个数，同时保存两个变量maxl，maxr，记录目前出现的左右最大值，
     * 当maxl>maxr,说明r指向的位置可以确定存水量，因为，左侧还没遍历完就大于右侧最大值，可以直接确定
     * 反之亦然。
     * 当maxl=maxr，先移动哪个指针都可以
     *
     * 同属，每计算完移动指针的存水量，都要更新其所在一侧的最大值。
   *
     */
    public int trap(int [] height){
        if(height==null|| height.length<3){//小于三个直接返回
            return 0;
        }

        int maxl = height[0];
        int maxr = height[height.length-1];

        int l = 1;
        int r = height.length-2;

        int res = 0;

        while(l<=r){//直到两指针重和后才停止
            if(maxl<maxr){
                res += Math.max(0,maxl-height[l]);
                maxl = Math.max(maxl,height[l++]);
            }else{
                res += Math.max(0,maxr-height[r]);
                maxr = Math.max(maxr,height[r--]);
            }
        }

        return res;
    }

    /**动态规划解法
     *
     * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，
     * 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]。
     *
     * 朴素的做法是对于数组 height 中的每个元素，分别向左和向右扫描并记录左边和右边的最大高度，
     * 然后计算每个下标位置能接的雨水量。假设数组height的长度为 n，
     * 该做法需要对每个下标位置使用 O(n) 的时间向两边扫描并得到最大高度，
     * 因此总时间复杂度是 O(n^2)
     *
     * 上述做法的时间复杂度较高是因为需要对每个下标位置都向两边扫描。
     * 如果已经知道每个位置两边的最大高度，则可以在 O(n)O(n) 的时间内得到能接的雨水总量。
     * 使用动态规划的方法，可以在 O(n)的时间内预处理得到每个位置两边的最大高度。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode.cn/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public int trap2(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**单调栈解法
     * 官方解法演示：
     * https://leetcode.cn/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode-solution-tuvc/

     * 本题设计的是一个**单调递减栈**: 维护一个单调栈, 单调栈存储的是**下标**,
     * 满足从栈底到栈顶的下标对应的**数组 height 中的元素递减**.
     *
     * 我们来分析下单调栈**为什么可以满足此题的要求**.
     * 首先明确一点: **只有产生凹陷的地方才能存储雨水, 那么高度一定是先减后增**, 所以当我们遍历到 增 这个位置的时候,
     * 前面的减的地方(凹陷处)一定会存储雨水, 所以我们就将凹陷处出栈, 来计算它所能存储的雨水量.
     *
     * 用**算法思想来表述**就是: 从左到右遍历数组, 遍历到下标 i 时,
     * 如果栈内至少有两个元素, 记栈顶元素为top, top 的下面一个元素是 left,
     * 则一定有height[left]≥height[top]。如果height[i]>height[top], 则得到一个可以接雨水的区域. !
     *
     * 接下来就是**雨水量的计算**了, 这里有个**很大的区别**,
     * 之前的方法都是对于单独的 i 位置进行计算的, 但是单调栈在计算的时候是一个**长方形的区域**,
     * 是按照长度为1,2,3,4分别来求的, 这是最大的不同点, 理解可能有点困难, 我会在视频中详解的. !
     *
     * 该区域的**宽度**是i−left−1, **高度**是min(height[left],height[i])−height[top],
     * 根据宽度和高度即可计算得到该区域能接的雨水量.
     *
     * 算法运行的整个过程如下: !
     *
     * 在代码中有两个要注意的点: 一个是 **弹出栈顶后判断栈是否为空**, 因为当栈为空, 说明左边不存在最大值, 是无法接雨水的.
     */
    public int trap1(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }


}
