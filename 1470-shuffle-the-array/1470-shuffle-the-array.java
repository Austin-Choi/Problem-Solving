class Solution {
    public int[] shuffle(int[] nums, int n) {
        int N = nums.length;
        int[] rst = new int[N];
        for(int i = 0; i<N; i++){
            if(i%2==1){
                rst[i] = nums[i/2+n];
            }
            else
                rst[i] = nums[i/2];
        }
        return rst;
    }
}