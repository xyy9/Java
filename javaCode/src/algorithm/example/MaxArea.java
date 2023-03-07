package algorithm.example;

import java.util.Map;
import java.util.Scanner;

/* 盛最多水的容器
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
返回容器可以储存的最大水量。
说明：你不能倾斜容器。
链接：https://leetcode.cn/problems/container-with-most-water/
 */
public class MaxArea {

    /*
    n == height.length
    2 <= n <= 10^5
    0 <= height[i] <= 10^4

    示例1：
    输入：[1,8,6,2,5,4,8,3,7]
    输出：49
    示例2：
    输入：[1,1]
    输出：1
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String nStr  = scanner.nextLine();
            String[] inputStrs = nStr.substring(nStr.indexOf('[') + 1, nStr.indexOf(']')).split(",");
            int[] height = new int[inputStrs.length];
            for(int i = 0; i < height.length; i++) {
                height[i] = Integer.parseInt(inputStrs[i]);
            }
            MaxArea res = new MaxArea();
            System.out.println(res.maxArea(height));
        }
    }

    public int maxArea(int[] height) {
        // 前挡板
        int front = 0;
        // 后挡板
        int back = height.length - 1;
        // 最大值
        int max = (back - front) * Math.min(height[front], height[back]);
        while (front < back) {
            // 挡板矮的向中间进1
            if (height[front] < height[back]) {
                front = front + 1;
            }
            else {
                back = back - 1;
            }
            int area = (back - front) * Math.min(height[front], height[back]);
            max = Math.max(max, area);
        }
        return max;
    }
}
