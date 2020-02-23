/**
 * 30.包含min函数的栈
 * P165
 */

import java.util.Stack;

public class StackWithMin
{
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void push(int node)
    {
        stack1.push(node);
        if (stack2.empty())
            stack2.push(node);
        else if (node < stack2.peek())
            stack2.push(node);
    }

    void pop()
    {
        if (stack1.peek().equals(stack2.peek()))  // peek()返回值不删除
            stack2.pop();
        stack1.pop();
    }

    int top(){return stack1.peek();}

    int min(){return stack2.peek();}
}
