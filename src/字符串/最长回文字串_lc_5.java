package 字符串;


/**
 * @author zhp
 * @date 2022-07-16 16:31
 * https://leetcode.cn/problems/longest-palindromic-substring/
 *  * 1.经典解法，中心扩散发。O(n^2)
 *  * 2.Manacher. O(n)
 * *        manager是在经典算法的基础上进行扩展的，同样需要对字符进行扩展处理。
 */
public class 最长回文字串_lc_5 {
    /**
     * 经典的dp
     * 使用范围模型，dp[i][j]表示字符串i-j范围内以i开头，j结尾的回文字符串长度
     * 初始化情况：
     *      只有一个字符时，该字符一定构成一个回文串，dp[i][i] = 1;
     *      只有两个字符时，如果两个字符一致，那么构成回文字符串。dp[i][i+1]=2
     * 可能性分析：
     *      对于字符串i-j,如果i+1--j-1构成一个回文字符串（即dp[i+1][j-1]不等于0),
     *      当str[i] == str[j]时，dp[i][j] = dp[i+1][j-1] +2
     *       当str[i] ！= str[j]时，dp[i][j] = 0
     * 结果:
     *      去所有可能中的最大值，并记录位置
     */
    public static String longestPalindrome(String s) {
        if(s==null || s.length()==0){
            return "";
        }

        int n =s.length();
        int dp[][] = new int[n][n];
        int end = 0;
        int max = 1;
        dp[n-1][n-1]=1;
        for(int i=0;i<n-1;i++){
            dp[i][i]=1;
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1] = 2;
                max = 2;
            }
        }


        for(int i=n-3;i>=0;i--){
            for(int j=i+2;j<n;j++){
                if(dp[i+1][j-1]!=0 && s.charAt(i)==s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] +2;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(dp[i][j]==max){
                    end = j;
                }
            }
        }


        return s.substring(end-max+1,end+1);
    }

    /**
     * 扩展中心法
     * 回文字符串必定是对称的，所以我们每次循环可以选择一个中心，进行左右扩展，判断左右字符是否相等即可
     * 由于存在奇数的字符串和偶数的字符串，所以我们需要从一个字符开始扩展，或者从两个字符之间开始扩展，
     * 所以总共有 n+n-1 个中心。
     *
     * @param s
     * @return
     */
//    public static String longestPalindrome1(String s){
//        if(s==null || s.length()==0){
//            return "";
//        }
//        int start =0;
//        int end =0;
//        int n = s.length();
//        for(int i=0;i<n;i++){
//            int len1 = expandArounCenter(s,i,i);//选择以当前访问位置为中心，该回文串为奇数个
//            int len2 = expandAroundCenter(s,i,i+1);//选择以两个相邻数中间的空隙为中心，改回文串为偶数个
//            int len = Math.max(len1,len2);
//            if(len>end-start){//回文串长度大于过去的
//                start = i - (len-1)/2;//i表示当前位置，减去一半长度就是起始位置
//                end = i + len/2;//i表示当前位置，加上一半长度就是结尾位置
//            }
//        }
//        return s.substring(start,end+1);
//    }

    /**
     * 中心比较字符是否一致
     */
