import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M, Q;
    static int LOG;
    static ArrayList<Integer>[] g;
    static int[] depth;
    static int[][] parent;

    static void dfs(int u, int p){
        parent[0][u] = p;
        for(int v : g[u]){
            if(v == p)
                continue;
            depth[v] = depth[u] + 1;
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

        // diff = da-db 맞추기
        int diff = depth[a] - depth[b];
        for(int i = LOG-1; i>=0; i--){
            if((diff & (1<<i)) != 0)
                a = parent[i][a];
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


    public static void main(String[] args) throws Exception {
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }
        M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        depth = new int[N+1];
        while(N >= (1<<LOG))
            LOG++;
        parent = new int[LOG][N+1];

        dfs(1,0);
        build();

        Q = read();
        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();
            int c = read();
            int ab = getLCA(a,b);
            sb.append(getLCA(ab, c)).append("\n");
        }
        System.out.print(sb);
    }
}