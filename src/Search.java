/**
 * 几种常见查询方式的java实现
 *
 * @author song
 * @date 3/21
 */
public class Search {
    /**
     * 二分查找, 要求数组已经排好序，时间复杂为log2(N)
     */
    public static int binarySearch(int value, int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (end > start) {
            int index = (end + start) / 2;
            if (array[index] == value) {
                return index;
            } else if (array[index] > value) {
                end = index - 1;
            } else {
                start = index + 1;
            }
        }
        // 在目标数组中不存在
        return -1;
    }

    public static void main(String[] args) {
        // 测试二分查找
        System.out.println(binarySearch(3, new int[]{1, 2, 3, 4, 5, 6}));
    }
}