package 数组;

/**
 * @author zhp
 * @date 2022-07-27 0:23
 */
public class Excel表列序号_lc_171 {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for(int i=0;i<columnTitle.length();i++){
            ans += columnTitle.charAt(i)-'A'+1;
            if(i!=columnTitle.length()-1){
                ans*=26;
            }
        }
        return ans;
    }
}
