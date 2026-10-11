/*
dp[i][j] = i에서 j번째 행렬까지 곱하기 위한 최소 곱셈연산 수

5 3 10
5 10 6

3 10 6
5 3 6
*/
import java.util.*;
class Solution {
    int INF = Integer.MAX_VALUE/2;
    // MS = matrix_sizes
    public int solution(int[][] MS) {
        int N = MS.length;
        int[][] dp = new int[N][N];
        for(int i = 0; i<N; i++)
            Arrays.fill(dp[i], INF);
        
        // 자기자신은 곱셈 안해도됨
        for(int i = 0; i<N; i++)
            dp[i][i] = 0;
        
        // 구간 dp 구간크기 k
        for(int k = 2; k<=N; k++){
            // 시작점 i
            for(int i = 0; i+k-1<N; i++){
                int j = i+k-1;
                // 좌변 우변 분할지점
                for(int mid = i; mid<j; mid++){
                    // 행렬 곱셈은 좌변 가로랑 우변 세로 크기가 같아야하고
                    // 좌변 세로 * 좌변 가로 * 우변 가로
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j] + MS[i][0] * MS[mid][1] * MS[j][1]);
                }   
            }
        }
        return dp[0][N-1];
    }
}