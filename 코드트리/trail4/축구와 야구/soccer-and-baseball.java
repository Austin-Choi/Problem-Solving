import java.util.*;
import java.io.*;

// N은 롤링 dp로 처리하고
// dp[i][j] = 축구팀을 i명 뽑고 야구를 j명 뽑았을 때 최대 능력합

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final long INF = Long.MIN_VALUE / 4;

    public static void main(String[] args) throws IOException{
        int N = read();

        long[][] dp = new long[12][10];
        for(int i = 0; i < 12; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int i = 0; i<N; i++){
            int ss = read();
            int bb = read();

            for(int a = 11; a>=0; a--){
                for(int b = 9; b>=0; b--){
                    if(dp[a][b] == INF)
                        continue;
                    if(a < 11)
                        dp[a+1][b] = Math.max(dp[a+1][b], dp[a][b] + ss);
                    if(b < 9)
                        dp[a][b+1] = Math.max(dp[a][b+1], dp[a][b] + bb);
                }
            }
        }
        System.out.print(dp[11][9]);
    }
}