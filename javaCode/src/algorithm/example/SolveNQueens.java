package algorithm.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* n皇后问题(回溯法)
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n，返回所有不同的n皇后问题 的解决方案。
每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
链接：https://leetcode.cn/problems/n-queens
 */
public class SolveNQueens {

    /*
    1 <= n <= 9

    示例1：
    输入：n = 4
    输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    示例2：
    输入：n = 1
    输出：[["Q"]]
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String nStr  = scanner.nextLine();
            int n = Integer.parseInt(String.valueOf(nStr.charAt(4)));
            SolveNQueens res = new SolveNQueens();
            res.dfs(0, n);
            // 结果集为List<List<String>> res.result
            System.out.println(res.result);

        }
    }

    // 结果集
    public List<List<String>> result = new ArrayList<>();

    // 棋子摆放位置(index为row，value为col)
    private List<Integer> index_col = new ArrayList<>();
    // 左斜线
    private List<Integer> index_add = new ArrayList<>();
    // 右斜线
    private List<Integer> index_sub = new ArrayList<>();

    // 深度优先算法，搜索至第row+1行，一共n行
    public void dfs(int row, int n) {
        // 遍历结束，添加结果
        if (row == n) {
            result.add(resultToList(n));
            return;
        }
        // 在第row行放置棋子
        for (int col = 0; col < n; col++) {
            // 重复
            if (index_col.contains(col) || index_add.contains(row + col) || index_sub.contains(row - col)) {
                continue;
            }
            // 放置
            index_col.add(col);
            index_add.add(row + col);
            index_sub.add(row - col);
            // 遍历
            dfs(row + 1, n);
            // 回溯
            index_col.remove(row);
            index_add.remove(row);
            index_sub.remove(row);
        }
    }

    // 封装结果
    private List<String> resultToList(int n) {
        List<List<String>> rows = index_col.stream().map(p -> {
            List<String> inits = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (i == p) {
                    // 放置
                    inits.add("Q");
                } else {
                    inits.add(".");
                }
            }
            return inits;
        }).collect(Collectors.toList());
        return rows.stream().map(p -> String.join("", p)).collect(Collectors.toList());
    }

}
