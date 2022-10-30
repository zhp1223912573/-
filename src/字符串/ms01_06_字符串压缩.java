package 字符串;

/**
 * @author zhp
 * @date 2022-10-20 21:37
 * https://leetcode.cn/problems/compress-string-lcci/submissions/
 */
public class ms01_06_字符串压缩 {
    public String compressString(String S) {
        if(S.length()==0) return "";
        StringBuilder sb = new StringBuilder();
        char ch = S.charAt(0);
        int cnt = 1;
        for(int i=1;i<S.length();i++){
            if(S.charAt(i)!=ch){
                sb.append(ch);
                sb.append(String.valueOf(cnt));
                ch = S.charAt(i);
                cnt=0;
            }
            cnt++;
        }

        sb.append(ch);
        sb.append(cnt);
        return S.length()<=sb.length()?S:sb.toString();
    }
}
