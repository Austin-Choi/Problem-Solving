import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static ArrayList<int[]>[] g;
    static final int INF = 1000 * 10 + 1;

    static int[] dijkstra(){
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[K] = 0;
        q.add(new int[]{K, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nl = n[1];

                if(dist[ni] > dist[ci] + nl){
                    dist[ni] = dist[ci] + nl;
                    q.add(new int[]{ni, dist[ni]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
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

        int[] dist = dijkstra();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            int cur = dist[i];
            if(cur == INF)
                cur = -1;
            sb.append(cur).append("\n");
        }
        System.out.print(sb);
    }
}