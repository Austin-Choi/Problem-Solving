// 트리고 edge의 양쪽 끝 중 하나는 켜져 있어야 함
// 상태는 dp[i][0,1]
// dfs로 순회하면서 부모방향 막는 parent 쓰고
// 트리 dp (후위 순회, 자식 먼저 다 돌고 부모로 와서 값 sum)
import java.util.*;

class Solution {
    ArrayList<Integer>[] g;
    int[][] dp;
    
    void dfs(int cur, int parent){
        dp[cur][0] = 0;
        dp[cur][1] = 1;
        
        for(int next : g[cur]){
            if(next == parent)
                continue;
            
            dfs(next, cur);
            // 지금게 꺼져있으면 옆은 켜져야함
            dp[cur][0] += dp[next][1];
            // 지금게 켜져있으면 옆은 켜지거나 꺼져있거나 상관없는데 최소로 켜야하니까
            dp[cur][1] += Math.min(dp[next][1], dp[next][0]);
        }
    }
    
    public int solution(int n, int[][] E) {
        g = new ArrayList[n+1];
        for(int i = 1; i<=n; i++)
            g[i] = new ArrayList<>();
        
        for(int[] ee : E){
            int u = ee[0];
            int v = ee[1];
            g[u].add(v);
            g[v].add(u);
        }
        
        // 정점 i를 루트로 하는 트리에서 i가 0 꺼져 있을때 1 켜져있을때 최소 등대 갯수
        dp = new int[n+1][2];
        dfs(1,0);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
}