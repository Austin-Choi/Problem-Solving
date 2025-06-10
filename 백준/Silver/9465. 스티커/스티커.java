import java.util.*;
import java.io.*;

public class Main {
    private static int[][] board;
    private static int[][] dp;
    private static int n;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            n = Integer.parseInt(br.readLine());
            board = new int[2][n];
            dp = new int[2][n];

            for(int i = 0; i<2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = board[0][0];
            dp[1][0] = board[1][0];

            for(int i = 1; i<n; i++){
                dp[0][i] = Math.max(dp[0][i-1], board[0][i] + dp[1][i-1]);
                dp[1][i] = Math.max(dp[1][i-1], board[1][i] + dp[0][i-1]);
            }

            sb.append(Math.max(dp[0][n-1], dp[1][n-1])).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
