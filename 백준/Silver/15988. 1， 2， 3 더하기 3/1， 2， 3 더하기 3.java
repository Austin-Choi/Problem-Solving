import java.io.*;
public class Main {
    static int n;
    static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i<=1000000; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % MOD;
        }
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb);
    }
}
