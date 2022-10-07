package 递归.先后手问题;


/**
 * @author zhp
 * @date 2022-07-02 19:15
 * 有两只羊，羊每次吃4的幂次方的草（1，4，16....)，
 * 现在给定一定的草，判断是先手吃草的羊获胜，还是后手吃草的羊获胜。
 */
public class _吃草先后问题 {
    /**
     * 典型的一道博弈论问题，双方根据目前的情况都能做出最优选择,判断获胜者
     *
     * 首先，如果出现0，1，2，3，4，5棵草的情况，那么就可以直接得出获胜者
     * 其次，如果当前无法判断吃多少草可以获胜，那么需要遍历所有可以吃的情况下能获胜的情形，
     *        这需要通过递归来实现
     * 最后，如果当前情况下无法获胜，那么说明必输，则需要返回‘后手’表示自己的失败
     *
     * 细节：注意递归过程中可能出现数值溢出的情况，需要特别判断
     */
    public static String func(int glass){
        if(glass<5){

            if(glass==0 || glass==2){//如果是0，说明对方已经把草吃完了，则对手获胜，如果是3，只能吃1次，那么对手也必胜
                return "后手";
            }else{//如果是1，3，4那么当前吃草的羊可以吃完或者保证一定获胜的局面出现，所以当前的羊获胜
                return "先手";
            }
        }

        int base = 1;
        //当前羊从一开始进行尝试，直到出现自己能获胜的局面出现，或者自己获胜的局面不存在
        while(base<=glass){
           //如果当前尝试吃的草量可以使自己获胜，则退出循环
            if(func(glass-base).equals("后手")){//这里为什么是等于“后手”时退出循环，因为后手的后手就是当前调用函数的先手
                return "先手";
            }
            if(base>glass/4){//防止溢出
                break;
            }
            base*=4;
        }

        return "后手";
    }

    /**
     * 规律总结
     * 根据上述方法进行打表，发现结果有一定规律，每五个出现都是“后先后先先”的结果，利用此规律可以直接输出
     */
    public static String funcbyguilv(int glass){
        return "";
    }


    public static void main(String[] args) {
//        int glassnum = 99;
//        long cur =  System.currentTimeMillis();


//        System.out.println("当前有"+glassnum+"棵草,获胜的是"+func1(glassnum));
//        long now = System.currentTimeMillis();
//        System.out.println("花费时间："+(now-cur));
//        for (int i = 0; i <50 ; i++) {
//            System.out.println("当前有"+i+"棵草,获胜的是"+func(i));
//        }
        //System.out.println("当前有"+glassnum+"棵草,获胜的是"+func(glassnum));
//        cur = System.currentTimeMillis();
//        System.out.println("花费时间："+(cur-now));

    }
}
