/*
52.两个链表的第一个公共节点
P253
 */

public class FindFirstCommonNode
{
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2)
    {
        if (pHead1 == null || pHead2 == null)
            return null;

        ListNode currentNode1 = pHead1;
        ListNode currentNode2 = pHead2;
        int length1 = getLength(currentNode1);
        int lenght2 = getLength(currentNode2);

        if (length1 > lenght2)
        {
            int step = length1 - lenght2;
            // 先遍历链表1，遍历的长度就是两链表的长度差
            while (step > 0)
            {
                currentNode1 = currentNode1.next;
                step--;
            }
        }
        else if (lenght2 > length1)
        {
            int step = lenght2 - length1;
            while (step > 0)
            {
                currentNode2 = currentNode2.next;
                step--;
            }
        }

        //开始齐头并进，直到找到第一个公共结点
        while (currentNode1 != currentNode2)
        {
            currentNode1 = currentNode1.next;
            currentNode2 = currentNode2.next;
        }

        return currentNode1;
    }

    private int getLength(ListNode pHead)
    {
        int length = 0;
        ListNode currentNode = pHead;
        while (currentNode != null)
        {
            currentNode = currentNode.next;
            length++;
        }
        return length;
    }
}
