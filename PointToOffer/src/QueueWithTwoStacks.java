/**
 * 9.用两个栈实现队列
 * P68
 */

import java.util.Stack;

public class QueueWithTwoStacks
{
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    //private static void push(int node)
    private void appendTail(int node)
    {
        stack1.push(node);
    }

    private int deleteHead()
    {
        if (stack2.empty())
        {
            while (! stack1.empty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
