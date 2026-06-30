import java.util.*;
import java.io.*;

// 구간합 dp
// 구간 길이 순으로 채워야 함 

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] pSum = new int[N+1];
        int[][] dp = new int[N+1][N+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], INF);
        }
        int[] cost = new int[N+1];
        for(int i=1; i<=N; i++){
            cost[i] = read();
            pSum[i] = pSum[i-1] + cost[i];
            dp[i][i] = 0;
        }

        for(int len = 2; len <=N; len++){
            for(int i = 1; i + len - 1<=N; i++){
                int j = i + len - 1;
                for(int k = i; k<j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + pSum[j] - pSum[i-1]);
                }
            }
        }
        System.out.print(dp[1][N]);
    }
}