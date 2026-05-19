import java.util.*;
import java.io.*;

/*
dp[i] = i길이까지 왔을때 채우는 방법의 수 (1-based)

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
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i<=N; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % MOD;
        }
        System.out.print(dp[N]);
    }
}