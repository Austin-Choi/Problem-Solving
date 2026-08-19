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
        int[] coin = new int[N+1];
        for(int i = 1; i<=N; i++){
            coin[i] = read();
        }

        // dp[i][j] = i층에 도달했고 1계단 오르는 것을 j번 했을 때 얻는 동전의 최대 갯수
        int[][] dp = new int[N+1][4];
        for(int i = 0; i<N; i++)
            Arrays.fill(dp[i], -1);
        
        dp[1][1] = coin[1];

        if(N >= 2){
            dp[2][0] = coin[2];
            dp[2][2] = coin[1] + coin[2];
        }

        
        for(int i = 3; i<=N; i++){
            for(int j = 0; j<4; j++){
                if(dp[i-2][j] != -1){
                    dp[i][j] = Math.max(dp[i][j], dp[i-2][j] + coin[i]);
                }

                if(j >= 1 && dp[i-1][j-1] != -1){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + coin[i]);
                }
            }
        }

        int ans = 0;
        for(int j = 0; j<4; j++){
            ans = Math.max(ans, dp[N][j]);
        }
        System.out.print(ans);
    }
}