/*
intensity는 경로에서 간선 가중치가 가장 큰 값임 -> minimax?
경로 방문 순서 자체는 별로 안 중요하고 간선 집합이 더 중요한듯 
-> 이때 같은 시작지점으로 돌아와야하는데 산봉우리 한번만 찍고 와야함
-> 양방향 그래프이므로 항상 간선 가중치 낮은것들만 고르면 될거 같은데 
-> 일종의 mst같은걸로 풀어야하나?
크루스칼 될거같은데 프림은 N^3이라 안될거같고
-> 양방향이라 출발지 -> 쉼터 산봉우리로 편도만 구하면됨
아니면 gates 다 넣고 max 가중치값 업뎃하는 다익스트라 해도 될듯

어차피 1-based 지점이니까 가상 지점하나 두고 disjion set union + kruskal
-> 가상 지점에서 산봉우리 값이 지정된게 확인되면 편도 경로 완성된거임
*/
import java.util.*;
class Solution {
    int[] parent;
    // minSummit[i] = i를 루트로하는 경로에서 최소 가중치 값을 가지는 산봉우리 번호
    int[] minSummit;
    
    Map<Integer, Integer> ss = new HashMap<>();
    
    int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    
    void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        // pb의 부모가 pa가 됨
        parent[pb] = pa;
        minSummit[pa] = Math.min(minSummit[pa], minSummit[pb]);
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        parent = new int[n+1];
        for(int i = 0; i<=n; i++)
            parent[i] = i;
        
        minSummit = new int[n+1];
        Arrays.fill(minSummit, Integer.MAX_VALUE);
        
        boolean[] isSummit = new boolean[n+1];
        for(int summit : summits)
            isSummit[summit] = true;
        
        for(int gate : gates)
            union(0, gate);
        
        Arrays.sort(paths, Comparator.comparingInt(a->a[2]));

        int idx = 0;
        while(idx < paths.length){
            int w = paths[idx][2];
            
            // 동일 가중치 가진 간선 처리
            while(idx < paths.length && paths[idx][2] == w){
                int u = paths[idx][0];
                int v = paths[idx][1];
                
                if(isSummit[u] && isSummit[v])
                    continue;

                // 한쪽이 산봉우리면 정보만 갱신
                if(isSummit[u]){
                    int root = find(v);
                    minSummit[root] = Math.min(minSummit[root], u);
                }
                else if(isSummit[v]){
                    int root = find(u);
                    minSummit[root] = Math.min(minSummit[root], v); 
                }
                else
                    union(u,v);
                
                idx++;
            }
            
            // 크루스칼이라 가중치 적은 순으로 처리했기 때문에
            // 가상지점 0번이 산봉우리 연결 가능한 순간의 minimax 가중치 값이 정답임
            int summit = minSummit[find(0)];
            if(summit != Integer.MAX_VALUE)
                return new int[]{summit, w};
        }
        
        return new int[]{-1,-1};
    }
}