import java.util.*;
import java.io.*;

/*
dp[i] = i길이까지 경우의 수
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int MOD = 10_007;
    public static void main(String[] args) throws IOException{
        int N = read();
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;
        for(int i= 3; i<=N; i++){
            dp[i] = ((3*dp[i-1])%MOD - (4*dp[i-3] % MOD) + MOD) % MOD;
        }
        System.out.print(dp[N]);
    }
}