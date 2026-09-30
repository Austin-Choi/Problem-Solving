/* 
북동남서로 di,dj 구성하고 
최단비용을 구해야하니까 dist[i][j][d] = 시작에서 i,j 칸에 d방향 상태로 도달하기까지 최소비용
queue에는 i,j,dir,cost dir는 방향
처음에 큐에 초깃값 넣을때 1,2 넣는데 (동,남) 그 방향에 벽있으면 안넣음 cost 100
*/
import java.util.*;
class Solution {
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    int INF;
    
    public int solution(int[][] B) {
        int N = B.length;
        INF = 400*N*N+1;
        
        int[][][] dist = new int[N][N][4];
        for(int i =0 ; i<N; i++){
            for(int j = 0; j<N; j++){
                Arrays.fill(dist[i][j], INF);
            }
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[3]));
        if(B[0][1] != 1){
            q.add(new int[]{0,1,1,100});
            dist[0][1][1] = 100;
        }
        if(B[1][0] != 1){
            q.add(new int[]{1,0,2,100});
            dist[1][0][2] = 100;
        }
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            int cc = cur[3];
            
            if(dist[ci][cj][cd] != cc)
                continue;
            
            // 현재 방향이랑 다르면 cost 400
            // 아니면 100 
            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(B[ni][nj] == 1)
                    continue;
                int nc = cc + 100;
                if(d != cd)
                    nc += 500;

                if(dist[ni][nj][d] > nc){
                    dist[ni][nj][d] = nc;
                    q.add(new int[]{ni,nj,d,dist[ni][nj][d]});
                }
            }
        }
        
        int ans = INF;
        for(int d = 0; d<4; d++){
            ans = Math.min(ans, dist[N-1][N-1][d]);
        }
        return ans;
    }
}