package 树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhp
 * @date 2022-07-25 12:29
 * https://leetcode.cn/problems/implement-trie-prefix-tree/solution/
 */
public class 前缀树实现_lc_208 {
    final int anInt =1;
    int da;
    static void aVoid(){
        System.out.println("a");
    }
    public static void main(String[] args) {
        前缀树实现_lc_208  x = new 前缀树实现_lc_208();
        x.aVoid();
        System.out.println(x.da);
    }
    /**
     * 基于数组的字典树实现
     */
//    class Trie {
//        Trie[] children;
//        boolean isEnd;
//        public Trie() {
//            children = new Trie[26];
//            isEnd = false;
//        }
//
//        public void insert(String word) {
//            Trie node = this;
//            for(int i=0;i<word.length();i++){
//                char ch = word.charAt(i);
//                int index = ch-'a';
//                if(node.children[index]==null){
//                    node.children[index] = new Trie();
//                }
//                node = node.children[index];
//            }
//            node.isEnd = true;
//        }
//
//        public boolean search(String word) {
//            Trie node = searchPrefix(word);
//            return node!=null &&node.isEnd;
//        }
//
//        public Trie searchPrefix(String prefix){
//            Trie node = this;
//            for(int i=0;i<prefix.length();i++){
//                int index = prefix.charAt(i)-'a';
//                if(node.children[index]==null){
//                    return null;
//                }
//                node = node.children[index];
//            }
//            return node;
//        }
//
//        public boolean startsWith(String prefix) {
//            return searchPrefix(prefix)!=null;
//        }
//    }

    /**
     * 基于哈希表的前缀树实现
     * 【前缀树】
     *
     * 虽然数组化哈希很常用，但如果题目给出的字符串是Unicode形式，那还是要实现成基于哈希表的前缀树。
     *
     * 【解法一：基于哈希表的前缀树】
     *
     * 本题考查前缀树的实现。在了解前缀树的结构和作用后，其实现是简单而直接的。
     * 前缀树问题一般都是在同一棵树上求解。树的根为非前缀，前缀从根的儿子开始。
     * 树结点类通常写作Trie。
     * 每一个结点的儿子结点数量，与「构成前缀的基本元素的种类数」相关。本题的插入和搜索方法也是一般前缀树的基本方法。
     *
     * insert：从root开始按输入的word的字符依次向下延伸，若无代表次字符的结点，创建之。
     * search：向下寻找word，若找到，则末尾字符处判断是否为单词。因此，Trie类还需维护一个isEnd属性，用以标记到该节点为止的前缀是否为单词。
     * startsWith：向下寻找prefix。某一字符找不到则立即返回false，否则返回true。
     * 其中，2，3动作类似，可以另外给出一个searchPrefix方法，寻找输入的字符串是否在前缀树上，
     * 找到则返回最后一个结点，否则返回null。于是2和3就可以通过调用searchPrefix，以一条返回语句完成方法。
     *
     * 时间复杂度：初始化为O(1)，每次操作为O(N)，N为插入或查找的字符串的长度。
     * 空间复杂度：O(N)，N表示Trie结点数量。N基本上等于所有插入字符的长度之和。
     * （说基本上是因为如果插入单词的有部分前缀相同，那么结点数量要减去这些前缀的长度）。
     */
    class Trie {
        Map<Character,Trie>children;
        boolean isEnd;
        public Trie() {
            children = new HashMap<>();
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for(char ch : word.toCharArray()){
                if(node.children.get(ch)==null){
                    node.children.put(ch,new Trie());
                }
                node = node.children.get(ch);
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie trie = searchPreix(word);
            return trie!=null&&trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPreix(prefix)!=null;
        }

        public Trie searchPreix(String prefix){
            Trie node = this;
            for(char ch : prefix.toCharArray()){
                if(node.children.get(ch)==null){
                    return null;
                }
                node = node.children.get(ch);
            }
            return node;
        }
    }
}


