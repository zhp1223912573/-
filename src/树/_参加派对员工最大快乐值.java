package 树;

import java.util.List;

/**
 * @author zhp
 * @date 2022-06-15 14:29
 *
 * 树形dp问题
 */
public class _参加派对员工最大快乐值 {
    class Employee{
        public int happy;//带来的快乐值
        List<Employee> subordinates;//直接下级
    }

    class Info{
        public int laiMaxhappy;//该员工参加派对带来的最大快乐值
        public int buMaxhappy;//该员工不参加派对带来的最大快乐值

        public Info(int laiMaxhappy, int buMaxhappy) {
            this.laiMaxhappy = laiMaxhappy;
            this.buMaxhappy = buMaxhappy;
        }
    }

    public int maxHappy(Employee boss){
        Info info = process(boss);
        return Math.max(info.buMaxhappy,info.laiMaxhappy);
    }

    private Info process(Employee x) {
        if(x.subordinates==null){//底层员工，不存在下级
            return new Info(x.happy,0);
        }

        int lai = x.happy;
        int bu = 0;
        for(Employee next : x.subordinates){
            Info process = process(next);
            bu += Math.max(process.laiMaxhappy,process.buMaxhappy);//当前员工不在，那么最大快乐值取决于下一个员工来与不来
            lai += process.buMaxhappy;//当前员工来，那么最大快乐值取决于下一个员工不来的最大快乐
        }
        return new Info(lai,bu);
    }


}
