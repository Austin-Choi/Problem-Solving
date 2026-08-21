import java.util.*;
import java.io.*;

/*
선택한 구간을 찾는게 아니라 뺄 구간을 찾아서 최대화하기?
--
dp[i][j] = i번째 까지 봤을때 j 구간을 선택한 상태
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int INF = 500 * -1000 -1;

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int[] pre = new int[N+1];
        for(int i = 1; i<=N; i++){
            pre[i] = pre[i-1] + A[i-1];
        }

        //A 값 범위 -1000~1000
        int[][] dp = new int[N+1][M+1];
        for(int i= 0; i<=N; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        for(int j = 0; j<=M; j++){
            int best = INF;
            if(j > 0)
                best = dp[0][j-1];

            for (int i = 1; i <= N; i++) {
                // i번째 원소를 선택x
                dp[i][j] = dp[i - 1][j];

                if (j > 0) {
                    if (i >= 2 && dp[i - 2][j - 1] != INF) {
                        best = Math.max(best, dp[i - 2][j - 1] - pre[i - 1]);
                    }

                    if (best != INF) {
                        dp[i][j] = Math.max(dp[i][j], pre[i] + best);
                    }
                }
            }
        }
        System.out.println(dp[N][M]);
    }
}