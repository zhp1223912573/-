package 图.并查集;

import com.sun.javafx.geom.Arc2D;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhp
 * @date 2023-04-17 16:49
 * https://leetcode.cn/problems/accounts-merge/
 * 涉及到合并操作的题目都可以考虑并查集
 * 该题目需要处理的条件相对比较多
 */
public class 账户合并_lc_721 {
    int parent[];
    void init(int n){
        parent = new int[n];
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }
    int find(int x){
        return x==parent[x]? x : (parent[x]=find(parent[x]));
    }
    void union(int x,int y){
        parent[find(x)] = find(y);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        init(n);

        //因为用户名称无法作为区分所有用户的唯一标识，我们使用用户在accounts的位置来表示该用户的id，用id区别用户
        //先完成所有邮箱到账号的映射，如果某个邮箱已经存在对应的账号id，那么说明这两个id是属于同一用户的
        Map<String,Integer> email_accId = new HashMap<>();
        //读取所有账号，完成映射
        for(int accId =0;accId<n;accId++){
            int m = accounts.get(accId).size();
            for(int i =1;i<m;i++){//跳过名称，直接获取用户邮箱
                String email = accounts.get(accId).get(i);
                //当前邮箱第一次出现
                if(!email_accId.containsKey(email)) email_accId.put(email,accId);
                //第二次出现，合并用户
                else union(accId,email_accId.get(email));
            }
        }

        //完成映射关系，开始获取账户id到对应邮箱的映射
        Map<Integer,List<String>> accId_emails = new HashMap<>();
        //获取每一条邮箱
        for(String email:email_accId.keySet()){
            //得到邮箱所属的根id
            int accId = find(email_accId.get(email));
            //将改邮箱加入该id的所有邮箱内
            if(accId_emails.containsKey(accId)) accId_emails.get(accId).add(email);
            else{
                List<String> emails = new LinkedList<>();
                emails.add(email);
                accId_emails.put(accId,emails);
            }
        }

        List<List<String>> res = new LinkedList<>();
        for(int accId:accId_emails.keySet()){
            Collections.sort(accId_emails.get(accId),((o1,o2)->{return o1.compareTo(o2);}));
            List<String> tmp = new LinkedList<>();
            tmp.add(accounts.get(accId).get(0));
            for(String email:accId_emails.get(accId)) tmp.add(email);
            res.add(tmp);
        }
        return res;
    }
}
