import java.util.*;
/*
격자 dp인데 물웅덩이는 못감
물웅덩이 전처리시켜서 좌표 O(1) 조회
-> 좌표가 100 이하니까 bool[][]로 바꿔서 체킹

좌표 m,n -> 뒤집혀있음 입력 주의
*/

class Solution {
    final int MOD = 1_000_000_007;
    boolean[][] pudd;

    public int solution(int m, int n, int[][] p) {
        int[][] dp = new int[n][m];
        pudd = new boolean[n][m];
        
        for(int k= 0; k<p.length; k++){
            pudd[p[k][1]-1][p[k][0]-1] = true;
        }
        
        dp[0][0] = 1;
        // 초기화에서 물웅덩이 있으면 전파되면 안될거같음
        for(int i= 1 ; i<n; i++){
            if(pudd[i][0])
                dp[i][0] = 0;
            else
                dp[i][0] = dp[i-1][0];
        }
        for(int j = 1; j<m; j++){
            if(pudd[0][j])
                dp[0][j] = 0;
            else
                dp[0][j] = dp[0][j-1];
        }
        
        
        for(int i = 1; i<n; i++){
            for(int j= 1; j<m; j++){            
                if(pudd[i][j])
                    dp[i][j] = 0;
                else
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }
        return dp[n-1][m-1];
    }
}