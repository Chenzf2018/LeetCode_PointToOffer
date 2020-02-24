/*
22.链表中倒数第k个节点
P134
 */

public class FindKthFromTail
{
    public ListNode findKthFromTail(ListNode head, int k)
    {
        if (k <= 0 || head == null)
            return null;
        ListNode p1 = head;
        ListNode p2 = head;
        // p2向前移动k个节点
        for (int i = 0; i < k - 1; i++)
        {
            if (p2 == null)
                return null;  // 链表的节点总数少于K-1
            p2 = p2.next;
        }
        if (p2 == null)
            return null;  // 链表的节点总数少于K
        while (p2.next != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
