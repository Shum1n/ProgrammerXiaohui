package leecode.easy.反转链表;

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
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode node = reverseList(node1);
        for(;node!=null;){
            System.out.print(node.val);
            node = node.next;
        }

    }
    /**
     * 这是一个单链表，只要把原来
     * @param head
     * @return
     */
    private static ListNode reverseList(ListNode head) {
//        if(head ==null){
//            return null;
//        }
//        Stack<ListNode> stack = new Stack<>();
//        ListNode node = head;
//        // 怎么使用哨兵简化操作？
//        // 使用迭代的方式
//        for(;node.next !=null;){
//            stack.push(node);
//            node = node.next;
//        }
//        ListNode res = node;
//        // 此时 node.next ==null
//        for(;!stack.isEmpty();){
//            // 从栈中取出结点
//            ListNode pop = stack.pop();
//            node.next = pop;
//            node = pop;
//            // 最后一个元素指向 null
//            if(stack.isEmpty()){
//                node.next = null;
//            }
//        }
//        return res;

//        ListNode prev = null; //前指针节点
//        ListNode curr = head; //当前指针节点
        //每次循环，都将当前节点指向它前面的节点，然后当前节点和前节点后移
//        while (curr != null) {
//            ListNode nextTemp = curr.next; //临时节点，暂存当前节点的下一节点，用于后移
//            curr.next = prev; //将当前节点指向它前面的节点
//            prev = curr; //前指针后移
//            curr = nextTemp; //当前指针后移
//        }
////        for(ListNode nextTemp
////            ;curr != null;
////            nextTemp = curr.next,
////            curr.next = prev, //将当前节点指向它前面的节点
////            prev = curr, //前指针后移
////            curr = nextTemp //当前指针后移
////        ){ }
//        return prev;
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}