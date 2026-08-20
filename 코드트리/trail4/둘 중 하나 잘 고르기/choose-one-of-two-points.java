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
        int[][] card = new int[2*N][2];
        for(int i = 0; i<2*N; i++){
            card[i] = new int[]{read(), read()};
        }

        // dp[i][j] = 빨간색을 i개 뽑고 파란색을 j개 뽑았을 때 뽑힌 정수들의 최대 합
        int[][] dp = new int[N+1][N+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for(int i = 0; i<2*N; i++){
            // b는 조건에 의해 r + b = i 이므로
            // 상대적으로 계산 가능
            for(int r = 0; r<=N; r++){
                int b = i - r;
                if(b < 0 || b > N)
                    continue;
                if(dp[r][b] == -1)
                    continue;
                
                if(r < N){
                    dp[r+1][b] = Math.max(dp[r+1][b], dp[r][b]+ card[i][0]);
                }
                if(b < N){
                    dp[r][b+1] = Math.max(dp[r][b+1], dp[r][b]+ card[i][1]);
                }
            }
        }
        System.out.print(dp[N][N]);
    }
}