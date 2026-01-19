// 한번에 그래프처럼 if ||로 묶어서 제약조건 처리하지 말기
// -> dp에서 아래 오른쪽 각각 방향은 각각의 전이라 따로 처리해야함
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] board;
    static long[][] dp;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new long[N][N];
        dp[0][0] = 1;
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(dp[i][j] == 0)
                    continue;
                if(i == N-1 && j == N-1)
                    continue;

                int c = board[i][j];
                if(i+c < N)
                    dp[i+c][j] += dp[i][j];
                if(j+c < N)
                    dp[i][j+c] += dp[i][j];
            }
        }
        System.out.print(dp[N-1][N-1]);
    }
}
