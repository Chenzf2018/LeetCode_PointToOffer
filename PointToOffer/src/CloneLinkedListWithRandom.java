/*
35.复杂链表的复制
P187
 */

public class CloneLinkedListWithRandom
{
    public RandomListNode clone(RandomListNode pHead)
    {
        if (pHead == null)
            return null;

        // 遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面
        RandomListNode currentNode = pHead;
        while (currentNode != null)
        {
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            cloneNode.next = currentNode.next;
            currentNode.next = cloneNode;
            currentNode = cloneNode.next;
        }

        // 重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        currentNode = pHead;
        while (currentNode != null)
        {
            currentNode.next.random = currentNode.random == null ? null : currentNode.random.next;
            // 1->1'->2->2'：1.random=2, 1.random.next=2.next=2'
            currentNode = currentNode.next.next;
        }

        // 拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (currentNode != null)
        {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next == null ? null : cloneNode.next.next;
            currentNode = currentNode.next;
        }

        return pCloneHead;
    }
}
