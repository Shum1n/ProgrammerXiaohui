package leecode.easy.合并两个有序链表;

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
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 看了昨天 leetCode 解答，使用哨兵结点比较容易.
        // 在循环中，已经改变了哨兵节点
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        for(;l1!=null && l2!=null;){
            if(l1.val<l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
//        if(l1.next == null){
//            prev.next = l2;
//        }
//        if(l2.next == null){
//            prev.next = l1;
//        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}