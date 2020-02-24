/*
18.2.删除排序链表中重复的节点
P122
 */

public class DeleteDuplicatedNode
{
    public ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode nextNode = pHead.next;
        if (pHead.value == nextNode.value)
        {
            while (nextNode != null && pHead.value == nextNode.value)
            {
                // 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
                nextNode = nextNode.next;
            }
            return deleteDuplication(nextNode);  // 进入递归，重新赋值pHead，则前面相同的节点被舍弃
        }
        else
        {
            pHead.next = deleteDuplication(pHead.next);
            // 假设1->2->3，如果返回3，则pHead.next=3, pHead=2
            return pHead;
        }
    }
}
