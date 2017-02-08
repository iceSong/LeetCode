package two.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，返回两个数字的索引，使得它们相加到一个特定的目标。
 *
 * @author song.liu@ele.me
 * @version v1.0，2017-02-07 17:01
 */
public class TwoSum {

    public static void main(String[] args) {
        SolutionA solutionA = new TwoSum().new SolutionA();
        int[] answer = solutionA.twoSum(new int[]{1, 2, 4, 3, 6, 9, 7, 4, 8, 12, 32}, 5);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    /**
     * 1. 先对数组进行过排序
     * 2. 从排序后数组两端开始，定义两个游标向中间移动，大于目标值减小右边的游标，小于目标则增加左边的游标
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    class SolutionA {
        public int[] twoSum(int[] nums, int target) {
            int[] copy = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                copy[j] = nums[j];
            }
            Arrays.sort(copy);  // 从小到大排序
            int[] answer = new int[2];

            int l = 0, r = copy.length - 1;
            while (l < r && l >= 0 && r < copy.length) {
                int temp = copy[l] + copy[r];
                if (temp > target) {
                    r--;
                } else if (temp < target) {
                    l++;
                } else {
                    break;
                }
            }

            boolean rr = true, ll = true;
            for (int k = 0; k < nums.length; k++) {
                if (nums[k] == copy[l] && ll) {
                    answer[0] = k;
                    ll = false;
                } else if (nums[k] == copy[r] && rr) {
                    answer[1] = k;
                    rr = false;
                }
            }
            return answer;
        }
    }

    /**
     * HashMap查找速度最快为O(1),最差为O(n),整体速度取决于hash算法的优劣
     * (Two-pass Hash Table)
     * To improve our run time complexity, we need a more efficient way to check if the complement exists in the array.
     * If the complement exists, we need to look up its index. What is the best way to maintain a mapping of each element
     * in the array to its index? A hash table. We reduce the look up time from O(n)O(n) to O(1)O(1) by trading space for
     * speed. A hash table is built exactly for this purpose, it supports fast look up in near constant time. I say "near"
     * because if a collision occurred, a look up could degenerate to O(n)O(n) time. But look up in hash table should be
     * amortized O(1)O(1) time as long as the hash function was chosen carefully. A simple implementation uses two iterations.
     * In the first iteration, we add each element's value and its index to the table. Then, in the second iteration we check
     * if each element's complement (target - nums[i]target−nums[i]) exists in the table. Beware that the complement mus
     * -t not be nums[i]nums[i] itself!
     * <p>
     * Complexity Analysis:
     * Time complexity : O(n)O(n). We traverse the list containing nn elements exactly twice. Since the hash table reduc
     * -es the look up time to O(1)O(1), the time complexity is O(n)O(n).
     * Space complexity : O(n)O(n). The extra space required depends on the number of items stored in the hash table, wh
     * -ich stores exactly nn elements.
     */
    class SolutionB {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[]{i, map.get(complement)};
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }


    /**
     * It turns out we can do it in one-pass. While we iterate and inserting elements into the table, we also look back
     * to check if current element's complement already exists in the table. If it exists, we have found a solution and
     * return immediately.
     * <p>
     * Time complexity : O(n)O(n). We traverse the list containing nn elements only once. Each look up in the table cost
     * -s only O(1)O(1) time.
     * Space complexity : O(n)O(n). The extra space required depends on the number of items stored in the hash table, wh
     * -ich stores at most nn elements.
     */
    class SolutionC {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
