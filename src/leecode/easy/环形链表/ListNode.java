package leecode.easy.环形链表;

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
class Solution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        node2.next = node1;
        System.out.println(hasCycle(node1));
    }
    // 什么时候循环结结束
    private static boolean hasCycle(ListNode head) {
        // 使用两个指针，一个一次走两步。一个一次走一步。
        // 会重合
        ListNode one = head;
        ListNode two = head;
        // 如果有环一定会相遇，如果没环就会停下来
        for(;one.next!=null && two.next.next !=null;){
            // 一定有环？
            two = two.next.next;
            one = one.next;
            if(two == one){
                return true;
            }
        }
        return false;
       // 另外一种解法
//        while(head != null){
//            if(head == head.next){
//                return true;
//            }
//            if(head.next != null){
//                head.next = head.next.next;
//            }
//            head = head.next;
//        }
//        return false;
    }


}