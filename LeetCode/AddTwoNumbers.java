package LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;  // 初始化为null
 *     ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers
{
    public ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2)
    {
        ListNode dummyHead = new ListNode(0);  // 定义一个哑结点
        ListNode p = listNode1, q = listNode2, curr = dummyHead;
        int carry = 0;  // 进位

        while (p != null || q != null)
        {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }

        // 防止求和最后出现进位 99 + 1 -> 001
        if (carry > 0)
            curr.next = new ListNode(carry);

        return dummyHead.next;
    }
}


