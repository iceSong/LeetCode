/**
 * 几种常见排序方式的java实现
 *
 * @author song
 * @date 3/21
 */
public class Sort {
    /**
     * 冒泡排序，稳定，最差O(N^2)，平均O(N^2), 空间O(1)
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int tmp = array[i];
                if (array[i] > array[j]) {
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序，稳定，最差O(N^2)，平均O(N^2), 空间O(1)
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 先假设第一个数是最小的
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    // 寻找本轮最小值
                    min = j;
                }
            }
            if (i != min) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }
        }
    }

    /**
     * 插入排序，稳定，最差O(N^2)，平均O(N^2), 空间O(1), 比前两个好
     */
    public static void insertSort(int[] array) {
        int tmp, j;
        // 假定第一个元素已经完成排序
        for (int i = 1; i < array.length; i++) {
            // j代表着已经完成排序的个数
            tmp = array[i];
            for (j = i; j > 0 && tmp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }

    /**
     * 快速排序，不稳定，最差O(N^2)，平均O(n*log2(N)), 空间O(log2n)~O(n)
     */
    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int key = array[left];
        // 选定第一个数作为基准值
        int low = left;
        int high = right;
        while (low < right) {
            while (low < high && key <= array[high]) {
                high--;
            }
            array[low] = array[high];
            array[high] = key;
            while (low < high && key >= array[low]) {
                low++;
            }
            array[high] = array[low];
            array[low] = key;
        }
        quickSort(array, left, low--);
        quickSort(array, low++, right);
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 0, 1, 43, 7, 3, 9, 23, 8, 90};
        //bubbleSort(data);
        //selectSort(data);
        //insertSort(data);
        quickSort(data, 0, data.length - 1);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print("\t");
        }
    }
}