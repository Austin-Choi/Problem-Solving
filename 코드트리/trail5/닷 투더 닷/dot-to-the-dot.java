import java.util.*;
import java.io.*;

/*
최솟값을 등장하는 C값중 하나로 고정하고 cc가 C 이상인 간선만 사용하면서 
최단거리 구해서 B+X/C 최소값 구하기?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,X;
    static ArrayList<int[]>[] g;
    static final int INF = 500 * 1_000_000 + 1;

    static int dijkstra(int limit){
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        // curI, curDist
        q.add(new int[]{1, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            if(ci == N)
                break;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nl = n[1];
                int nc = n[2];
                if(nc < limit)
                    continue;

                if(dist[ni] > dist[ci] + nl){
                    dist[ni] = dist[ci] + nl;
                    q.add(new int[]{ni, dist[ni]});
                }
            }
        }
        if(dist[N] == INF)
            return INF;
        return dist[N] + X/limit;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        X = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        Set<Integer> ss = new HashSet<>();
        while(M-->0){
            int u = read();
            int v = read();
            int l = read();
            int c = read();
            ss.add(c);
            g[u].add(new int[]{v,l,c});
            g[v].add(new int[]{u,l,c});
        }

        int ans = INF;
        for(int cc : ss){
            ans = Math.min(ans, dijkstra(cc));
        }
        System.out.print(ans);
    }
}