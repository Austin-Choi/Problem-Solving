import java.util.*;
import java.io.*;

/*
부모가 물건이 있었으면 다음건 없음, 부모가 물건이 있었으면 다음건 있음
dp[i][j] = i노드에 j=0 물건이 없음, j=1 물건이 있음 상태일 때 i를 부모로 갖는 서브트리 전체의 최소 물건의 갯수
0이면 반드시 자식 선택해야하고
1이면 둘중에 작은거
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static int N;
    static ArrayList<Integer>[] g;
    static int[][] dp;
    static boolean[] visited;

    static void dfs(int ci){
        visited[ci] = true;
        dp[ci][1] = 1;

        for(int n : g[ci]){
            if(visited[n])
                continue;
            dfs(n);
            dp[ci][0] += dp[n][1];
            dp[ci][1] += Math.min(dp[n][0], dp[n][1]);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        g = new ArrayList[N+1];
        dp = new int[N+1][2];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        for(int i = 1; i<N; i++){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }
        visited = new boolean[N+1];
        dfs(1);
        System.out.print(Math.min(dp[1][0], dp[1][1]));
    }
}