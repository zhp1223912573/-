package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2023-03-29 16:53
 * https://leetcode.cn/problems/restore-ip-addresses/
 */
public class 复原IP地址_lc_93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans  = new ArrayList<>();

        dfs(s,0,0,new String[4],ans);
        return ans;
    }

    boolean check(String s){
        return (s.charAt(0)!='0' || s.equals("0")) && Integer.parseInt(s)<256;
    }


    String getString(String[] segement){
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<3;i++){
            sb.append(segement[i]+".");
        }
        sb.append(segement[3]);
        return sb.toString();
    }

    void dfs(String s, int index, int segementId, String[] segement, List<String> ans){
        if(index==s.length()||segementId==4){
            if(index==s.length()&&segementId==4){
                ans.add( getString(segement));
            }
            return;
        }

        for(int i=1;i<=3;i++){
            if(i+index>s.length()) return;
            String substring = s.substring(index,index+i);
            if(check(substring)){
                segement[segementId] = substring;
                dfs(s,index+i,segementId+1,segement,ans);
            }
        }

    }
}