//    private static int expandArounCenter(String s, int left, int right) {
//        int L = left;
//        int R = right;
//        while(L>=0 && R<=s.length() && s.charAt(L)==s.charAt(R)){
//            L--;
//            R++;
//        }
//
//        return R-L-1;
//    }


    /**manacher算法
     * 基本思路：
     * 在经典算法的基础上进行扩展，
     * 记录下每个遍历的字符的最长半径范围R为数组，最大半径范围数组的中心点C。
     * 每当R更新，C才更新。R不变，C不变。
     * 通过记录以及访问的字符为中心的最长半径范围，我们可以对后续的字符的半径计算过程进行缩减，
     * 从而不必要像经典算法一样对每个字符都进行一次中心扩散的操作（虽然本质上，我们还是需要进行中心扩散
     * 但是，通过记录已经遍历过的字符串的半径范围，我们可以对处于该范围内的字符进行“跳转”的操作，避免了
     * 对所有相邻字符的判断，使算法时间复杂度达到O(n))
     *
     * 伪代码：
     * //1221 -> #1#2#2#1#
     * s -> 处理串 str
     * char[] str
     *
     * int[] pArr = new int [str.length]
     * int R = ?
     * int C = ?
     *
     * for(int i=0; i<str.lenght;i++){
     *     if(i在R外部){
     *         从i开始往两边暴力扩
     *     }else{(i在R内部)
     *         if(i'回文区域彻底在L..R以内）{
     *             pArr[i] = 某个O(1)表达式
     *         }else if(i'回文区域有一部分在L..R以外）{
     *             pArr[i] = 某个O(1)表达式
     *         }else{//i'回文区域和L..R的左边界压线
     *             从R之外的字符开始，往外扩，然后确定
     *             第一步扩失败了，R不变
     *             否则，R变大
     *         }
     *     }
     * }
     * @param str
     * @return
     */
    public static int maxLcsLength1(String str){
        if(str==null ||  str.length()<1){
            return -1;
        }

        char [] chars = manacher(str);//1221-> #1#2#2#1#
        int [] parr = new int[chars.length];//回文半径数组
        int C = -1; //中心
        int R = -1; //回文右边界的再往右一个位置，最右的有效区是r-1位置
        int max = Integer.MIN_VALUE;//扩展出来的最大值
        for(int i=0;i!=chars.length;i++){//求每一个位置的回文半径
            //i至少的回文半径，先给parr[i]
            //如果r大于i，说明i处于半径范围内，那么当前字符可以跳转的位置取决于i'的半径和r-i的大小
            //             取i’半径和r-i的更小值，这样就达到了根据已有的回文半径减少了重复的对称字符的比较
            //              该情况容纳了处于r范围内的三种情况
            //反之r小与i，说明i处于半径范围外，应当无脑扩展，但为了缩减代码量，这里将这种情况直接归为1
            //          他会直接退出后续循环，区寻找最大半径
            parr[i] = R>i ? Math.min(parr[2*C-i],R-i):1;

            while(i+parr[i] <chars.length && i -parr[i]>-1){//保证回文字符串不越界
                if(chars[i+parr[i]] == chars[i-parr[i]]){//对称字符相等
                    parr[i]++;//以当前字符为中心的回文字符长度半径+1
                }else{
                    break;//不等直接退出，无须继续比较
                }
            }

            //每次对以一个字符为中心的回文字符长度计算完成后，都需要判断是否需要更新字符R与C
            if(i+parr[i]>R){
                R = i + parr[i];
                C = i;
            }
            max = Math.max(max,parr[i]);//记录最大值
        }
        //#1#2#2#1#
        //观察上述字符，可发现，max为5，但最长回文字符串长度为5，按照规律要将max-1再返回
        return max-1;
    }

    /**字符串扩展处理函数
     * 将字符串"121121"扩展为"#1#2#1#1#2#1#"的形式，
     * 补充的符号不一定是'#',也可以是与元字符串中的字符相同的字符，不会影响结果
     * @param str
     * @return
     */
    public static char [] manacher(String str){
        char[] charArray = str.toCharArray();
        char[] array = new char[2*str.length()+1];
        int index = 0;

        for(int i=0;i<2*str.length()+1;i++){
            array[i] = (i&1)==0? '#' : charArray[index++];
        }
        System.out.println(array);
        return array;
    }

    /**经典最长回文串长度算法
     * 以遍历到的每一位为中心，向两端扩散，直到出现不对称的情况，就停止扩散
     * 在对每一个字符遍历完成后，就能得到一个最大值max
     * 该max是在填充‘#’后的数值，我们需要将max-1,得到最长回文串长度
     * @param str
     * @return
     */
    public static int maxLcsLength(String str){
        if(str == null || str.length()<1){
            return -1;
        }

        int max = Integer.MIN_VALUE;

        char[] manacher = manacher(str);
        for(int i=0;i<manacher.length;i++){
            int left = i-1;
            int right = i+1;
            int   lcs = 1;
            while(left>=0 && right<manacher.length) {
                if (manacher[left] == manacher[right]) {
                    lcs++;
                    left--;
                    right++;
                }else{
                    break;
                }
            }
            if(max<lcs){
                max = lcs;
            }
        }
        return max-1;
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }
}
