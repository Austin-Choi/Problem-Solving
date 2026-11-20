/*
격자 dp인데 cityMap 제한 보고 결정
dp[i][j][2] = i,j 위치까지 올수 있는 방법의 총 갯수 0,1로 방향구분
*/
class Solution {
    int MOD = 20170805;
    // m 세로크기, n 가로크기
    public int solution(int m, int n, int[][] cityMap) {
        if(m == 1 && n == 1)
            return 1;
        
        int[][][] dp = new int[m][n][2];
        // 초기화
        // 가로는 1방향만 1로
        for(int j = 0; j<n; j++){
            if(cityMap[0][j] == 1)
                break;
            dp[0][j][1] = 1;
        }
        // 세로는 0방향만 1로
        for(int i = 0; i<m; i++){
            if(cityMap[i][0] == 1)
                break;
            dp[i][0][0] = 1;
        }
        
        // cityMap 방향으로 이전이 2일때는 그거 현재 위치와 방향에 저장
        for(int i = 1; i<m; i++){
            for(int j = 1; j<n; j++){
                if(cityMap[i][j] == 1)
                    continue;
                // 위쪽에서 내려오는 경로
                if(cityMap[i-1][j] == 2)
                    dp[i][j][0] = dp[i-1][j][0];
                else
                    dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                
                // 왼쪽에서 오는 경로
                if(cityMap[i][j-1] == 2)
                    dp[i][j][1] = dp[i][j-1][1];
                else
                    dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
            }
        }
        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }
}