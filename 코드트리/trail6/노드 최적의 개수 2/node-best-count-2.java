import java.util.*;
import java.io.*;

/*
모든 간선의 coverage 형태를 봐야하는거라서 
현재 - 자식 만 봐야 한다.
정점 cover하는 dominating set 문제하고 다른건 
정점으로 하는건 현재를 부모가 커버하게 되면 자식도 뭘 고르든 상관없게 되는데 
간선으로 하는건 현재를 부모가 커버한다 해도 현재-자식은 독립사건이다.

dp[u][2] = 현재 정점 u의 선택상태가 2가지일때 추가로 놓아야 하는 최소 갯수
dfs에서 u가 M에 속하는 정점이면 
dp[u][0] = INF, dp[u][1] = 0
나머지는
dp[u][0] += dp[v][1]
dp[u][1] += min(dp[v][1], dp[v][0])
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<Integer>[] g;
    static Set<Integer> s = new HashSet<>();
    static int[][] dp;
    static final int INF = 100_001;

    static void dfs(int u, int parent){
        // 이미 물건을 놓은 정점에서 리턴해버리면 자식을 안봄
        // 초깃값만 달라지는거
        if(s.contains(u)){
            dp[u][0] = INF;
            dp[u][1] = 0;
        }
        else{
            dp[u][0] = 0;
            dp[u][1] = 1;
        }

        for(int v : g[u]){
            if(v == parent)
                continue;
            dfs(v, u);
            dp[u][0] += dp[v][1];
            dp[u][1] += Math.min(dp[v][1], dp[v][0]);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        M = read();
        int K = N-1;
        while(K-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }
        for(int i=1;i<=M; i++)
            s.add(read());
        
        dp = new int[N+1][2];
        dfs(1, -1);
        System.out.print(Math.min(dp[1][1], dp[1][0]));
    }
}