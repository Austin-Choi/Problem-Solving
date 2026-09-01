import java.util.*;
/*다익 돌려서 최단경로 구하고 최대 값 가진 노드수 구하기 대신 도달불가 노드 제외*/
class Solution {
    ArrayList<Integer>[] g;
    
    int[] dijkstra(int N){
        int[] dist = new int[N+1];
        Arrays.fill(dist, 50001);
        dist[1] = 0;
        // 현재 노드, 간선 수
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        q.add(new int[]{1, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(cd != dist[ci])
                continue;
        
            for(int ni : g[ci]){
                if(dist[ni] > cd + 1){
                    dist[ni] = cd + 1;
                    q.add(new int[]{ni, cd+1});
                }
            }
        }
        
        return dist;
    }
    
    public int solution(int n, int[][] edge) {
        g = new ArrayList[n+1];
        for(int i =1 ; i<=n; i++){
            g[i] = new ArrayList<>();
        }
        
        for(int i = 0; i<edge.length; i++){
            g[edge[i][0]].add(edge[i][1]);
            g[edge[i][1]].add(edge[i][0]);
        }
        
        int[] dist = dijkstra(n);
        int max = 0;
        for(int i = 1; i<=n; i++){
            if(dist[i] == 50001)
                continue;
            max = Math.max(max, dist[i]);
        }
        int cnt = 0;
        for(int i = 1; i<=n; i++){
            if(dist[i] == max)
                cnt++;
        }
        return cnt;
    }
}