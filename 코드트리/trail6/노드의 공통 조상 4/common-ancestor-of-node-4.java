import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,Q;
    static int LOG = 0;
    static ArrayList<Integer>[] g;
    static int[] depth;
    static int[][] parent;

    static void dfs(int u, int p){
        parent[0][u] = p;
        for(int v : g[u]){
            if(v == p)
                continue;
            depth[v] = depth[u] + 1;
            dfs(v,u);
        }
    }

    static void build(){
        for(int i = 1; i<LOG; i++){
            for(int u=1; u<=N; u++){
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

        // depth 같게 맞추기
        int diff = depth[a] - depth[b];
        for(int i = LOG-1; i>=0; i--){
            if((diff & (1<<i)) != 0)
                a = parent[i][a];
        }

        if(a == b)
            return a;

        // parent[i][a] == parent[i][b] 될때까지 올리기
        for(int i = LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }


    public static void main(String[] args) throws IOException{
        N = read();
        while(N >= (1<<LOG))
            LOG++;
        g = new ArrayList[N+1];
        for(int i = 1;i<=N; i++)
            g[i] = new ArrayList<>();
        depth = new int[N+1];
        parent = new int[LOG][N+1];

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1, 0);
        build();
        Q = read();
        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a  = read();
            int b = read();
            sb.append(getLCA(a,b)).append("\n");
        }
        System.out.print(sb);
    }
}