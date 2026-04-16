import java.util.*;
import java.io.*;
/*
scc까진 아닌거같고-> 무방향이니까 
그냥 모든 정점에 대해서 visited 공유하고 
bfs로 방문 그냥 하고 bfs 호출 갯수가 comp 수

*/

class Solution {
    static ArrayList<Integer>[] g;
    static int[] visited;
    static int vNum = 1;
    
    static void bfs(int si){
        Queue<Integer> q = new ArrayDeque<>();
        visited[si] = vNum;
        q.add(si);
        
        while(!q.isEmpty()){
            int ci = q.poll();
            
            for(int next : g[ci]){
                if(visited[next] == 0){
                    visited[next] = vNum;
                    q.add(next);
                }
            }
        }
    }
    
    // v, input
    public int solution(int N, int[][] in) {
        int ans = 0;
        visited = new int[N];
        
        g = new ArrayList[N];
        for(int i = 0; i<N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 0; i<N; i++){
            for(int j= 0; j<N; j++){
                if(in[i][j] == 1){
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        
        for(int i = 0; i<N; i++){
            if(visited[i] == 0){
                bfs(i);
                vNum++;
            }
        }
        
        return vNum-1;
    }
}