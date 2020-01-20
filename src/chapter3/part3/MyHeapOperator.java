package chapter3.part3;

import java.util.Arrays;

public class MyHeapOperator {

    public static void main(String[] args) {
        int[] array = {1,3,2,6,5,7,8,9,10,0};
        // 0 1 2 6 3 7 8 9 10 5
        upAdjust(array);

        array = new int[]{7,1,3,10,5,2,8,9,6};
        // 为什么 > = 0
        for (int i = (array.length-2)/2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        // [1, 5, 2, 6, 7, 3, 8, 9, 10]
        // [1, 5, 2, 6, 7, 3, 8, 9, 10]
        System.out.println(Arrays.toString(array));
    }

    /**
     * 这只是其中一个节点的下沉
     * @param arr
     * @param pIndex
     * @param length
     */
    private static void downAdjust(int[] arr,int pIndex,int length){
        /**
         * 1. 找到最后一个非叶子节点，与左右节点中较小的一个》下沉
         */
        // 父节点的值
        int pVal = arr[pIndex];
        // 左子节点下标
        int childIndex = pIndex * 2 + 1;
        for(;childIndex<arr.length && pVal>arr[childIndex];){
            // 找出左右子节点中较小的。
            // 假设都在左右子树
            // 父节点大于其中最小的一个，下沉。交换位置
            childIndex = arr[childIndex] < arr[childIndex+1] ? childIndex : ++childIndex;
            // 单向赋值
            arr[pIndex] = arr[childIndex];
            pIndex = childIndex;
            childIndex = pIndex * 2 + 1;
        }
        arr[pIndex] = pVal;
    }


    /**
     * 上浮调整
     * @param arr
     */
    private static void upAdjust(int[] arr){
        /**
         * 1。插入到最后一个位置 V
         * 2。与父节点比较，比父节点小，上浮，交换位置
         * 3。重重2
         */
        // 1。最后插入的位置
        // 假设这是左孩子
        int child = arr.length-1;
        int childVal = arr[child];
        // 父节点的位置
        int p = (child - 1) / 2;

        // 循环中，父节点改变位置，每次与父节点比较。
        // 参考书中单向赋值。
        for(;child>0 && childVal< arr[p];){
            arr[child] = arr[p] ;
            // 移动到父节点位置
            child = p;
            // 继续向上找父节点
            p = (child - 1) / 2;
        }
        arr[child] = childVal;

        for (int i : arr) {
            System.out.print(i);
        }


    }
}
