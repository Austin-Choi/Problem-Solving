import java.util.*;
import java.io.*;

/*
전처리
parent[i][u] = 햔재 노드 u에서 2^i번 위로 올라가면 도착하는 노드 번호
1) LOG 구함 -> while N > (1<<LOG) LOG++
2) parent = new int[LOG][N+1]
3) dfs로 depth 구함, 루트 1번이라고 주어짐 
-> parent[0][N+1] = 바로 위 노드
4) parent[1 ~ LOG-1][N+1] 구하기
-> parent[i][u] = parent[i-1][parent[i-1][u]]

LCA
5) a depth가 크게 a,b 맞춰주고
6) diff = depth[a] - depth[b] 이라하면, diff = [0,depth[a]]이고 i = [LOG-1, 0]일때
예를 들어 diff = 1101(2) 라고 하면
diff & (1<<i) != 0 이면 해당 2진수 자리 1이니까 그때 점프하면 됨 
7) i = [LOG-1, 0] 일때 parent[i][a] != parent[i][b]이면 a=parent[i][a], b=parent[i][b] 
8) return parent[0][a]
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, Q;
    static ArrayList<Integer>[] g;
    static int[] depth;
    // log, N+1
    static int[][] parent;
    static int LOG = 0;

    static void dfs(int u, int p){
        parent[0][u] = p;
        for(int v : g[u]){
            if(v == p)
                continue;
            depth[v] = depth[u]+1;
            dfs(v,u);
        }
    }

    static void buildParent(){
        for(int i = 1; i<LOG; i++){
            for(int u = 1; u <=N; u++){
                parent[i][u] = parent[i-1][parent[i-1][u]];
            }
        }
    }

    static int getLCA(int a, int b){
        if(depth[b] > depth[a]){
            int temp = a;
            a = b;
            b = temp;
        }

        // depth[a] = depth[b] 해주기
        int diff = depth[a] - depth[b];
        for(int i = LOG-1; i>=0; i--){
            if((diff & (1<<i)) != 0)
                a = parent[i][a];
        }

        if(a == b)
            return a;

        // parent[0][a] == parent[0][b] 될때까지 올리기
        for(int i = LOG-1; i>=0; i--){
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    /*
    N>=(1<<LOG) LOG++ -> parent[LOG][N+1] 
    for(int i = LOG-1; i>=0; i--) -> getLCA 안에서 이걸로 고정
    */
    public static void main(String[] args) throws IOException{
        N = read();
        while(N >= (1<<LOG))
            LOG++;

        depth = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        parent = new int[LOG][N+1];
    
        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1,0);
        buildParent();

        Q = read();
        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int a = read();
            int b = read();
            sb.append(getLCA(a,b)).append("\n");
        }

        System.out.print(sb);
    }
}