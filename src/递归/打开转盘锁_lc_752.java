package 递归;

import sun.security.krb5.internal.tools.Klist;

import java.util.*;

/**
 * @author zhp
 * @date 2022-07-19 15:07
 * https://leetcode.cn/problems/open-the-lock/solution/da-kai-zhuan-pan-suo-by-leetcode-solutio-l0xo/
 */
public class 打开转盘锁_lc_752 {
    /**
     * 广搜
     * 尝试初始字符串"0000"到target字符串的最短步骤数，显然，使用广搜就可以。
     * 为了实现目标，需要解决尝试过程中是否出现了死亡字符串的尝试结果，或者重复构造以及出现过的字符串，
     * 所以我们需要一个dead和seen的set集合数据结果保存死亡字符串以及已经出现过的字符串，某个字符串
     * 不属于上述两个集合，那么就可以加入队列中进入旋转尝试，直到得到目的字符串target
     * 需要注意特殊情况：deadends存在"0000"，直接返回0，无法构成
     */
    public int openLock(String[] deadends, String target) {
        if("0000".equals(target)){
            return 0;
        }
        Set<String> dead = new HashSet<>();//死亡字符串
        Set<String> seen = new HashSet<>();//已经旋转出来的字符串
        Queue<String> queue = new LinkedList<>();//尝试队列
        for(String str:deadends){
            dead.add(str);
        }
        //特殊情况
        if(dead.contains("0000")){
            return -1;
        }
        seen.add("0000");
        queue.add("0000");
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            step++;//旋转次数增加
            for(int i=0;i<size;i++) {
                String poll = queue.poll();
                //获取当前字符串旋转后的所有可能
                for (String next : getNext(poll)) {
                    //既不是死亡数字，也没有出现过
                    if (!dead.contains(next) && !seen.contains(next)) {
                        //得到了最少旋转次数得到的目标字符串
                        if (next.equals(target)) {
                            return step;
                        }
                        seen.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
     }

    private List<String> getNext(String poll) {
        char[] chars = poll.toCharArray();
        List<String> ans = new ArrayList<>();
        //对四位字符分别尝试前旋转和后旋转
        for(int i=0;i<4;i++){
            char num = chars[i];
            //前旋
            chars[i] = numPre(num);
            //放入当前尝试结果
            ans.add(new String(chars));
            //后旋
            chars[i] = numPost(num);
            //放入当前尝试结果
            ans.add(new String(chars));
            //复原字符串
            chars[i] = num;
        }
        return ans;
    }

    private char numPost(char num) {
        //如果为’9‘,后旋需要返回0
        return num=='9'?'0':(char)(num+1);
    }

    private char numPre(char num) {
        return num=='0'?'9':(char)(num-1);
    }


    /**
     * 启发式搜索
     * https://leetcode.cn/problems/open-the-lock/solution/da-kai-zhuan-pan-suo-by-leetcode-solutio-l0xo/
     */
}
