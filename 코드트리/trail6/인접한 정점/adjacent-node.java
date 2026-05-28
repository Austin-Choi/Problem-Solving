import java.util.*;
import java.io.*;

/*
Independent dominating set??
현재 = u,자식 = v

1) u를 선택했고 u의 부모는 선택안함
-> dp[u][0] = A[u] + E dp[v][1]

2) u를 선택하지 않았고 u의 부모를 선택함
-> dp[u][1] = E max(dp[v][0] , dp[v][2])

3) u를 선택하지 않았고 u의 부모도 선택하지 않음
-> v중 적어도 하나는 선택되어야 함
-> dp[u][2] = E (dp[v][0], dp[v][2]) 인데 인접 조건상 적어도 하나는 dp[v][0]을 골라야함
따라서 dp[v][0]-dp[v][2]를 최소화하는 값 구해놓고
한번이라도 [v][0]이 커서 골라졌으면 [u][2] = sum2, 아니면 [u][2] = sum2+best

dp[i][3] = 3가지 i의 선택여부 상태에 따라, 
i를 루트로 갖는 서브트리에서 선택한 정점 적힌 수의 최대합
-> 방향은 자식 -> 부모
정답은 루트는 부모가 없으니까 max(dp[1][0], dp[1][2])
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] A;
    static ArrayList<Integer>[] g;
    static int[][] dp;
    // 그냥 Integer.MIN_VALUE 쓰면 오버플로 발생함..
    static final int INF = 10_000 * 10_000 + 1;

    static void dfs(int ci, int parent){
        dp[ci][0] = A[ci];
        
        int sum1 = 0;
        int sum2 = 0;
        boolean picked = false;
        int best = -INF;
        boolean hasChild = false;

        for(int n : g[ci]){
            if(n == parent)
                continue;
            hasChild = true;
            dfs(n, ci);

            // state 0
            dp[ci][0] += dp[n][1];

            // state 1
            sum1 += Math.max(dp[n][0], dp[n][2]);
            
            // state 2
            if(dp[n][0] >= dp[n][2]){
                picked = true;
                sum2 += dp[n][0];
            }
            else
                sum2 += dp[n][2];

            best = Math.max(best, dp[n][0] - dp[n][2]);
        }

        dp[ci][1] = sum1;

        // 리프노드면 자식중에 아무도 선택할수가 없음
        // 따라서 [2]의 경우에는 최솟값이 됨
        if(!hasChild){
            dp[ci][2] = -INF;
            return;
        }

        if(picked)
            dp[ci][2] = sum2;
        /* 
        적어도 하나는 자식에서 선택해야하는데 max로 고르면 [2]만 선택될 수 있어서
        그나마 손실이 가장 적은 선택된 자식의 값으로 교체하기 
        (dp[n][0] - dp[n][2] 니까 그 당시의 [2] 빠지고 [0] 추가되니 그냥 더하면됨)
        */
        else
            dp[ci][2] = sum2 + best;

    }

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N+1];
        for(int i= 1; i<=N; i++)
            A[i] = read();

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();
        dp = new int[N+1][3];

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1,-1);
        // state 1은 부모를 선택함인데 루트는 부모가 없음
        System.out.print(Math.max(dp[1][0], dp[1][2]));
    }
}