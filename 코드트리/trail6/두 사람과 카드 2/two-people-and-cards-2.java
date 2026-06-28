import java.util.*;
import java.io.*;

/*
바이토닉 사이클 형태인데 카드 diff 계산하려면 아무것도 없는상태 고려해야하니까
dp[i][j] = max(i,j) 까지 배정했을때 두사람 점수 최소합

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] card;
    static Set<Integer> no = new HashSet<>();
    static final long INF = Long.MAX_VALUE;

    static long getDiff(int u, int v){
        if(u == 0 || v == 0)
            return 0;
        return (long) Math.abs(card[u] - card[v]);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        card = new int[N+1];
        for(int i = 1; i<=N; i++)
            card[i] = read();
        while(M-->0)
            no.add(read());

        long[][] dp = new long[N+1][N+1];
        for(int i =0; i<=N; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){ 
                if(dp[i][j] == INF)
                    continue;
                int k = Math.max(i,j);
                // 새 카드를 줄수없는 상태로 검사해야함
                if(k == N)
                    continue;
                if(!no.contains(k+1))
                    dp[i][k+1] = Math.min(dp[i][k+1], dp[i][j] + getDiff(j, k+1));
                dp[k+1][j] = Math.min(dp[k+1][j], dp[i][j] + getDiff(i, k+1));
            }
        }

        long ans = INF;
        for(int i = 0; i<N; i++){
            ans = Math.min(ans, dp[i][N]);
            ans = Math.min(ans, dp[N][i]);
        }
        System.out.print(ans);
    }
}