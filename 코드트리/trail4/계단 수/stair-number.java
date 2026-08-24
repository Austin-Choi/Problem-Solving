import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException{
        int N = read();
        // i번째 자리까지 보고 마지막 선택한 수가 j일때 경우의 수
        long[][] dp = new long[N+1][10];
        for(int i = 1; i<=9; i++){
            dp[1][i] = 1;
        }

        for(int n = 1; n<N; n++){
            // 전 : a, 지금 : b
            for(int a = 0; a<10; a++){
                for(int b = 0; b<10; b++){
                    if(Math.abs(a-b) == 1)
                        dp[n+1][b] = (dp[n+1][b] + dp[n][a]) % MOD;
                }
            }
        }
        
        long ans = 0;
        for(int i = 0; i<10; i++){
            ans = (ans + dp[N][i]) % MOD;
        }
        System.out.print(ans);
    }
}