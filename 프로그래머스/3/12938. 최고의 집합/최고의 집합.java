/*
만약 S/N이 0이면 -1
곱이 최대가 되려면 S/N이 N개만큼 있고 S%N을 균등하게 분배하기

1 9 2 8 3 7 4 6 5 5
합이 10이고 n이 2라고 하면 9,16,21,24,25 
합이 13이고 n이 2라고 하면
1 13 2 11 3 10 4 9 5 8 6 7
133 22 30 36 40 42
*/
import java.util.*;
class Solution {
    public int[] solution(int N, int S) {
        if(S/N == 0)
            return new int[]{-1};
        int remainder = S%N;
        int[] rst = new int[N];
        Arrays.fill(rst, S/N);
        for(int i = N-1; i>=0; i--){
            if(remainder == 0)
                break;
            rst[i]++;
            remainder--;
        }
        return rst;
    }
}