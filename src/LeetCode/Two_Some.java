package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
// 단순히 찾다 라는 개념을 순차적으로 탐색한다는 생각을 하지말고 컴퓨터를 이용해
// hashmap으로 찾는다던가 여러 방법이 있다는것을 기억하자
public class Two_Some {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hm.put(target - nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i]) && i != hm.get(nums[i])) {
                return new int[]{i, hm.get(nums[i])};
            }
        }
        return  new int[]{};
    }

}
