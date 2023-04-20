package 迷宫;

/**
 * @author zhp
 * @date 2023-04-18 20:08
 * https://leetcode.cn/problems/flood-fill/
 */
public class 图像渲染_lc_733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        DFS(image,sr,sc,newColor,oldColor);
        return image;
    }

    public void DFS(int[][] image,int x,int y,int newColor,int oldColor){
        if(x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
            return;
        }
        if(image[x][y] != oldColor || image[x][y] == newColor){
            return;
        }
        image[x][y] = newColor;
        DFS(image, x - 1, y,newColor,oldColor);
        DFS(image, x + 1, y,newColor,oldColor);
        DFS(image, x, y - 1,newColor,oldColor);
        DFS(image, x, y + 1,newColor,oldColor);
    }
}
