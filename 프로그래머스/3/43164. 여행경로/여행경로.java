import java.util.*;
/*
티켓을 한번씩 사용해야 하므로 티켓 id를 부여한 그래프를 구성하고 dfs+백트래킹으로 품
리턴해야하는 공항 수 = 티켓 수 + 1

*/

class Solution {
    HashMap<String, Integer> m = new HashMap<>();
    String[] airport;
    ArrayList<Edge>[] g;
    
    boolean[] visited;
    boolean found;

    static class Edge {
        int to;
        int id;

        Edge(int to, int id) {
            this.to = to;
            this.id = id;
        }
    }
    
    int[] ans;
    // 현재 도시 idx, 깊이, 총 티켓 수
    void dfs(int ci, int depth, int N){
        if(found)
            return;
        
        if(depth == N){
            found = true;
            return;
        }
        
        for(Edge e : g[ci]){
            int to = e.to;
            int id = e.id;
            
            if(visited[id])
                continue;
            visited[id] = true;
            ans[depth] = to;
            
            dfs(to, depth+1, N);
            
            if(found)
                return;
            visited[id] = false;
        }
    }
    
    public String[] solution(String[][] T) {
        // 총 등장하는 공항 종류 수 세어주기
        HashSet<String> ss = new HashSet<>();
        for(String[] s : T){
            ss.add(s[0]);
            ss.add(s[1]);
        }
        
        int N = ss.size();
        airport = new String[N];
        int idx = 0;

        for(String s : ss){
            airport[idx++] = s;
        }
        // 
        Arrays.sort(airport);
        
        for(int i= 0; i<N; i++){
            m.put(airport[i], i);
        }
        
        g = new ArrayList[N];
        for(int i = 0; i<N; i++)
            g[i] = new ArrayList<>();
        
        int num = 0;
        for(String[] s : T){
            g[m.get(s[0])].add(new Edge(m.get(s[1]), num++));    
        }
        for(int i = 0; i<N; i++){
            Collections.sort(g[i], (a,b)->{
                return Integer.compare(a.to, b.to);
            });
        }
        
        ans = new int[T.length+1];
        ans[0] = m.get("ICN");
        visited = new boolean[T.length];
        dfs(m.get("ICN"), 1, T.length+1);
        
        String[] sb = new String[T.length+1];
        int ii = 0;
        for(int i : ans){
            sb[ii++] = airport[i];
        }
        return sb;
    }
}