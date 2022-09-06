package 字符串;

/**
 * @author zhp
 * @date 2021-11-09 10:38
 */
/*
* KMP的经典思想就是:当出现字符串不匹配时，可以记录一部分之前已经匹配的文本内容，利用这些信息避免从头再去做匹配。
* 所以如何记录已经匹配的文本内容，是KMP的重点，也是next数组肩负的重任
*
*https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0028.%E5%AE%9E%E7%8E%B0strStr.md
* */
public class KMP {
    /**一般版本
    * 使用next数组来充当一个前缀表
    * 前缀表是用来回退的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配。
    *
    *1.初始化
     2.处理前后缀不相同的情况
     3.处理前后缀相同的情况
    *
    * */
    public static boolean  getNext(int next[],String s){
        //若输入参数不符合规定 则输出false
        if(next.length<0||next==null||s==null||s.length()<1)
            return false;

        char[] chars = s.toCharArray();
        int j=-1;   //初始化为-1只是一种实现方式
        next[0]=j;  //i指向后缀起始位置 j指向前缀起始位置

        for(int i=1;i<s.length();i++){
            //前后缀不同
            while(j>=0&&chars[j]!=chars[j+1]){
                j=next[j]; //向前回退
            }
            if(chars[i]==chars[j+1]) { //找到相同前缀
                j++;
            }
            next[i]=j;//将前缀长度赋值给next【i】
        }
        return true;
    }
    /**
     *
     * @param haystack 文本字符串
     * @param needle    目标字符串
     * @return -1 未找到匹配字符串
     */
    public static int getStrindex(String haystack,String needle){
       if(needle.length()==0) return 0;

        int []next=new int[needle.length()];
        getNext(next,needle);

        char[] s = haystack.toCharArray();
        char[] t = needle.toCharArray();

        int j=-1; //在找next中j为-1

        for(int i=0;i<haystack.length();i++){
            while(j>=0&&s[i]!=t[j+1]){//不匹配
                j=next[j];//寻找之前匹配的位置
            }

            if(s[i]==t[j+1]){//匹配则将j后移
                j++;
            }

            if(j==(t.length-1)){//s中出现了与t匹配的字符串
                return (i-t.length+1);//返回第一次出现的位置
            }
        }

        return -1;
    }

    /**左神版本 更好理解
     *
     */
    public static int getindex(String haysatck,String needle){
        if(haysatck==null || needle== null || haysatck.length() < needle.length() || needle.length()<1){
            return -1;//不存在
        }

        char[] h = haysatck.toCharArray();
        char[] n = needle.toCharArray();
        int [] next = getNext(needle);//O(m)

        int i=0,j=0;
        //O(n)
        while(i<h.length && j<n.length){
            if(h[i]==n[j]){//字符相同，移动到下一位
                i++;
                j++;
            }else if(next[j]==-1){//等于-1说明到了目标字符的第一位，无法跳了，需要移动i指针，比较新的位
                i++;
            }else{//既不相同，也不是第一位，此时就应该跳转到最长公共前后缀的位置(kmp算法的核心），减少不必要的移动比较
                j = next[j];
            }
        }
        //i，j是否越界
        return j== n.length ? i-j : -1;//越界相等，说明找到匹配字符串
    }

    private static int[] getNext(String needle) {
        if(needle.length()==1){
            return new int[]{-1};
        }

        char[] n = needle.toCharArray();
        int next [] = new int[needle.length()];
        next[0]=-1;
        next[1]=0;
        int cn = 0;//cn是当前字符要与先前字符进行比较的位置
        int i =2;//要比较的字符位置

        while(i<needle.length()){
            if(n[i-1]==n[cn]){
                //如果当前字符的前一个字符和要比较的字符一致，说明当前字符的相同前缀和长度的位置就是cn+1
                next[i] = cn+1;
                //比较完成需要移动字符比较指针i
                i++;
                //同时需要移动比较字符指针cn,这样才能保证下一次比较的是当前字符的后一位
                cn++;
            }else if(cn>0){//当前字符的前一个字符不与比较字符cn一致，则需要移动cn，直接利用next数组移动cn到其相同前缀位置
                cn = next[cn];
            }else{//等到cn无法再移动时，说明当前字符的前一个字符不存在相同公共前后缀，直接赋值为0
                next[i] = 0;
                i++;//移动i，比较下一位
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String string  = "abcabceabcef";
        String string1 = "abcef";
        System.out.println(getindex(string,string1));
    }
}
