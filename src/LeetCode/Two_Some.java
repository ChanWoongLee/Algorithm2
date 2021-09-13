package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

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
