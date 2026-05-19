import java.util.*;
import java.io.*;

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
        if(N >= 2)
            dp[2] = 1;
        if(N >= 3)
            dp[3] = 1;
        for(int i = 4; i<=N; i++){
            dp[i] = (dp[i-2] + dp[i-3]) % MOD;
        }
        System.out.print(dp[N]);
    }
}