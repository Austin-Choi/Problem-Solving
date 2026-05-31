import java.util.*;
import java.io.*;

/*
트리의 경로는 유일하긴 한데 쿼라마다 거리를 구하면 터질거임 10만이니까
-> 조회를 O(1)안에 해야함
어떤 점에서 어떤 점으로 간다면 모든 점에 대해서 루트에서부터의 depth를 구하고
depth[a] + depth[b] - depth[lca(a,b)]
전처리 N log N
조회 + Q log N
-> (N+Q) log N

1-2-3-4-5-6
      4-7-8
6,8 -> 5 + 5 - 3

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, Q;
    static int LOG;
    static ArrayList<Integer>[] g;
    static int[] depth;
    // LOG N+1
    static int[][] parent;

    // 루트 1임
    static void dfs(int u, int p){
        parent[0][u] = p;
        for(int v : g[u]){
            if(v == p)
                continue;
            depth[v] = depth[u]+1;
            dfs(v,u);
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

        //a가 긴쪽이니까 
        // a랑 b의 깊이 맞추기
        int diff = depth[a] - depth[b];
        for(int i = LOG-1; i>=0; i--){
            if((diff & (1<<i)) != 0)
                a = parent[i][a];
        }

        if(a == b)
            return a;
        
        //a,b의 2^i칸 점프한 결과가 같을때까지 동시에 binary lifting
        for(int i = LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static int getDist(int a, int b){
        return depth[a] + depth[b] - 2*depth[getLCA(a,b)]+1;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        while(N >= (1<<LOG))
            LOG++;
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
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