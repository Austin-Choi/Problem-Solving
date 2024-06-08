class Solution {
    public int solution(int[] num_list) {
        int sum = 0;
        int multpl = 1;
        
        for(int n : num_list){
            sum += n;
            multpl *= n;
        }
        
        if(multpl < sum*sum)
            return 1;
        else
            return 0;
    }
}