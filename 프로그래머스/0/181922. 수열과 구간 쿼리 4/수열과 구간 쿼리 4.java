class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int[] query : queries){
            int s = query[0];
            int e = query[1];
            int k = query[2];

            for(;s<=e;s++){
                if(s%k==0)
                    arr[s] += 1;
            }

        }
        return arr;
    }
}