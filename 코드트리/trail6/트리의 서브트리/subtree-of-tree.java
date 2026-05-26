import java.util.*;
import java.io.*;

/*
일단 그래프로 입력을 받아서 루트에서 u까지 visited 체킹하고 u에서 멈추고 
visited 유지한채로 u에서 dfs로 서브트리 리프노드면 1 반환하고 이거 더해주는 방식으로 리턴하는 방식으로.. 
구성??
쿼리 Q개라 배열 유지해야함..
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,R,Q;
    static boolean[] v;
    static ArrayList<Integer>[] g;
    static int[] dp;

    // dp 채우기
    // dp[ci] = ci를 루트로 갖는 하위의 정점의 수 + 1 (자기자신)
    static int dfs(int ci){
        int tot = 0;
        for(int n : g[ci]){
            if(v[n])
                continue;
            v[n] = true;
            tot += dfs(n);
        }
        dp[ci] = 1 + tot;
        return dp[ci];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        //root
        R = read();
        // Q개의 쿼리 들어옴 
        // dp[i] = i를 루트로 갖는 서브트리의 정점 수
        Q = read();

        v = new boolean[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dp = new int[N+1];
        v[R] = true;
        int tot = dfs(R);

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            sb.append(dp[read()]).append("\n");
        }
        System.out.print(sb);
    }
}