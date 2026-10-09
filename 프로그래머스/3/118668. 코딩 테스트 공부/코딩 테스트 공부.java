/*
dp[a][c] = 알고력 a, 코딩력 c에 도달하기 위한 최소 시간
*/
import java.util.*;

class Solution {
    int INF = Integer.MAX_VALUE;
    
    public int solution(int alp, int cop, int[][] ps) {
        int maxA = 0;
        int maxC = 0;
        
        for(int[] p : ps){
            maxA = Math.max(maxA, p[0]);
            maxC = Math.max(maxC, p[1]);
        }
        
        if(alp >= maxA && cop >= maxC)
            return 0;
        
        // 초기 알고력과 코딩력을 조정해야함 
        // 만약 위 조건에 안 걸린다면 maxA를 넘는 초기 능력이 
        // 인덱스 밖을 접근할 수 있음
        alp = Math.min(alp, maxA);
        cop = Math.min(cop, maxC);
        
        int[][] dp = new int[maxA+2][maxC+2];
        
        for(int i = 0; i<=maxA+1; i++)
            Arrays.fill(dp[i], INF);
        
        // 초기화
        dp[alp][cop] = 0;
        
        for(int a = alp; a<=maxA; a++){
            for(int c = cop; c<=maxC; c++){
                if(dp[a][c] == INF)
                    continue;
                // 공부하기
                dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c] + 1);
                dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c] + 1);
                
                // 현재 알고력 코딩력으로 풀수있는 문제 있으면 풀고 전이하기
                for(int[] p : ps){
                    int na = Math.min(a+p[2], maxA);
                    int nc = Math.min(c+p[3], maxC);
                    
                    if(a >= p[0] && c>=p[1]){
                        dp[na][nc] = Math.min(dp[na][nc], dp[a][c] + p[4]);
                    }
                }
            }
        }
        return dp[maxA][maxC];
    }
}