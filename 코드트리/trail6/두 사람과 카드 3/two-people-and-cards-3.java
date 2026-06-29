import java.util.*;
import java.io.*;

/*
dp[pos][i][j][m] = pos까지 카드를 봤을 때 각 마지막 카드가 i,j일때 m만큼 스킵했을 때 최소합
최대 M개의 카드를 버릴 수 있음
1) 왼쪽이 가져가기 2) 오른쪽이 가져가기 3) 스킵하기
-> 스킵하면 pos+1, i, j, m+1 이 됨
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] card;
    static final long INF = Long.MAX_VALUE/4;

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
        
        // 롤링 배열 기법
        long[][][][] dp = new long[2][N+1][N+1][M+1];
        for (int t = 0; t < 2; t++)
            for (int i = 0; i <= N; i++)
                for (int j = 0; j <= N; j++)
                    Arrays.fill(dp[t][i][j], INF);
        dp[0][0][0][0] = 0;

        for (int p = 0; p < N; p++) {
            int cur = p & 1;
            int nxt = cur ^ 1;

            // 다음 층 초기화
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    Arrays.fill(dp[nxt][i][j], INF);
                }
            }

            int cardIdx = p + 1;

            for (int i = 0; i <= p; i++) {
                for (int j = 0; j <= p; j++) {
                    for (int m = 0; m <= M; m++) {
                        if (dp[cur][i][j][m] == INF)
                            continue;
                        long now = dp[cur][i][j][m];
                        // B가 가져감
                        dp[nxt][i][cardIdx][m] = Math.min(dp[nxt][i][cardIdx][m], now + getDiff(j, cardIdx));
                        // A가 가져감
                        dp[nxt][cardIdx][j][m] = Math.min(dp[nxt][cardIdx][j][m], now + getDiff(i, cardIdx));
                        // 버림
                        if (m < M) 
                            dp[nxt][i][j][m+1] = Math.min(dp[nxt][i][j][m+1], now);
                    }
                }
            }
        }

        int last = N & 1;
        long ans = INF;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int m = 0; m <= M; m++) {
                    ans = Math.min(ans, dp[last][i][j][m]);
                }
            }
        }
        System.out.print(ans);
    }
}