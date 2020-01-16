package chapter3.part1;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * https://www.cnblogs.com/wylwyl/p/10477528.html
 */
public class TreeNode {

    String val;
    Integer data;
    TreeNode left;
    TreeNode right;

    TreeNode(String x) {
        val = x;
    }

    TreeNode(Integer x) {
        data = x;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return val;
    }
}

/**
 * 前序遍历：FBADCEGIH
 * 中序遍历：ABCDEFGHI
 * 后序遍历：ACEDBHIGF
 */
class Order {

    private static final String[] INPUTLIST = {"3", "2", "9", null, null, "10", null, null, "8", null, "4"};


    /**
     * 构建二叉树
     *
     * @param linkedList
     * @return
     */
    private static TreeNode createBinaryTree(LinkedList<String> linkedList) {
        /**
         * 前序递归
         * 获取元素只要不为空，就一直往左边放。
         * 为空就往右边放。
         * 递归退出的条件是
         */
        /**
         * removeFirst不能被用来连续取空一个链表，而pollFirst可以.
         * 如果改成removeFirst就会弹异常造成多余的麻烦处理。\
         * https://zhidao.baidu.com/question/1542344265065197347.html
         */
//        if(linkedList == null || linkedList.isEmpty()){
//            return null;
//        }
        return Optional.ofNullable(linkedList.pollFirst()).map(u -> {
            TreeNode treeNode = new TreeNode(u);
            treeNode.left = createBinaryTree(linkedList);
            treeNode.right = createBinaryTree(linkedList);
            return treeNode;
        }).orElseGet(() -> null);

        // 非递归，以栈的方式构建
        // 栈  <==> 递归
        // 遇到null 值，出栈


    }

    /**
     * 通过栈，非递归的方式构建二叉树
     *
     * @param linkedList
     * @return
     */
    private TreeNode createBinaryTreeWithStack(LinkedList<String> linkedList) {
        Stack<TreeNode> stack = new Stack<>();
        for (; linkedList != null && !linkedList.isEmpty() || !stack.isEmpty(); ) {
            // 取出头元素,是否左右节点都绑定了
            if (!stack.isEmpty()) {
                // 如果右节点已绑定，则出栈
                TreeNode pop = stack.pop();
                if (pop.right != null) {
                    // 取出最后一个元素。
                    if (stack.isEmpty()) {
                        return pop;
                    }
                    continue;
                }
                stack.push(pop);
            }
            // 并没有与之前节点关联
            String first = linkedList.pollFirst();
            // 空就空
            TreeNode treeNode = new TreeNode(first);
            // 空栈
            if (stack.isEmpty()) {
                stack.push(treeNode);
                continue;
            }
            // 取出上一个，不移除
            TreeNode pop = stack.peek();
            if (pop != null) {
                if (pop.left == null) {
                    pop.left = treeNode;
                } else {
                    pop.right = treeNode;
                }
            }

            // 按原来的顺序放回到栈中。
            if (first != null) {
                stack.push(treeNode);
            }
        }

        return null;
    }

    public static void main2(String[] args) {
        // 3291084
        TreeNode treeNode = createBinaryTree(new LinkedList<String>(Arrays.asList(INPUTLIST)));
        preOrder(treeNode);
    }

    // 定义节点
    public static void main(String[] args) {
        TreeNode f = new TreeNode("F");
        TreeNode b = new TreeNode("B");
        TreeNode g = new TreeNode("G");
        f.setLeft(b);
        f.setRight(g);
        TreeNode a = new TreeNode("A");
        TreeNode d = new TreeNode("D");
        b.setLeft(a);
        b.setRight(d);
        TreeNode c = new TreeNode("C");
        TreeNode e = new TreeNode("E");
        d.setLeft(c);
        d.setRight(e);
        TreeNode i = new TreeNode("I");
        g.setRight(i);
        TreeNode h = new TreeNode("H");
        i.setLeft(h);

        // 前序遍历 根左右：根节点排最先，然后同级先左后右
        // FBADCEGIH
        System.out.println("前序遍历：");
        preOrder(f);
        System.out.println();
        preOrderWithStack(f);
        System.out.println();
        System.out.println("中序遍历：");
        // 中序遍历 左根右
        inOrder(f);
        System.out.println();
        inorderWithStack(f);
        postOrderNo(f);

        System.out.println("后序遍历");
//        // 后序
        postOrder(f);
        postOrderNo(f);
        postOrderWithStack(f);
    }

    /**
     * 递归前序遍历
     *
     * @param treeNode
     */
    private static void preOrder(TreeNode treeNode) {
        System.out.print(treeNode.val);
        Optional.ofNullable(treeNode.left).ifPresent(Order::preOrder);
        Optional.ofNullable(treeNode.right).ifPresent(Order::preOrder);
    }

