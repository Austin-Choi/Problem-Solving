import java.util.*;
import java.io.*;

/*
dp[i] = 현재 i자리까지 봤을 때 인접한 숫자간의 차가 전부 1인 경우의 수
i -> i-1, i+1 가능
bitCount(m) == 10이어야 만족하는것
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException{
        int N = read();
        if(N < 10){
            System.out.print(0);
            return;
        }

        // N번째 자리까지 봤고 현재 집합 상태 m, 마지막으로 고른 숫자가 j인 상태에서 경우의 수
        int[][][] dp = new int[N+1][1<<10][10];
        for(int s = 1; s<10; s++){
            dp[1][1<<s][s] = 1;
        }

        for(int pos = 2; pos<=N; pos++){
            for(int m = 0; m<(1<<10); m++){
                for(int i = 0; i<10; i++){
                    if(i > 0){
                        int nm = m | 1<<(i-1);
                        dp[pos][nm][i-1] = (dp[pos][nm][i-1] + dp[pos-1][m][i]) % MOD;
                    }
                    if(i < 9){
                        int nm = m | 1<<(i+1);
                        dp[pos][nm][i+1] = (dp[pos][nm][i+1] + dp[pos-1][m][i]) % MOD;
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<10; i++)
            ans = (ans + dp[N][(1<<10)-1][i]) % MOD;
        System.out.print(ans);
    }
}