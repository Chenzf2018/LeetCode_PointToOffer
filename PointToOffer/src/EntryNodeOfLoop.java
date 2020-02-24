/*
23.链表中环的入口
P139
 */

public class EntryNodeOfLoop
{
    public ListNode entryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode low = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) //这两个判断条件避免fast空指针异常(没有环)
        {
            low = low.next;
            fast = fast.next.next;
            if (low == fast)  // 两个指针相遇
            {
                low = pHead;
                while (low != fast)
                {
                    low = low.next;
                    fast = fast.next;
                }
                return low;
            }
        }
        return null;
    }
}
