package algorithm.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* 分发糖果
n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
你需要按照以下要求，给这些孩子分发糖果：
每个孩子至少分配到 1 个糖果。
相邻两个孩子评分更高的孩子会获得更多的糖果。
请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
链接：https://leetcode.cn/problems/candy
 */
public class Candy {

    /*
    n == ratings.length
    1 <= n <= 2 * 10^4
    0 <= ratings[i] <= 2 * 10^4

    示例1：
    输入：ratings = [1,0,2]
    输出：5
    示例2：
    输入：[1,2,2]
    输出：4
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String nStr  = scanner.nextLine();
            String[] inputStrs = nStr.substring(nStr.indexOf('[') + 1, nStr.indexOf(']')).split(",");
            int[] ratings = new int[inputStrs.length];
            for(int i = 0; i < ratings.length; i++) {
                ratings[i] = Integer.parseInt(inputStrs[i]);
            }
            Candy res = new Candy();
            res.candy(ratings);
            System.out.println(res.count);
        }
    }

    public int count = 0;

    public void candy(int[] ratings) {
        int[] nums = new int[ratings.length];
        if (nums.length == 0) {
            return;
        }
        // 初始化
        nums[0] = 1;
        // 正序
        for (int i = 1; i < nums.length; i++) {
            // 如果后一位比前一位分数高，则比前一位多1
            if (ratings[i] > ratings[i - 1]) {
                nums[i] = nums[i - 1] + 1;
            }
            // 否则为1
            else {
                nums[i] = 1;
            }
        }

        // 倒序
        for (int i = nums.length - 2; i >= 0; i--) {
            // 如果前一位比后一位分数高，且糖果少则多1
            if (ratings[i] > ratings[i + 1] && nums[i] <= nums[i + 1]) {
                nums[i] = nums[i + 1] + 1;
            }
        }

        // 结果
        count = Arrays.stream(nums).sum();
    }

}
