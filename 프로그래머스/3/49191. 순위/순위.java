import java.util.*;
/*
순위 관계 알려면 플로이드 워셜 쓰기
-> 모든 도달 관계 알아야 함
*/

class Solution {
  
    public int solution(int n, int[][] r) {
        boolean[][] g = new boolean[n+1][n+1];
        for(int[] rr : r){
            g[rr[0]][rr[1]] = true;
        }
        
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(g[i][k] && g[k][j]){
                        g[i][j] = true;
                    }
                }
            } 
        }
        
        int ans = 0;
        // 모든 정점에 대해 연결관계 n-1개 존재해야함
        for(int i = 1; i<=n; i++){
            int cnt = 0;
            for(int j = 1; j<=n; j++){
                if(g[i][j] || g[j][i])
                    cnt++;
            }
            
            if(cnt == n-1)
                ans++;
        }
        return ans;
    }
}