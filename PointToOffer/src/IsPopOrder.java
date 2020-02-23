/**
 * 31.栈的压入、弹出序列
 * P168
 */

import java.util.Stack;

public class IsPopOrder
{
    public static boolean isPopOrder(int[] pushA, int[] popA)
    {
        if (pushA == null || popA == null || pushA.length == 0 || popA.length == 0 || pushA.length != popA.length)
            return false;
        Stack<Integer> stack = new Stack<>();  // 辅助栈
        // 模拟pushA入栈--进入栈s，popA出栈--弹出s栈顶；若popA合法，则s最后一定是空的
        for (int i = 0, popIndex = 0; i < pushA.length; i++)
        {
            stack.push(pushA[i]);  // 将压入栈中元素压入辅助栈
            while (! stack.empty() && stack.peek() == popA[popIndex])
            {
                // 弹出一个,继续比较下一个是否还可以弹出；辅助栈栈顶和popA相同则出栈，且popIndex++
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args)
    {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {4, 3, 5, 1, 2};
        System.out.println(isPopOrder(push,pop1));
        System.out.println(isPopOrder(push,pop2));
    }
}
