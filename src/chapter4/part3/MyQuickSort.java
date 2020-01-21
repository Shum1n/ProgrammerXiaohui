package chapter4.part3;

import java.util.Arrays;

public class MyQuickSort {

    public static void main(String[] args) {
        int[] arr = {3,1,2,4,5,6};
        arr = new int[]{4,7,6,5,3,2,8,1};
        arr = new int[]{1,2,4,3};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
    }

    private static void quickSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }
        System.out.println(Arrays.toString(arr));
        int pivot = partition(arr, begin, end);
        quickSort(arr,begin,pivot-1);
        quickSort(arr,pivot+1,end);

    }


    /**
     * 分治双边算法
     * @param arr
     * @param begin
     * @param end
     * @return 返回重合的位置
     * https://www.bilibili.com/video/av78089094/?spm_id_from=333.788.videocard.6
     */
    private static int partition(int[] arr,int begin,int end){
        int pivot = arr[begin];
        int left = begin;
        int right = end;
        for(;left<right;){
            for(;arr[right]>=pivot && right>0 ;){
                right--;
            }
            arr[left] = arr[right];
            // 左指针：小于等于基数 left++
            // left<right 重合
            for(;arr[left]<=pivot && left<arr.length -1 && left<right;){
                left++;
            }

            arr[right] = arr[left];
        }
        // 交换基准元素
        arr[left] = pivot;

        return left;
    }

    /**
     * 按照从小到大排序
     * 1。左右指针
     *   右指针左移：比基数小
     *   左指针右移：比基数大
     * 2。重合时，结束本轮循环。重合点与基数交换进入下一次循环
     *   重合点判定？
     *
     *  分治法-递归好实现？
     * @param arr
     */
    private static void quickSort (int[] arr){
        // 第一个元素为基准元素
        int pivot = arr[0];
        // 什么时候退出：没有位置交换的时候
        int left = 0;
        int right = arr.length - 1;

        for(;right>=left;){
            // 双边循环法。
            // 右指针：大于等于基数 right-- [1~len-1]
            for(;arr[right]>=pivot && right>0 ;){

                right--;
            }
            // 左指针：小于等于基数 left++
            // left<right 重合
            for(;arr[left]<=pivot && left<arr.length -1 && left<right;){
                left++;
            }
            // right< left 有序
            if(right>=left){
                // 交换
                int temp = arr[right];
                arr[right] = arr[left];
                arr[left] = temp;
                // 交换基准元素
                if(left == right){
                    temp = arr[left];
                    arr[left] = arr[0];
                    arr[0] = temp;
                }
                // 重新找基准元素
                pivot = arr[0];
                left = 1;
                right = arr.length - 1;

                System.out.println(Arrays.toString(arr));
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }

    // 分治法——合并得到问题的解？

}
