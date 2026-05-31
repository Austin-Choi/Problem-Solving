import java.util.*;
import java.io.*;

/*
dist[u] = 루트노드에서 u까지 왔을때 색칠된 정점 갯수
dist(a,b) = dist[a] + dist[b] - 2*dist[lca(a,b)]
  2-6
    5-12
  2-5-11-15
  2-4-9
    4-10
1-2
    7-14
1-3-7-13
  3-8

2 4 6 7 11

6,11 dist[11] = 2, dist[6] =2, dist[2] = 1
10,9 dist[10] = 2, dist[9] =2, dist[4] = 2
-> dist[a]+dist[b]-2*dist[lca(a,b)] + if(lcd(a,b) in ss)
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, Q;
    static int LOG = 0;
    static ArrayList<Integer>[] g;
    static Set<Integer> ss = new HashSet<>();
    static int[] dist;
    static int[] depth;
    static int[][] parent;
    static StringBuilder sb = new StringBuilder();

    static void dfs(int u, int p){
        if(ss.contains(u))
            dist[u] += 1;
        parent[0][u] = p;
        for(int v : g[u]){
            if(v == p)
                continue;
            depth[v] = depth[u] + 1;
            dist[v] = dist[u];
            
            dfs(v, u);
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
            if((diff & (1<<i)) != 0)
                a = parent[i][a];
        }

        if(a==b)
            return a;
        
        for(int i= LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static int getDist(int a, int b){
        int c = getLCA(a,b);
        int nd = 0;
        if(ss.contains(c))
            nd = 1;
        return dist[a] + dist[b] - 2*dist[c] + nd;
    }
    

    public static void main(String[] args) throws IOException{
        N = read();
        while(N >= (1<<LOG))
            LOG++;
        g = new ArrayList[N+1];
        for(int i =1 ; i<=N; i++){
            g[i] = new ArrayList<>();
        }
        dist = new int[N+1];
        depth = new int[N+1];
        parent = new int[LOG][N+1];

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        int k= read();
        while(k-->0)
            ss.add(read());
        
        dfs(1,0);
        build();

        Q = read();
        while(Q-->0){
            int a= read();
            int b = read();
            sb.append(getDist(a,b)).append("\n");
        }
        System.out.print(sb);
    }
}