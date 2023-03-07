package algorithm.example;

import java.util.*;

/* 电话号码的字母组合
给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 */
public class LetterCombinations {

    /*
    0 <= digits.length <= 4
    digits[i] 是范围 ['2', '9'] 的一个数字。

    示例1：
    输入：digits = "23"
    输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
    示例2：
    输入：digits = ""
    输出：[]
    示例3：
    输入：digits = "2"
    输出：["a","b","c"]
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String nStr  = scanner.nextLine();
            String inputStrs = nStr.substring(nStr.indexOf('"') + 1, nStr.lastIndexOf('"'));
            // 深度优先法
            LetterCombinations dfsRes = new LetterCombinations();
            dfsRes.dfs(inputStrs, "");
            // 结果集为List<String> result
            System.out.println("深度优先法:" + dfsRes.result);

            // 广度优先法
            LetterCombinations bfsRes = new LetterCombinations();
            Queue<String> bfsQueue = new LinkedList<String>();
            for (int index = 0; index < inputStrs.length(); index++) {
                // 遍历
                bfsRes.bfs(bfsQueue, inputStrs.charAt(index));
            }
            bfsRes.result.addAll(bfsQueue);
            System.out.println("广度优先法:" + bfsRes.result);
        }
    }

    // 结果集
    public List<String> result = new ArrayList<>();

    // 9键键盘字母对应
    private Map<Character, String[]> phoneMap = new HashMap<Character, String[]>() {
        {
            put('0', new String[] {});
            put('1', new String[] {});
            put('2', new String[] { "a", "b", "c" });
            put('3', new String[] { "d", "e", "f" });
            put('4', new String[] { "g", "h", "i" });
            put('5', new String[] { "j", "k", "l" });
            put('6', new String[] { "m", "n", "o" });
            put('7', new String[] { "p", "q", "r", "s" });
            put('8', new String[] { "t", "u", "v" });
            put('9', new String[] { "w", "x", "y", "z" });
        }
    };

    // 深度优先算法，inputStrs为等待遍历的集合，output为遍历后的集合
    public void dfs(String inputStrs, String output) {
        // 遍历结束，添加结果
        if (inputStrs.length() == 0) {
            if (output.length() != 0) {
                result.add(output);
            }
            return;
        }
        // 待遍历数字
        char num = inputStrs.charAt(0);
        String[] phoneStrs = phoneMap.getOrDefault(num, new String[] {});
        for (int index = 0; index < phoneStrs.length; index++) {
            // 遍历
            dfs(inputStrs.substring(1), output.concat(phoneStrs[index]));
        }
    }

    // 广度优先算法
    public void bfs(Queue<String> queue, char num) {
        // 待遍历数字
        String[] phoneStrs = phoneMap.getOrDefault(num, new String[] {});
        // 如果队列中没有值(第一层)
        if (queue.size() == 0) {
            // 添加第一层
            for (int index = 0; index < phoneStrs.length; index++) {
                queue.add(phoneStrs[index]);
            }
        }
        // 遍历
        else {
            int queueLength = queue.size();
            for (int i = 0; i < queueLength; i++) {
                String tmp = queue.poll();
                for (int index = 0; index < phoneStrs.length; index++) {
                    queue.add(tmp.concat(phoneStrs[index]));
                }
            }
        }
    }

}
