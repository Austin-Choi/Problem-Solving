class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int N = nums.length;
        int rst = 0;
        int cur = 0;
        for(int i = 0; i<N; i++){
            if(nums[i] != 1){
                rst = Math.max(rst, cur);
                cur = 0;
            }
            else
                cur++;
        }
        return Math.max(rst, cur);
    }
}