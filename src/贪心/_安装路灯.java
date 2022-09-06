package 贪心;

/**
 * @author zhp
 * @date 2022-07-06 15:58
 * <p>
 * 小Q正在给一条长度为n的道路设计路灯安置方案。
 * 为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要
 * 照亮的障碍物格子用'X'表示。小Q现在要在道路上设置一些路灯, 对于安置在
 * pos位置的路灯, 这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。
 * 小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需
 * 要多少盏路灯。
 * 输入描述：
 * 输入的第一行包含一个正整数t(1 <= t <= 1000), 表示测试用例数
 * 接下来每两行一个测试数据, 第一行一个正整数n(1 <= n <= 1000),表示道路
 * 的长度。第二行一个字符串s表示道路的构造,只包含'.'和'X'。
 * 输出描述：
 * 对于每个测试用例, 输出一个正整数表示最少需要多少盏路灯
 */
public class _安装路灯 {
    /**
     * 典型的贪心问题
     * 为了使灯数量最少，则安装灯时现需要让灯照到尽可能多的方块
     * 所以每当出现依次.时，都直接安装灯，我们只考虑当前位置之后的情况，默认之前的情况已经符合条件，
     * 所以我们都需要直接让指针加3跳到灯的照射范围外，
     */
    public static int minLight(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        int light = 0;
        int index = 0;
        while (index < str.length()) {
            //直接跳过不需要照射的方块
            if (str.charAt(index) == 'X') {
                index++;

            } else {//来到需要照亮的方块
                //"X.X....X" 来到第2个位置时，直接安装灯，但是该灯安装在第3个位置，所以2，3，4都被照射，我们直接跳转到第5个位置
                light++;
                index += 3;
            }

        }
        return light;
    }


        public static void main (String[]args){
            System.out.println(minLight("XX.XX.X.X.X.X.XX.XXX.X.XXX.XX"));
        }

}
