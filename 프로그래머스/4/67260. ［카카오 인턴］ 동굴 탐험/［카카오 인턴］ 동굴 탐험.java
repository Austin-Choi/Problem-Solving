import java.util.*;
/*
위상정렬로 사이클 유무
-> 방문관계상 사이클 있으면 false, 사이클 없으면 true

그래프에서
트리 간선 indegree로 넣고 
order도 넣음

그리고 위상정렬하고 count가 n이 아니라면 사이클 발생한거임
*/

class Solution {
    // 위상 정렬 전에 path를 먼저 0을 부모로 하는 그래프로 만들어야함
    ArrayList<Integer>[] tree;
    ArrayList<Integer>[] g;
    int[] indegree;
    
    boolean topologySort(int N){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            if(indegree[i] == 0)
                q.add(i);
        }
        int count = 0;
        
        while(!q.isEmpty()){
            int ci  = q.poll();
            count++;
            
            for(int ni : g[ci]){
                if(--indegree[ni] == 0){
                    q.add(ni);
                }
            }
        }
        // 모든 정점 처리하면 사이클 없음
        return count == N;
    }
    
    public boolean solution(int n, int[][] path, int[][] order) {
        // 1) 먼저 0을 루트로 하는 tree에 대해서 
        // 부모관계 정해둠 parent에 저장
        tree = new ArrayList[n];
        g = new ArrayList[n];
        indegree = new int[n];

        for(int i = 0; i<n; i++){
            g[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        
        for(int[] p : path){
            tree[p[0]].add(p[1]);
            tree[p[1]].add(p[0]);
        }
        
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        parent[0] = 0;
        
        while(!q.isEmpty()){
            int ci = q.poll();
            
            for(int ni : tree[ci]){
                if(parent[ni] != -1)
                    continue;
                parent[ni] = ci;
                q.add(ni);
            }
        }
        
        // 2) parent에 따라서 dag로 g에 path저장하고
        // 위상정렬로 사이클 확인할거니까 order도 g에 저장하고
        // 위상정렬할때 횟수가 정점 갯수가 아니면 사이클 발생한거임
        // -> 0에서 시작해야하니까 0은 제외 
        for(int i = 1; i<n; i++){
            g[parent[i]].add(i);
            indegree[i]++;
        }
        for(int[] o : order){
            g[o[0]].add(o[1]);
            indegree[o[1]]++;
        }
        
        return topologySort(n);
    }
}