package 数学;

/**
 * @author zhp
 * @date 2022-10-28 13:54
 * https://leetcode.cn/problems/elimination-game/submissions/
 */
public class 消除游戏_lc_390 {
    /**
     * 我们来看下上面的代码，直接看可能不太直观，我们可以把n想象成一个长度为n的数组，数组的元
     * 素 是 1,2,3,4,5……n ， 我 们 只 需 要 记 录 下 每 次 删 除 一 遍 之 后 数 组 的 第 一 个 元 素 即 可 ， 当
     * remaining==1的时候就会退出while循环，最后返回数组的仅有的一个元素即可（这只是我们的
     * 想象，实际上操作的并不是数组，也没有删除，只是记录，但原理都类似
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        //代码direction判断是否是从左往右删除，如果为true表示的是从左往右删除，如果为false表示的是从右
        //往左删除。
        boolean direction = true;
        //代 码 remaining 表 示 剩 余 的 个 数 。 step 表 示 每 次 删 除 的 间 隔 的 值 ， 不 是 间 隔 的 数 量 ， 比 如
        //1,2,3,4,5,6,7,8。第一次从左往右删除的时候间隔值是1，删除之后结果为2,4,6,8。第二次从右往左
        //删除间隔值就变为2了，删除之后结果是2,6。然后第3次就变成4了。head表示的是记录的剩余数
        //字从左边数第一个的值。
        int remain = n;
        int head =1;
        int step = 1;


        while(remain>1){
            //如果是从这边循环，那么第一个肯定是会被删除的，第二个会成为
            //head，而第二个值就是head+step；如果从右边开始循环，如果数组的长度是奇数，那么第一个
            //元素head也是要被删除的，所以head值也要更新，代码remaining&1==1判断remaining是否是
            //奇数
            if(direction|| (remain&1)==1){
                head += step;
            }
            //remaining表示的是剩余个数，每次删除的时候都会剩余一半，所以除以
            //2，也可以表示为往右移一位。step上面说了表示的是间隔值，每次循环之后都会扩大一倍，left就
            //不在说了，一次往左一次往右……一种这样循环。
            remain>>=1;
            direction = !direction;
            step += step;
        }
        return head;
    }
}
