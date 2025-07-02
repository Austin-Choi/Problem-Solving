import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Point[] board = new Point[N];

        for(int n = 0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[n] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // i부터 j까지 곱하는 최소 비용
        int[][] dp = new int[N][N];
        for(int len = 1; len <N; len++){
            for(int i = 0; i + len < N; i++){
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k<j; k++){
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k+1][j] + board[i].x * board[k].y * board[j].y);
                }
            }
        }

        bw.write(String.valueOf(dp[0][N-1]));
        bw.flush();
        bw.close();
        br.close();
    }
}
