import java.util.*;
import java.io.*;

/*
dp[N] = 정수 N을 1,2,5의 합으로 나타내는 방법의 수
N을 구성하는 거 중에 초기값은 
dp[0] = 1
dp[i] = ((dp[i-1] + dp[i-2]) % MOD + dp[i-5]) % MOD

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException{
        N = read();
        int[] dp = new int[N+1];
        dp[0] = 1;
        for(int i = 1; i<=N; i++){
            if(i >= 5)
                dp[i] = ((dp[i-1] + dp[i-2]) % MOD + dp[i-5]) % MOD;
            else if(i >= 2)
               dp[i] = (dp[i-1] + dp[i-2]) % MOD;
            else
                dp[i] = dp[i-1] % MOD;
        }
        System.out.print(dp[N]);
    }
}