import java.util.*;
import java.io.*;

// 처음 들어가는 방향 고정하기

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] A = new int[N][3];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read(), read()};
        }

        int ans = 0;
        for(int s = 0; s<3; s++){
            int[][] dp = new int[N][3];

            for(int i = 0; i<N; i++){
                Arrays.fill(dp[i], INF);
            }

            dp[0][s] = A[0][s];
            
            for(int i = 1; i<N; i++){
                for(int a = 0; a<3; a++){
                    for(int b = 0; b<3; b++){
                        if(a == b)
                            continue;
                        if(dp[i-1][a] == INF)
                            continue;
                        dp[i][b] = Math.max(dp[i][b], dp[i-1][a] + A[i][b]);
                    }
                }
            }

            // 처음에 시작했던 방향과 마지막 방향 같으면 안됨
            for(int e = 0; e<3; e++){
                if(s == e)
                    continue;
                ans = Math.max(ans, dp[N-1][e]);
            }
        }

        System.out.print(ans);
    }
}