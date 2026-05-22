import java.util.*;
import java.io.*;

/*
부분수열이니까 순서를 지켜야 하고 각 원소를 한번씩만 써야 함
dp[i][j] = 앞에서 i번째까지 물건을 사용해서 숫자의 합이 j일때 최소 사용 갯수
dp[i][j] = min(dp[i-1][j], dp[i-1][j-A[i-1]] + 1)

최대 사용 갯수는 N, INF = N+1

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[] A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        int[][] dp = new int[N+1][M+2];
        int INF = N+1;

        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for(int i = 1; i<=N; i++){
            int cur = A[i-1];
            for(int j = 0; j<=M; j++){
                // 선택 안함
                dp[i][j] = dp[i-1][j];
                // 선택 함
                if(j >= cur)
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-cur] + 1);               
            }
        }
        System.out.print(dp[N][M] == INF ? -1 : dp[N][M]);
    }
}