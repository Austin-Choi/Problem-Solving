import java.util.*;
/*
경로 길이 자체를 따지고 있고
양방향이라서 머 뒤집어도 별거없을거같긴한데
a->b 최단경로 = b->a 최단경로 성질 이용해서 풀면될듯
도착 -> 소스들 bfs 한번으로 (가중치 1이니까)

1-based vertex
*/

class Solution {
    ArrayList<Integer>[] g;
    final int INF = 500_001;
    
    int[] bfs(int n, int si){
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[si] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int next : g[ci]){
                if(dist[next] > cd + 1){
                    dist[next] = cd + 1;
                    q.add(new int[]{next, cd + 1});
                }
            }
        }
        return dist;
    }
    
    public ArrayList<Integer> solution(int n, int[][] board, int[] ss, int dest) {
        g = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            g[i] = new ArrayList<>();
        }
        
        for(int a = 0; a<board.length; a++){
            int[] info = board[a];
            int u = info[0];
            int v = info[1];
            g[u].add(v);
            g[v].add(u);
        }
        
        // bfs 결과 dist 가지고 와서 
        // ss에 있는것들만 추려서 출력
        int[] rst = bfs(n, dest);
        ArrayList<Integer> ans = new ArrayList<>();
        for(int s : ss){
            if(rst[s] == INF)
                ans.add(-1);
            else
                ans.add(rst[s]);
        }
        return ans;
    }
}