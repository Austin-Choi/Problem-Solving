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
        int[][] board = new int[N][N];
        int[][] dp = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                board[i][j] = read();
            }
        }

        dp[0][0] = board[0][0];
        for(int j = 1; j<N; j++){
            dp[0][j] = Math.max(dp[0][j-1], board[0][j]);
        }
        for(int i = 1; i<N; i++){
            dp[i][0] = Math.max(dp[i-1][0], board[i][0]);
        }

        for(int i = 1; i<N; i++){
            for(int j= 1; j<N; j++){
                dp[i][j] = Math.min(Math.max(dp[i-1][j], board[i][j]), Math.max(dp[i][j-1], board[i][j]));
            }
        }
        System.out.print(dp[N-1][N-1]);
    }
}