    /**
     * 非递归前序遍历
     *
     * @param treeNode
     */
    private static void preOrderWithStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        for (; !stack.isEmpty(); ) {
            TreeNode current = stack.pop();
            System.out.print(current.val);
            Optional.ofNullable(current.getRight()).ifPresent(stack::push);
            Optional.ofNullable(current.getLeft()).ifPresent(stack::push);
        }
    }

    /**
     * 中序遍历 左根右
     *
     * @param treeNode
     */
    private static void inOrder(TreeNode treeNode) {
        Optional.ofNullable(treeNode.getLeft()).ifPresent(Order::inOrder);
        System.out.print(treeNode.val);
        Optional.ofNullable(treeNode.getRight()).ifPresent(Order::inOrder);
    }

    /**
     * 非递归 中序遍历
     *
     * @param treeNode
     */
    private static void inorderWithStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // 有叶子节点入栈
        for (; treeNode != null; ) {
            // 重构代码。只把根节点入栈
            // 如有叶子节点，就入栈
            if (treeNode.left != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                System.out.print(treeNode.val);
                // 出栈根节点
                // 处理根节点已出栈，此时栈中无元素
                if (!stack.isEmpty()) {
                    treeNode = stack.pop();
                    System.out.print(treeNode.val);
                }
                if (treeNode.right != null) {
                    treeNode = treeNode.right;
                } else {
                    treeNode = null;
                }
            }
        }
    }

    // 非递归算法

    /**
     * 用栈来保存先前走过的路径，以便可以在访问完子树后,可以利用栈中的信息,回退到当前节点的双亲节点,进行下一步操作。
     */
    private static void inOrderNo(TreeNode treeNode) {
        System.out.println();
        Stack<TreeNode> stack = new Stack<>();
        for (; !stack.isEmpty() || treeNode != null; ) {
            if (treeNode != null) {
                // 还有左右结点的时候：存
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                // left 为null，取出父节点。
                // 再判断right 是否null
                // 左右结点遍历完之后：取
                treeNode = stack.pop();
                System.out.print(treeNode.val);
                // 出栈的元素指向右节点
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param treeNode
     */
    private static void postOrder(TreeNode treeNode) {
        Optional.ofNullable(treeNode.getLeft()).ifPresent(Order::postOrder);
        Optional.ofNullable(treeNode.getRight()).ifPresent(Order::postOrder);
        System.out.print(treeNode.val);
    }

    private static void postOrderWithStack(TreeNode treeNode) {
        System.out.println();
        Stack<TreeNode> stack = new Stack<>();
        // 用来保存已经走到右节点的，父节点
        Set<TreeNode> right = new HashSet<>();
        // 已经走过左节点
        Set<TreeNode> left = new HashSet<>();

        // 结束循环的条件
        for (; !stack.isEmpty() ; ) {
            // 左节点
            // 从栈中出来的数据不走左节点
            if (treeNode.left != null && !left.contains(treeNode)) {
                stack.push(treeNode);
                left.add(treeNode);
                treeNode = treeNode.left;
            } else {
                // 走过父节点
                if (treeNode.right != null) {
                    if (right.contains(treeNode)) {
                        // 输出，不放回栈中
                        System.out.print(treeNode);
                        // 避免 EmptyStackException
                        if(!stack.isEmpty()){
                            treeNode = stack.pop();
                        }
                        continue;
                    }
                    // 右节点不为空放回去
                    stack.push(treeNode);
                    // 标记已走到右树
                    right.add(treeNode);
                    treeNode = treeNode.right;
                } else {
                    // 没有子点了，输出
                    System.out.print(treeNode.val);
                    // 出栈，父节点
                    treeNode = stack.pop();
                }
            }

        }
    }

    /**
     * 非递归后序遍历
     *
     * @param treeNode
     */
    private static void postOrderNo(TreeNode treeNode) {
        System.out.println();
        Stack<TreeNode> stack = new Stack<>();
        // 标记最近出栈的节点，用于判断是否是p节点的右孩子，如果是的话，就可以访问p节点
        TreeNode pre = treeNode;

        for (; treeNode != null || !stack.isEmpty(); ) {
            // 有左节点的，存
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                // 根节点处理。
                // 很明显：右节点为空时，输出根节点。
                // 如果右子树已经遍历过，输出根节点。
                if (treeNode.right == null || treeNode.right == pre) {
                    System.out.print(treeNode.val);
                    pre = treeNode;
                    treeNode = null;
                } else {
                    stack.push(treeNode);
                    treeNode = treeNode.right;
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
            }

        }
    }


}
