package 字符串;

/**
 * @author zhp
 * @date 2022-10-20 20:53
 * https://leetcode.cn/problems/one-away-lcci/solution/
 */
public class ms01_05_一次编辑 {
    /**
     * 总结三种操作插入、删除、替换的特点。
     * 两个字符串，只要他能通过上述操作实现两者一致，那么一定有某个字符串需要执上述操作。
     * 如果是插入，如下，字符串1的前半部分一定和被插入字符串2的前半部一样，并且后半部也一样。
     *      abcdef
     *      abdef
     * 如果是删除，如下，字符串1的前半部分和被删除字符串2的前半部分一样，并且后半部分也一样
     *      abdef
     *      abcdef
     * 如果是替换，如下，字符串1的前半部分和被替换字符串2的前半部分一样，并且后半部分也一样
     *      abcdef
     *      abhdef
     *
     * 插入和删除在题解中含义是一样的，所有我们只需要选取较长字符串，判断较短字符串需要删除的位置前后是否一致
     * 替换则要求字符串前后一致。
     *
     * 自己敲的太繁琐了，借鉴一波三叶姐
     *
     */
    public boolean oneEditAway(String first, String second) {
        int m = first.length();
        int n = second.length();
        if(Math.abs(m-n)>1) return false;
        //限定first字符串比second短
        if(m>n) return oneEditAway(second,first);
        //cnt统计不同的字符出现次数，如果超过一次，那么该字符串一定无法修改成功
        int i =0,j=0,cnt=0;
        while(i<m&&j<n&&cnt<=1){
            if(first.charAt(i)==second.charAt(j)){
                i++;
                j++;
            }else{
                if(m==n){
                    i++;j++;cnt++;
                }else{
                    j++;
                    cnt++;
                }
            }
        }
        return cnt<=1;
    }
}
