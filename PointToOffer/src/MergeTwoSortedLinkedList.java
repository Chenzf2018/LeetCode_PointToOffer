/*
25.合并两个排序的链表
P145
 */

public class MergeTwoSortedLinkedList
{
    public ListNode merge(ListNode list1, ListNode list2)
    {
        //新建一个头节点，用来存合并的链表。
        ListNode head = new ListNode(0);
        ListNode root = head;
        while (list1 != null && list2 != null)
        {
            if (list1.value < list2.value)
            {
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            }
            else
            {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }
        }
        while (list1 != null)
        {
            head.next = list1;
            head = head.next;
            list1 = list1.next;
        }
        while (list2 != null)
        {
            head.next = list2;
            head = head.next;
            list2 = list2.next;
        }
        return root.next;
    }
}
