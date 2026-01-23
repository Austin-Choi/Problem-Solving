/*
dp i j = i번째 수까지 고려했을때 j개의 구간을 선택해서 얻을 수 있는 최대 합
1) i번째 선택 x -> += dp i-1, j
2) i번째를 끝으로 하는 구간 선택 -> max(i,j , 구간 시작점-2,j-1 + (sum i - sum k-1)

 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static final int INF = -32768 * 101;
    static int[] in;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N];
        for(int i = 0; i<N; i++){
            in[i] = Integer.parseInt(br.readLine());
        }
        sum = new int[N+1];
        for(int i = 1; i<=N; i++){
            sum[i] = sum[i-1] + in[i-1];
        }

        dp = new int[N+1][M+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int i = 1; i<=N; i++){
            for(int j = 0; j<=M; j++){
                dp[i][j] = dp[i-1][j];

                if(j>=1){
                    // k = 구간 시작점
                    dp[i][j] = Math.max(dp[i][j], dp[0][j-1] + sum[i]);
                    for(int k = 2; k<=i; k++){
                        // j번째 구간 [k~i]로 선택하고 
                        // j-1개의 구간은 k-2에서 끝남
                        // 인접 x
                        dp[i][j] = Math.max(dp[i][j], dp[k-2][j-1]+ sum[i]-sum[k-1]);
                    }
                }
            }
        }
        System.out.print(dp[N][M]);
    }
}
