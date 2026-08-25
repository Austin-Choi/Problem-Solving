import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[][] A = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j =0 ; j<M; j++){
                A[i][j] = read();
            }
        }

        long[][] dp = new long[N][M];
        for(int m = 0; m<M; m++){
            dp[0][m] = A[0][m];
        }

        for(int i = 1; i<N; i++){
            for(int prev = 0; prev < M; prev++){
                for(int cur = 0; cur < M; cur++){
                    if(cur == prev)
                        continue;
                    dp[i][cur] = Math.max(dp[i][cur], dp[i-1][prev] + A[i][cur]);
                }
            }
        }

        long ans = 0;
        for(int i = 0; i<M; i++){
            ans = Math.max(ans, dp[N-1][i]);
        }
        System.out.print(ans);
    }
}