/*
색상환이니까 입력은 0~N-1으로 들어가고
인접한 두색 안되고 서로다른 k개의 색 선택
dp[n][k] = n번째 색까지 봤을때 k개의 색을 선택했을때 경우의 수
dp[i][j] = dp[i-1][j] (i번째 색 안고름)+ dp[i-2][j-1] (i번째 고름)
원형이라 0번째 고려 (idx : N-1)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static final int MOD = 1_000_000_003;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        if(K > N/2){
            System.out.print(0);
            return;
        }

        int[][] dp = new int[N][K+1];
        for(int i = 0; i<N; i++){
            // 아무 색도 안고르는거 1가지
            dp[i][0] = 1;
        }
        dp[1][0] = 1;
        dp[1][1] = 1;

        for(int i = 2; i<N; i++){
            for(int j = 1; j<=K; j++){
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD;
            }
        }

        // 0번째 안고를때 + 고를때
        // 0 -> N-1
        int ans = dp[N-3][K-1] + dp[N-1][K];
        System.out.print(ans%MOD);
    }
}