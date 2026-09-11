import java.util.*;
/*
S에서부터 x까지 공통으로 택시 요금을 지불하고 
플로이드로 최단경로 구한다음에 
s,k + k,a + k,b min 값 구하기
*/

class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = Integer.MAX_VALUE;
        int[][] dist = new int[n+1][n+1];
        for(int i =1; i<=n; i++){
            Arrays.fill(dist[i], INF);
        }
        // 이거 있어야함
        for(int i = 1; i<=n; i++){
            dist[i][i] = 0;
        }
        
        for(int[] fare : fares){
            int u = fare[0];
            int v = fare[1];
            int c = fare[2];
            dist[u][v] = c;
            dist[v][u] = c;
        }
        
        
        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        int answer = INF;
        // 3 점에서의 최단거리를 갖는 분할점 찾기
        for(int k = 1; k<=n; k++){
            answer = Math.min(answer, dist[s][k] + dist[k][a] + dist[k][b]);
        }
        return answer;
    }
}