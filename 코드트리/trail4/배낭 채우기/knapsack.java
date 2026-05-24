import java.util.*;
import java.io.*;

/*
하나씩만 골라야함
dp[w] = 무게가 w일때 가능한 최대의 value 합 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        int N = read();
        int M = read();
        int[][] A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i][0] = read();
            A[i][1] = read();
        }
        
        int[] dp = new int[M+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        int ans = 0;
        for(int i = 0; i<N; i++){
            int cw = A[i][0];
            int cv = A[i][1];
            for(int j = M; j>=cw; j--){
                if(dp[j-cw] != INF){
                    dp[j] = Math.max(dp[j], dp[j-cw]+cv);
                    ans = Math.max(ans, dp[j]);
                }
            }
        }
        System.out.print(ans);
    }
}