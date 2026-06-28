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
        
        long[][][][] dp = new long[N+1][N+1][N+1][M+1];
        for(int i= 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                for(int k = 0; k<=N; k++)
                    Arrays.fill(dp[i][j][k], INF);
            }
        }
        dp[0][0][0][0] = 0;

        for(int p = 0; p<N; p++){
            for(int i = 0; i<=p; i++){
                for(int j = 0; j<=p; j++){
                    for(int m = 0; m<=M; m++){
                        if(dp[p][i][j][m] == INF)
                            continue;
                        int nxt = p+1;
                        dp[nxt][i][nxt][m] = Math.min(dp[nxt][i][nxt][m], dp[p][i][j][m] + getDiff(j,nxt));
                        dp[nxt][nxt][j][m] = Math.min(dp[nxt][nxt][j][m], dp[p][i][j][m] + getDiff(i,nxt));
                        if(m < M)
                            dp[nxt][i][j][m+1] = Math.min(dp[nxt][i][j][m+1], dp[p][i][j][m]);
                    }
                }
            }
        }

        long ans = INF;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                for (int m = 0; m <= M; m++) {
                    ans = Math.min(ans, dp[N][i][j][m]);
                }
            }
        }
        System.out.print(ans);
    }
}