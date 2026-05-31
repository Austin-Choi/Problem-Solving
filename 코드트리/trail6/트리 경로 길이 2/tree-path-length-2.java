import java.util.*;
import java.io.*;

/*
depth[u] = 루트부터 u까지의 깊이
dist[u] = 루트부터 u까지의 간선 가중치의 합
dist(a,b) = dist[a]+dist[b] - 2*dist[lca(a,b)]

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,Q;
    static int LOG;
    static ArrayList<int[]>[] g;
    static int[] depth;
    static long[] dist;
    static int[][] parent;

    static void dfs(int u, int p){
        parent[0][u] = p;
        for(int[] v : g[u]){
            int vi = v[0];
            int vw = v[1];
            
            if(vi == p)
                continue;

            depth[vi] = depth[u] + 1;
            dist[vi] = dist[u] + (long) vw;
            dfs(vi, u);
        }
    }

    static void build(){
        for(int i = 1; i<LOG; i++){
            for(int u = 1; u<=N; u++){
                parent[i][u] = parent[i-1][parent[i-1][u]];
            }
        }
    }

    static int getLCA(int a, int b){
        if(depth[b] > depth[a]){
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];
        for(int i = LOG-1; i>=0; i--){
            if((diff & (1<<i))!= 0){
                a = parent[i][a];
            }
        }

        if(a == b)
            return a;
        
        for(int i = LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static long getDist(int a, int b){
        return dist[a] + dist[b] - 2*(dist[getLCA(a,b)]);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        
        while(N >= (1<<LOG))
            LOG++;
        
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        depth = new int[N+1];
        dist = new long[N+1];
        parent = new int[LOG][N+1];
        
        int M = N-1;
        while(M-->0){
            int u = read();
            int v= read();
            int w = read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        dfs(1,0);
        build();

        Q = read();
        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();
            sb.append(getDist(a,b)).append("\n");
        }
        System.out.print(sb);
    }
}