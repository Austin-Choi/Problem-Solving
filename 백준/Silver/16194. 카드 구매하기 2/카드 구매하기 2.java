import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =1; i<=N; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //아무것도 안사는거 최소값은 0
        dp[0] = 0;
        for(int i = 1; i<=N; i++){
            // i-j>=0일때만
            for(int j = 1; j<=i; j++){
                dp[i] = Math.min(dp[i], dp[i-j] + p[j]);
            }
        }
        System.out.println(dp[N]);
    }
}
