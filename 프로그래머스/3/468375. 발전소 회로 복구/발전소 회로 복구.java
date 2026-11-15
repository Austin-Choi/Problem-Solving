/*
그래프로 모델링하게 되면 각 패널의 위치 - 엘리베이터까지의 bfs 길이가 가중치가 됨.
-> 같은 층에 있는 패널도 존재함

1번 패널에서 시작하므로 indegree가 0인 정점들 간선비용 + 1의 간선비용(해당 정점들중 1이 아닌 것들만 더하기)
로 시작함
-> seqs에서 [a,b] 이렇게 주어지는데 정점이 최대 15개이므로 
-> int seq[i] = i번을 활성화하기 위한 mask 상태 : seqs 읽으면서 b에다가 1<<a 를 OR 연산해서 붙여줌
-> 굳이 위상정렬 필요 없는게 (mask & seq[i]) == seq[i] 인지 보면 됨

dp[i][mask] = 기술자가 마지막으로 방문한 패널이 i이고 현재 방문상태가 mask일때 최소 비용
-> TSP 문제
*/
import java.util.*;

class Solution {
    int ei, ej;
    int[] di = {-1,0,1,0};
    int[] dj = {0,1,0,-1};
    char[][] board;
    int N,M;
    
    int bfs(int ai, int aj, int bi, int bj){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ai,aj,0});
        boolean[][] v = new boolean[N][M];
        v[ai][aj] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cc = cur[2];
            
            if(ci == bi && cj == bj)
                return cc;
            
            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                
                if(ni < 0 || ni >= N || nj < 0 || nj >= M)
                    continue;
                if(board[ni][nj] == '#')
                    continue;
                if(v[ni][nj])
                    continue;
                
                v[ni][nj] = true;
                q.add(new int[]{ni,nj,cc+1});
            }
        }
        
        return 0;
    }
    
    long ijToKey(int i, int j){
        return ((long) i << 16) | j;
    }
    
    // int[] keyToIJ(long key){
    //     return new int[]{(int)(key >> 16), (int)(key - ((key>>16)<<16))};
    // }
    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        N = grid.length;
        M = grid[0].length();
        
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            char[] temp = grid[i].toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = temp[j];
                if(board[i][j] == '@'){
                    ei = i;
                    ej = j;
                }
            }
        }
        
        // 패널들끼리의 거리 bfs로 전처리
        int K = panels.length;
        Map<Long, Integer> vertices = new HashMap<>();
        
        for(int i = 0; i<K; i++){
            for(int j = i+1; j<K; j++){
                int ih = panels[i][0];
                int jh = panels[j][0];
                
                int ii = panels[i][1]-1;
                int ij = panels[i][2]-1;
                int ji = panels[j][1]-1;
                int jj = panels[j][2]-1;
                
                int cc = 0;
                // 같은 층이면 ii,ij <-> ji,jj 구하고
                // 다른 층이면 ii,ij <-> 엘리베이터 <-> ji,jj 구하기 + 층이동비용
                if(ih == jh)
                    cc = bfs(ii,ij,ji,jj);
                else
                    cc = bfs(ii,ij,ei,ej) + bfs(ei,ej,ji,jj) + Math.abs(ih-jh);
                
                vertices.put(ijToKey(i+1,j+1),cc);
                vertices.put(ijToKey(j+1,i+1),cc);
            }
        }
        
        for(int i = 1; i<=K; i++){
            vertices.put(ijToKey(i,i),0);
        }
        
        // 정점 방문순서용 마스크 전처리
        // 1-based 패널 번호
        int[] seq = new int[K+1];
        for(int i = 0; i<seqs.length; i++){
            int a = seqs[i][0];
            int b = seqs[i][1];
            seq[b] |= (1<<a);
        }
        
        // TSP 비트마스트 dp로 최소 전체 비용구하기
        int[][] dp = new int[K+1][1<<(K+1)];
        for(int i = 0; i<=K; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[1][0] = 0;
        
        for(int mask = 0; mask < 1<<(K+1); mask++){
            for(int i = 1; i<=K; i++){
                // 전이 불가능 상태는 전이안함
                if(dp[i][mask] == Integer.MAX_VALUE)
                    continue;
                
                for(int j = 1; j<=K; j++){
                    if((mask & (1<<j)) != 0)
                        continue;
                    // 방문순서 조건 있으니까 이미 mask에 해당 방문 상태 들어가있어야함
                    if((mask & seq[j]) != seq[j])
                        continue;
                    
                    int nextMask = mask | (1<<j);
                    dp[j][nextMask] = Math.min(dp[j][nextMask], dp[i][mask] + vertices.get(ijToKey(i,j)));
                }
            }
        }
        
        // 0은 아무것도 안 고른거고 1부터 시작해야
        // 0번째 비트는 안 쓰니까 전체 선택된 비트는 마지막만 0임
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<=K; i++)
            ans = Math.min(ans, dp[i][(1<<(K+1))-1^1]);
        return ans;
    }
}