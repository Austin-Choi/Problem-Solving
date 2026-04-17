import java.util.*;
/*
trap을 비트마스크로 trap on off 표현
u, v를 잇는 간선을 방문하면 u XOR v = true면 rg, false면 g
dist[N+1][mask] = last 방문 노드가 i이고 trap 상태 j일때 최단거리
*/

class Solution {
    ArrayList<int[]>[] g;
    ArrayList<int[]>[] rg;
    int N;
    // 함정노드인지 구별
    boolean[] T;
    // 함정노드 수
    int tn;
    int[] trapIdx;
    final int INF = 3000 * 3000 + 1;
    
    int dijkstra(int si, int ei){
        int[][] dist = new int[N+1][1<<tn];
        for(int i = 1; i<=N; i++){
            Arrays.fill(dist[i], INF);
        }
        // 시작노드는 함정노드가 아니므로
        dist[si][0] = 0;
        // ni, mask, dist
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));
        pq.add(new int[]{si, 0, 0});
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int ci = cur[0];
            int cMask = cur[1];
            int cd = cur[2];
            
            if(dist[ci][cMask] != cd)
                continue;
            
            int nMask = cMask;
            // 함정노드면
            if(T[ci]){
                nMask ^= (1 << trapIdx[ci]);
            }
            
            // 정방 역방 따로따로 계산함
            // 정방일때는 xor 결과가 정방일때만 역방도 똑같이
            for(int[] next : g[ci]){
                int ni = next[0];
                int nc = next[1];
                
                boolean uu = false;
                if(T[ci])
                    uu = (nMask & (1 << trapIdx[ci])) != 0;
                
                boolean vv = false;
                if(T[ni])
                    vv = (nMask & (1<< trapIdx[ni])) != 0;
                
                if(!(uu ^ vv)){
                    if(dist[ni][nMask] > cd + nc){
                        dist[ni][nMask] = cd + nc;
                        pq.add(new int[]{ni, nMask, dist[ni][nMask]});
                    }
                }
            }
            
            for(int[] next : rg[ci]){
                int ni = next[0];
                int nc = next[1];
                
                boolean uu = false;
                if(T[ci])
                    uu = (nMask & (1 << trapIdx[ci])) != 0;
                
                boolean vv = false;
                if(T[ni])
                    vv = (nMask & (1<< trapIdx[ni])) != 0;
                
                // 역방은 xor결과 true
                if((uu ^ vv)){
                    if(dist[ni][nMask] > cd + nc){
                        dist[ni][nMask] = cd + nc;
                        pq.add(new int[]{ni, nMask, dist[ni][nMask]});
                    }
                }
            }
            
        }
        
        int ans = INF;
        for(int mask = 0; mask < (1<<tn); mask++){
            ans = Math.min(ans, dist[ei][mask]);
        }
        return ans;
    }
    
    public int solution(int n, int s, int e, int[][] roads, int[] traps) {
        N = n;
        tn = traps.length;
        trapIdx = new int[N+1];
        
        T = new boolean[N+1];
        for(int i = 0; i<tn; i++){
            trapIdx[traps[i]] = i;
            T[traps[i]] = true;
        }
        
        g = new ArrayList[N+1];
        rg = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }
        
        for(int i = 0 ; i<roads.length; i++){
            int u = roads[i][0];
            int v = roads[i][1];
            int c = roads[i][2];
            g[u].add(new int[]{v,c});
            rg[v].add(new int[]{u,c});
        }
        
        int ans = dijkstra(s, e);
        return ans;
        
    }
}