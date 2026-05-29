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

서브트리 기반으로 i,j 순회 줄이기?? 
-> u 서브트리 내에서 실제 선택가능한 노드 수는 서브트리 크기를 절대 넘을 수 없음

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,K;
    static long[] A;
    static ArrayList<Integer>[] g;
    static long[][][] dp;
    static final long INF = -(1<<60);

    static int[] sz;

    static void dfs(int u, int parent){
        sz[u] = 1;

        for(int i = 0; i<2; i++){
            Arrays.fill(dp[u][i], INF);
        }
        dp[u][0][0] = 0;
        dp[u][1][1] = A[u];

        // 현재까지 merge 완료된 u쪽 노드 갯수
        int curSize = 1;

        // 배낭 부분해 merge
        for(int v : g[u]){
            if(v == parent)
                continue;
            dfs(v, u);
            
            long[][] next = new long[2][K+1];
            // 반드시 초기화해줘야 함
            for(int i = 0; i<2; i++){
                Arrays.fill(next[i], INF);
            }

            // 서브트리 내 노드수로 순회 최적화
            for(int i = 0; i<=Math.min(curSize, K); i++){
                for(int j = 0; j<=Math.min(sz[v], K-i); j++){
                    // 현재가 선택 안됬으면 자식은 선택해도 되고 안해도 되는데 그중 큰거 
                    if(dp[u][0][i] != INF){
                        long bestChild = Math.max(dp[v][0][j], dp[v][1][j]);
                        if(bestChild != INF){
                            next[0][i+j] = Math.max(next[0][i+j], dp[u][0][i] + bestChild);
                        }
                    }
                    
                    // 현재가 선택됬으면 자식은 선택하면 안됨.
                    if(dp[u][1][i] != INF){
                        if(dp[v][0][j] != INF){
                            next[1][i+j] = Math.max(next[1][i+j], dp[u][1][i] + dp[v][0][j]);
                        }
                    }
                }
            }

            dp[u] = next; 
            curSize += sz[v];
        }
        sz[u] = curSize;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        sz = new int[N+1];
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

        A = new long[N+1];
        for(int i = 1; i<=N; i++){
            A[i] = read();
        }
        K = read();

        dp = new long[N+1][2][K+1];
        dfs(1, -1);
        long ans = INF;
        for(int i =0; i<2; i++){
            for(long sum : dp[1][i])
                ans = Math.max(ans, sum);
        }
        System.out.print(ans);
    }
}