import java.util.*;
import java.io.*;

/*
카드 입력순으로 보면서 왼쪽에 줄지 오른쪽에 줄지
dp[i][j] = max(i,j) 까지 모두 배정했을때 왼쪽에 i까지 배정하고 오른쪽에 j까지 배정했을 때 
두 사람의 접수의 최소 합

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] card;
    static final long INF = Long.MAX_VALUE/2;

    static long getDiff(int u, int v){
        if(u == 0 || v == 0)
            return 0;
        return (long) (Math.abs(card[u] - card[v]));
    }

    public static void main(String[] args) throws IOException{
        N = read();
        card = new int[N+1];
        for(int i = 1; i<=N; i++){
            card[i] = read();
        }

        // 아무카드도 안받은 상태 있어야함
        long[][] dp = new long[N+1][N+1];
        for(int i= 0; i<=N; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        // 0번은 아무것도 안 받은 상태를 나타내므로 [1,N]
        for(int i= 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                if(dp[i][j] == INF)
                    continue;
                int k = Math.max(i,j);
                if(k == N)
                    continue;
                dp[i][k+1] = Math.min(dp[i][k+1], dp[i][j] + getDiff(j,k+1));
                dp[k+1][j] = Math.min(dp[k+1][j], dp[i][j] + getDiff(i,k+1));
            }
        }

        long ans = INF;
        for(int i = 0; i<N; i++){
            ans = Math.min(ans, dp[i][N]);
        }
        for(int i = 0; i<N; i++){
            ans = Math.min(ans, dp[N][i]);
        }
        System.out.print(ans);
    }
}