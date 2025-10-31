/*
t p 배열 따로 만들어서
dp [i] = 현재 날짜가 i일때 최대 금액
dp[N+2] 로 잡고 dp[0] 은 상담 아무것도 안했으니까 0
dp 초기값은 0 그대로

전개
dp[idx+1] = Math.max(dp[idx+1], dp[idx]
if(idx+현재t<=N+1) dp[idx+현재t] = Math.max(dp[idx+현재t], dp[idx] + 현재 p)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] times;
    static long[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new int[N+2];
        prices = new long[N+2];
        StringTokenizer st = null;
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            prices[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[N+2];
        long ans = 0;
        for(int i = 1; i<=N; i++){
            dp[i+1] = Math.max(dp[i], dp[i+1]);
            int ct = times[i];
            if(i + ct <= N+1)
                dp[i+ct] = Math.max(dp[i+ct], dp[i]+prices[i]);
            ans = Math.max(ans, dp[i]);
        }
        ans = Math.max(ans, dp[N+1]);
        System.out.println(ans);
    }
}
