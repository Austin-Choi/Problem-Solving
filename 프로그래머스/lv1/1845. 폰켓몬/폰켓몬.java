import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int i : nums)
        {
            s.add(i);
        }
        System.out.println(s);
        if(s.size() <= nums.length / 2)
            return s.size();
        else
            return nums.length/2;
    }
}