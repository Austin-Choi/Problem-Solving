/*
dfs로 하는 트리 dp
dp[u] = u를 노드로 하는 가중치의 합
전체 가중치의 합이 0이 아니면 불가능

a가 30만이라 dfs로 하니까 스택 오버플로나는데 재귀말고 스택 dfs로 해야할듯
*/
import java.util.*;
class Solution {
    ArrayList<Integer>[] g;
    long[] dp;
    long ans = 0;
        
    // long dfs(int cur, int parent){
    //     for(int n : g[cur]){
    //         if(n == parent)
    //             continue;
    //         dp[cur] += dfs(n, cur);
    //     }
    //     // 연산 횟수니까 abs
    //     ans += Math.abs(dp[cur]);
    //     return dp[cur];
    // }
    
    public long solution(int[] a, int[][] edges) {
        long sum = 0;
        int N = a.length;
        // dp 초깃값은 각 노드에 있는 처음 가중치
        dp = new long[N];
        for(int i = 0; i<N; i++){
            sum += a[i];
            dp[i] = a[i];
        }
        
        if(sum != 0)
            return -1;
        
        g = new ArrayList[N];
        for(int i = 0; i<N; i++)
            g[i] = new ArrayList<>();
        
        for(int[] e : edges){
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        //dfs(0, -1);
        // 각 노드 부모노드 저장해야하고 방문 순서 저장할 배열 필요
        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        int[] order = new int[N];
        int size = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(0);
        parent[0] = 0;
        
        while(!stack.isEmpty()){
            int cur = stack.pollFirst();
            order[size++] = cur;
            
            for(int n : g[cur]){
                if(n == parent[cur])
                    continue;
                parent[n] = cur;
                stack.addFirst(n);
            }
        }
        
        // dfs로 방문순서 정했으면 역순으로 리프노드부터 부모에게 누적해서
        // 0이 루트노드일때 전체 트리 최소 연산횟수 구하기
        for(int i = size-1; i>0; i--){
            int cur = order[i];
            ans += Math.abs(dp[cur]);
            dp[parent[cur]] += dp[cur]; 
        }
        
        return ans;
    }
}