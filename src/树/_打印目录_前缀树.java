package 树;

import java.util.TreeMap;

/**
 * @author zhp
 * @date 2022-07-05 20:49
 *
 * 给你一个字符串类型的数组arr，譬如：
 * String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
 * 你把这些路径中蕴含的目录结构给画出来，子目录直接列在父目录下面，并比父目录
 * 向右进两格，就像这样:
 * a
 *   b
 *     c
 *   d
 *     e
 * b
 *   cst
 * d
 * 同一级的需要按字母顺序排列，不能乱。
 */
public class _打印目录_前缀树 {

    public static class Node{//目录节点
        public String name;//当前目录名，多保存一份
        TreeMap<String,Node>nextMap;//下属目录的连接，通过有序表可以实现输出时的顺序输出

        public Node(String name) {
            this.name = name;
            this.nextMap = new TreeMap<>();
        }


    }

    //按题意输出目录结构
    public static void print(String []folderPaths){
        if(folderPaths==null || folderPaths.length<1){
            return ;
        }

        Node head = generateFolderTree(folderPaths);//得到目录树的根节点

        printProcess(head,0);
    }

    public static Node generateFolderTree(String[] folderPaths){
        //生成目录树根节点节点
        Node head = new Node("");
        for(String foldPath : folderPaths){
            String[] paths = foldPath.split("\\\\");//该正则表达式表示'\'
            Node  cur = head;
            for(int i=0;i<paths.length;i++){
                if(!cur.nextMap.containsKey(paths[i])){//查看当前目录是否已经存在不存在的话需要加入
                    cur.nextMap.put(paths[i],new Node(paths[i]));
                }
                cur = cur.nextMap.get(paths[i]);//移动当前node指针，指向下一层目录
            }
        }
        return head;
    }

    //dfs输出所有目录
    public static void printProcess(Node head,int level){//根据当前目录所在的层次，按规定输出
       if(level!=0){
           System.out.println(get2nSpace(level)+head.name);
       }
       //dfs
       for(Node next : head.nextMap.values()){
           printProcess(next,level+1);
       }
    }

    //根据所在的层输出2*level的空格
    public static String get2nSpace(int level) {
        String res = "";
        for(int i=1;i<level ;i++){
            res+=" ";
        }
        return res;
    }

    public static void main(String[] args) {
        String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
        print(arr);
    }


}
