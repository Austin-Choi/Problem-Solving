class Solution {
    public int[] getConcatenation(int[] nums) {
        int N = nums.length;
        int[] rst = new int[N*2];
        for(int i = 0; i<2; i++){
            for(int j = 0; j<N; j++){
                if(i == 1)
                    rst[j + N] = nums[j];
                else
                    rst[j]=nums[j];
            }
        }
        return rst;
    }
}