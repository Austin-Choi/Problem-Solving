import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,A,B;
    static ArrayList<int[]>[] g;
    static final long INF = 100_000L * 100_000 + 1;
    static ArrayList<int[]>[] sg;

    // 반환형을 boolean으로 해야 더이상 안보고 가지치기함
    static ArrayList<Integer> path = new ArrayList<>();

    static boolean dfs(int cur){
        path.add(cur);

        if(cur == B)
            return true;
        
        for(int[] n : sg[cur]){
            int ni = n[0];
            if(dfs(ni))
                return true;
        }

        path.remove(path.size()-1);
        return false;
    }

    static long[] dijkstra(){
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        q.add(new long[]{A, 0});
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[A] = 0;

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];

            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];
                if(dist[ni] > dist[ci]+nd){
                    dist[ni] = dist[ci]+nd;
                    q.add(new long[]{(long) ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        A = read();
        B = read();

        long[] dist = dijkstra();
        // 최단경로가 여러가지가 가능하니까 여기서 dist를 가지고 
        // 최단경로 DAG를 만들어야 함
        sg = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            sg[i] = new ArrayList<>();

        for(int i =1; i<=N; i++){
            for(int[] n : g[i]){
                int ni = n[0];
                int nd = n[1];
                if(dist[i] + nd == dist[ni]){
                    sg[i].add(new int[]{ni, nd});
                }
            }
        }

        for(int i = 1; i<N; i++)
            Collections.sort(sg[i], (a,b) -> {
                return a[0] - b[0];
            });
        
        dfs(A);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[B]).append("\n");
        for(int a : path)
            sb.append(a).append(" ");
        System.out.print(sb);
    }
}