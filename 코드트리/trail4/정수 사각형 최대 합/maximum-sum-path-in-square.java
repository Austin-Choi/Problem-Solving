import java.util.*;
import java.io.*;

/*
dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + in[i][j]

dp[0][0] = in[0][0];
가로 세로 첫번째 줄은 방향이 한가지라 따로 먼저 처리해줌
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] in = new int[N][N];
        long[][] dp = new long[N+1][N+1];

        for(int i =0; i<N; i++){
            for(int j = 0; j<N; j++){
                in[i][j] = read();
            }
        }

        dp[0][0] = in[0][0];
        for(int j = 1; j<N; j++){
            dp[0][j] = dp[0][j-1] + in[0][j];
        }

        for(int i = 1; i<N; i++){
            dp[i][0] = dp[i-1][0] + in[i][0];
        }

        for(int i =1; i<N; i++){
            for(int j = 1; j<N; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + in[i][j];
            }
        }

        System.out.print(dp[N-1][N-1]);
    }
}