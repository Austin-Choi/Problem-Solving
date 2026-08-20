import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int OFFSET = 20;

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[] num = new int[N];
        for(int i = 0; i<N; i++){
            num[i] = read();
        }

        // 총 N개를 처리했을 때 합이 M이 되는 경우의 수
        // M이 -20 ~ 20 이므로 offset 20 붙임
        long[][] dp = new long[N+1][20+1+OFFSET];
        // 합 0 1가지
        dp[0][OFFSET] = 1;

        for(int i = 1; i<=N; i++){
            for(int sum = -20; sum <= 20; sum++){
                long cnt = dp[i-1][sum + OFFSET];
                if(cnt == 0)
                    continue;
                int next = sum + num[i-1];
                if(next >= -20 && next <= 20){
                    dp[i][next + OFFSET] += cnt;
                }

                next = sum - num[i-1];
                if(next >= -20 && next <= 20)
                    dp[i][next+ OFFSET] += cnt;
            }
        }
        System.out.print(dp[N][M+OFFSET]);
    }
}