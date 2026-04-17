import java.util.*;
/*
char[]로 비교해서 다른단어 한개만 달려있으면 정점간 연결 있는 것
양방향
50*50이니까 그래프 형성할때 단순 N^2 써도될듯
가중치 1이니까 bfs로 근데 결국 dist 쓰긴써야할듯

반례
: 입력이 이미 words에 존재할 수 있음.
*/

class Solution {
    ArrayList<Integer>[] g;
    HashMap<String, Integer> m = new HashMap<>();
    int M;
    
    boolean isOneDiff(String a, String b){       
        int cnt = 0;
        for(int i = 0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i))
                cnt++;
            if(cnt > 1)
                return false;
        }
        if(cnt == 1)
            return true;
        else
            return false;
    }
    
    final int INF = 50 * 50 + 1;
    // 한번만 돌리고 dist 쓸거니까 visit안씀
    int dijkstra(String begin, String target){
        int si = m.get(begin);
        int ei = m.get(target);
        
        int[] dist = new int[M];
        Arrays.fill(dist, INF);
        
        // next, dist
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        dist[si] = 0;
        q.add(new int[]{si, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int ni : g[ci]){
                if(dist[ni] > dist[ci]+1){
                    dist[ni] = cd+1;
                    q.add(new int[]{ni, cd+1});
                }
            }
        }
        
        return dist[ei];
    }
    
    public int solution(String begin, String target, String[] words) {
        int N = words.length;
        int vNum = 0;
        m.put(begin, vNum++);
        for(int i = 0; i<N; i++){
            if(!m.containsKey(words[i]))
                m.put(words[i], vNum++);
        }
        
        if(!m.containsKey(target))
            return 0;
        
        M = vNum;
        g = new ArrayList[M];
        for(int i = 0; i<M; i++)
            g[i] = new ArrayList<>();
        
        ArrayList<String> ks = new ArrayList<>(m.keySet());
        for(int i = 0; i<ks.size(); i++){
            // i+1로 중복없애고 양방향입력
            for(int j = i+1; j<ks.size(); j++){
                String k = ks.get(i);
                String l = ks.get(j);
                if(isOneDiff(k,l)){
                    int u = m.get(k);
                    int v = m.get(l);
                    g[u].add(v);
                    g[v].add(u);
                }
            }
        }
        
        int ans = dijkstra(begin, target);
        return ans == INF ? 0 : ans;
    }
}