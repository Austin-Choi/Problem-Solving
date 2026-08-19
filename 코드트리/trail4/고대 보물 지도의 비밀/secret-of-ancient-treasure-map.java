import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final long INF = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        int N = read();
        int K = read();
        int[] map = new int[N];
        for(int i = 0; i<N; i++)
            map[i] = read();
        
        // dp[i][k] = i번째 원소를 반드시 포함하고,
        // 연속된 정수를 선택할 때 음수가 k개일 때 최대 연속합
        long[][] dp = new long[N][K+1];
        for(int i = 0; i<N; i++)
            Arrays.fill(dp[i], INF);
        
        long ans = map[0];
        for(int i = 1; i<N; i++){
            for(int k= 0; k<=K; k++){
                if(map[i] < 0){
                    // 이전 음수 들 + 현재 값
                    if(k > 0 && dp[i-1][k-1] != INF){
                        dp[i][k] = Math.max(dp[i][k], dp[i-1][k-1] + map[i]);
                    }
                    
                    // 현재 원소 하나만 선택
                    if(k == 0)
                        dp[i][k] = Math.max(dp[i][k], (long) map[i]);
                }
                else{
                    if(dp[i-1][k] != INF){
                        dp[i][k] = Math.max(dp[i][k], dp[i-1][k] + map[i]);
                    }

                    if(k == 0)
                        dp[i][k] = Math.max(dp[i][k], (long) map[i]);
                }

                ans = Math.max(ans, dp[i][k]);
            }
        }
        System.out.print(ans);
    }
}