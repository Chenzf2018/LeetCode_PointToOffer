/*
 * 59.1 滑动窗口的最大值
 * P288
 * 暴力解法
 */

import java.util.ArrayList;

public class MaxInWindows
{
    /**
     *
     * @param num 数组
     * @param size 窗口大小
     * @return 所有滑动窗口里数值的最大值
     */
    private static ArrayList<Integer> maxInWindows(int[] num, int size)
    {
        ArrayList<Integer> maximumInWindow = new ArrayList<>();
        if (num == null || size < 1 || size > num.length)
            return maximumInWindow;
        //preMaxIndex上一个滑动窗口最大值的下标
        int preMaxIndex = findIndexOfMax(num, 0, size - 1); // 第一个窗口中最大值的下标
        maximumInWindow.add(num[preMaxIndex]);  // 将第一个窗口中得到的最大值放入数组链表中
        // 从数组的index=1处开始在下一个窗口中找最大值
        for (int start = 1, end = size; start <= (num.length - size); start++, end++)
        {
            int newMaxIndex = maxNumIndexOfWindow(num, preMaxIndex, start, end);
            //maximumInWindow.add(newMaxIndex);
            maximumInWindow.add(num[newMaxIndex]);
            preMaxIndex = newMaxIndex;
        }
        return maximumInWindow;
    }

    /**
     *
     * @param num 数组
     * @param start 窗口开始位置
     * @param end 窗口结束位置
     * @return 返回数组[start,end]范围内的最大值的下标
     */
    private static int findIndexOfMax(int[] num, int start, int end)
    {
        int max = num[start];
        int maxIndex = start;
        for (int i = start; i <= end; i++)
        {
            if (num[i] >= max)
            {
                //max = num[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     *
     * @param num 数组
     * @param preMaxIndex 上一个滑动窗口最大值的下标
     * @param start 窗口起始处
     * @param end 窗口截止处
     * @return 新窗口中最大值的下标
     */
    private static int maxNumIndexOfWindow(int[] num, int preMaxIndex, int start, int end)
    {
        if (start <= preMaxIndex && preMaxIndex <= end)
        {
            // 如果preMaxIndex在新的滑动窗口[start,end]之间
            // 则判断上一个最大值与新增的一个值num[end]比较大小，返回index
            return (num[preMaxIndex] <= num[end]) ? end : preMaxIndex;
        }
        else
        {
            // 如果preMaxIndex不在新的滑动窗口[start,end]之间
            // 直接找新滑动窗口的最大值的下标
            return findIndexOfMax(num, start, end);
        }
    }

    public static void main(String[] args)
    {
        int[] num = {2, 3, 4, 2, 6, 2, 5, 1};
        int size = 3;
        System.out.println(maxInWindows(num, size));
    }
}
