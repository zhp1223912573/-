package 博弈论;

/**
 * @author zhp
 * @date 2022-07-10 20:57
 * 给定一个非负数组，每一个值代表该位置上有几个铜板。a和b玩游戏，a先手，b后手，
 * 轮到某个人的时候，只能在一个位置上拿任意数量的铜板，但是不能不拿。谁最先把铜
 * 板拿完谁赢。假设a和b都极度聪明，请返回获胜者的名字
 *
 * nim博弈论：
 *  始终留给后手异或和为0的局面，则先手必赢
 */
public class _nim {
    /**
     * 经典的nim博弈论问题
     *   始终留给后手异或和为0的局面，则先手必赢
     *   如果开始时先手面对的数组异或和为0，则先手必输，
     *   反之只要先手始终保证留给后手的异或和为0，则先手必赢。
     *   可以举例检验
     */
    // 保证arr是正数数组
    public static void printWinner(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        if (eor == 0) {
            System.out.println("后手赢");
        } else {
            System.out.println("先手赢");
        }
    }
}
