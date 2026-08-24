import java.util.*;
import java.io.*;



public class Main {
    static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] s = br.readLine().toCharArray();

        // L = 0, R = 1
        int[][] A = new int[N][2];
        for(int i = 0; i<N; i++){
            if(s[i] == 'L')
                A[i][0] = 1;
            else
                A[i][1] = 1;
        }

        // n번째까지 봤고, 총 k번 이동했을 때 수집할 수 있는 최대 수정의 갯수
        int[][][] dp = new int[N][2][K+1];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<2; j++){
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][0][0] = A[0][0];
        //! 각 수정이 떨어지기 직전 움직일수 있는데 첫번째도 가능함
        if(K >= 1){
            dp[0][1][1] = A[0][1];
        }

        for(int i = 1; i<N; i++){
            for(int a= 0; a<2; a++){
                for(int b = 0; b<2; b++){
                    for(int k = 0;k <= K; k++){
                        if(dp[i-1][a][k] == INF)
                            continue;

                        if(a != b){
                            if(k < K){
                                dp[i][b][k+1] = Math.max(dp[i][b][k+1], dp[i-1][a][k] + A[i][b]);
                            }
                        }
                        else{
                            dp[i][b][k] = Math.max(dp[i][b][k], dp[i-1][a][k] + A[i][b]);
                        }
                    }
                }
            }
        }


        int ans = 0;
        for(int i = 0; i<2; i++){
            for(int k = 0; k<=K; k++){
                ans = Math.max(ans, dp[N-1][i][k]);
            }
        }
        System.out.print(ans);
    }
}