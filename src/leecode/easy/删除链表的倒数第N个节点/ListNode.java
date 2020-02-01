package leecode.easy.删除链表的倒数第N个节点;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

/**
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
//        return removeNode(head,n)==n?head.next:head;
        // 定义哨兵结点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        // 定义快指针
        ListNode fast = dummyNode.next;
        ListNode slow = dummyNode.next;
        // 快指针先移动n 个位置 **
        for(int i =0;i<=n ;i++){
            fast = fast.next;
        }
        // 同时移动快、慢指针
        for(;fast !=null;){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }
//    public int removeNode(ListNode node,int n) {
//        if(node.next == null)  return 1;
//        int m = removeNode(node.next, n);
//        if(m == n)
//            if(m == 1) node.next = null;
//            else node.next = node.next.next;
//        return m+1;
//    }
}