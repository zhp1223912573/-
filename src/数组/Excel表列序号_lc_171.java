package 数组;

/**
 * @author zhp
 * @date 2022-07-27 0:23
 * https://leetcode.cn/problems/excel-sheet-column-number/
 */
public class Excel表列序号_lc_171 {

    /**
     * A-Z的26进制转换
     * @param columnTitle
     * @return
     */
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
