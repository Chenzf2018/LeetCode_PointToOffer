/*
6.从尾到头打印链表
P58
 */

import java.util.Stack;
import java.util.ArrayList;

public class PrintListFromTailToHead
{
    /**
     *
     * @param listNode 链表结点，定义见ListNode.java
     * @return 返回一个逆序的链表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode)
    {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null)
        {
            stack.push(listNode.value);
            listNode = listNode.next;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (! stack.empty())
            arrayList.add(stack.pop());
        return arrayList;
    }
}
