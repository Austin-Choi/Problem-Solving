import java.util.*;
import java.io.*;

/*
dp[u][c][K] = u를 루트노드로 갖는 서브트리에서 u의 색칠 상태가 c일때 정확히 k개를 색칠하여 얻는 노드값 최대 합
-> 색칠된 노드끼리는 인접하면 안됨
-> 배낭문제처럼 하는건데 이제 자식끼리에서 merge???
for(i)
    for(j)
        next[i+j] = old[i] + child[j]

if(k < K)-> i+j로 이미 제한해서 필요없음

next[u][0][i] = max(next[u][0][i], dp[u][0][i] + max(dp[v][1][j], dp[v][0][j]))
next[u][1][i] = max(next[u][1][i], dp[u][1][i] + dp[v][0][j])

-> next[u] 고정이라서 next는 long[2][k+1]로만 복사
next[0][i] = max(next[0][i], dp[u][0][i] + max(dp[v][1][j], dp[v][0][j]))
next[1][i] = max(next[1][i], dp[u][1][i] + dp[v][0][j])
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, K;
    static long[] A;
    static ArrayList<Integer>[] g;
    static long[][][] dp;
    static final long INF = -(1<<30);

    static void dfs(int ci, int parent){
        for(int i=0; i<=K; i++){
            dp[ci][0][i] = INF;
            dp[ci][1][i] = INF;
        }
        
        dp[ci][0][0] = 0;
        if(K >= 1)
            dp[ci][1][1] = A[ci];

        for(int n : g[ci]){
            if(n == parent)
                continue;
            dfs(n, ci);
            
            // 이번 자식으로 처리된 결과를 같은 자식을 처리하는 도중 또 사용하면 안돼서 역방향 순회랑 같은 역할
            long[][] next = new long[2][K+1];

            for(int i = 0; i<2; i++)
                Arrays.fill(next[i], INF);
            
            //현재 노드 상태
            for(int i = 0; i<=K; i++){
                //자식 노드 상태
                // i+j로 고른 갯수 제한
                for(int j = 0; i+j<=K; j++){
                    // 현재 노드 선택 안함
                    if(dp[ci][0][i] != INF){
                        long best = Math.max(dp[n][0][j], dp[n][1][j]);
                        if(best != INF){
                            next[0][i+j] = Math.max(next[0][i+j], dp[ci][0][i] + best);
                        }
                    }

                    // 현재 노드 선택 함
                    if(dp[ci][1][i] != INF){
                        if(dp[n][0][j] != INF){
                            next[1][i+j] = Math.max(next[1][i+j], dp[ci][1][i] + dp[n][0][j]);
                        }
                    }
                }
            }

            dp[ci] = next;
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        A = new long[N+1];
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

        for(int i =1; i<=N; i++){
            A[i] = read();
        }
        K = read();

        dp = new long[N+1][2][K+1];
        dfs(1, -1);
        
        long ans = INF;
        for(int i = 0; i<2; i++){
            for(long sum : dp[1][i]){
                ans = Math.max(ans, sum);
            }
        }
        System.out.print(ans);
    }
}