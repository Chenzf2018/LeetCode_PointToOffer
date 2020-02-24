/*
24.反转链表
P142
 */

public class ReverseList
{
    public ListNode reverseList(ListNode head)
    {
        if (head == null)
            return null;
        ListNode pre = null, next = null;
        //当前节点是head，pre为当前节点的前一节点，next为当前节点的下一节点
        //需要pre和next的目的是让当前节点从pre->head->next1->next2变成pre<-head next1->next2
        //即pre让节点可以反转所指方向，但反转之后如果不用next节点保存next1节点的话，此单链表就此断开了
        while (head != null)
        {
            //先用next保存head的下一个节点的信息，保证单链表不会因为失去head节点的原next节点而就此断裂
            next = head.next;
            //保存完head.next，让head从指向next变成指向pre，实现指针翻转
            head.next = pre;
            //让pre，head，next依次向后移动一个节点，继续下一次的指针反转
            pre = head;
            head = next;
        }
        //head为null的时候，pre(head前一个节点)就为最后一个节点了，此时链表已经反转完毕，pre就是反转后链表的第一个节点
        return pre;
    }
}
