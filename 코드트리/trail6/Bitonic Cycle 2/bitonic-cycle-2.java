import java.util.*;
import java.io.*;

/*
Bitonic Tour
정확히 한번만 스킵 가능함
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] pos;
    static final long INF = Long.MAX_VALUE / 2;

    static long getDist(int u, int v){
        int x1 = pos[u][0];
        int y1 = pos[u][1];
        int x2 = pos[v][0];
        int y2 = pos[v][1];

        long a = (long) (x1-x2);
        long b = (long) (y1-y2);
        return a*a + b*b;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        pos = new int[N][2];
        for(int i= 0; i<N; i++)
            pos[i] = new int[]{read(), read()};
        Arrays.sort(pos, (a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        long[][][] dp = new long[N][N][2];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                dp[i][j][0] = INF;
                dp[i][j][1] = INF;
            }
        }
        // 1까지 본거니까 초기 간선 가중치는 dist(0,1)
        // 초기간선도 스킵 가능함
        dp[0][1][0] = getDist(0,1);
        dp[0][1][1] = 0;

        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int m = 0; m<2; m++){
                    long now = dp[i][j][m];
                    if(now == INF)
                        continue;
                    if(i >= j)
                        continue;
                    if(j == N-1)
                        continue;
                    if(m < 1){
                        dp[i][j+1][m+1] = Math.min(dp[i][j+1][m+1], now);
                        dp[j][j+1][m+1] = Math.min(dp[j][j+1][m+1], now);
                    }
                    dp[i][j+1][m] = Math.min(dp[i][j+1][m], now + getDist(j, j+1));
                    dp[j][j+1][m] = Math.min(dp[j][j+1][m], now + getDist(i, j+1));
                } 
            }
        }

        long ans = INF;
        for(int i = 0; i<N; i++){
            for(int m = 0; m<2; m++){
                ans = Math.min(ans, dp[N-1][i][m] + getDist(i, N-1));
                ans = Math.min(ans, dp[i][N-1][m] + getDist(i, N-1));
                if(m == 0){
                    ans = Math.min(ans, dp[N-1][i][m]);
                    ans = Math.min(ans, dp[i][N-1][m]);
                }
            }
        }
        System.out.print(ans);
    }
}