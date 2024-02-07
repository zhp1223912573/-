package 树;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhp
 * @date 2021-11-29 11:16
 * 二叉搜索树中的众数（可能不止一个）
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 */
public class 二叉搜索树的众数_lc_501 {

    public int[] findMode(TreeNode root) {
        int[] a = {1};
        return a;
    }

    /**
     * 忽视搜索二叉树的性质 将其看作一颗普通的树
     * 直接使用任意的遍历方式遍历整棵树 利用map记录数值及其对应的频率
     * 最后进行转换 排序即可
     */
//    public int[] findMode(TreeNode root) {
//        Map<Integer, Integer> map = new HashMap<>();
//        List<Integer> list = new ArrayList<>();
//        if (root == null) return list.stream().mapToInt(Integer::intValue).toArray();
//        // 获得频率 Map
//        searchBST(root, map);
//        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream()
//                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
//                .collect(Collectors.toList());
//        list.add(mapList.get(0).getKey());
//        // 把频率最高的加入 list
//        for (int i = 1; i < mapList.size(); i++) {
//            if (mapList.get(i).getValue() == mapList.get(i - 1).getValue()) {
//                list.add(mapList.get(i).getKey());
//            } else {
//                break;
//            }
//        }
//        return list.stream().mapToInt(Integer::intValue).toArray();
//    }
//
//    void searchBST(TreeNode curr, Map<Integer, Integer> map) {
//        if (curr == null) return;
//        map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
//        searchBST(curr.left, map);
//        searchBST(curr.right, map);
//    }

    /**
     * 利用搜索二叉树的性质
     * 中序遍历记录数字出现的次数count
     * 统计次数出现最多的值记为maxcount
     * 再次遍历得到所有出现次数为maxcount的数字的集合
     * 该方法需要两次遍历
     */

    /**
     * 利用二叉树的性质
     * 中序遍历记录数字出现的频率count
     * 对某个出现次数count等于maxcount则使用集合记录目前的数字
     * 如果count大于maxcount则清楚集合记录下来的数字 因为出现了最大值 前面的所有数字作废
     * 该方法只需要一次遍历
     */
    List<Integer> list = new ArrayList<Integer>();
    int count = 0;
    int maxcount = 0;
    int pre = -1;

    public void fin(TreeNode root) {
        if (root == null) return;

        if (root.left != null) fin(root.left);

        if (pre == -1) {
            count = 1;
        } else if (pre == root.val) {
            count++;
        } else if (pre != root.val) {
            count = 1;
        }

        if (count == maxcount) {
            list.add(root.val);
        }

        //出现最新的众数 清空先前的众数
        if (count > maxcount) {
            maxcount = count;
            list.clear();
            list.add(root.val);
        }

        pre = root.val;

        if (root.right != null) fin(root.right);

    }


}

