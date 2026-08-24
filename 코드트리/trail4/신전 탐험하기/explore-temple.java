import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] A = new int[N][3];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read(), read()};
        }

        int[][] dp = new int[N][3];
        dp[0][0] = A[0][0];
        dp[0][1] = A[0][1];
        dp[0][2] = A[0][2];
        
        for(int i = 1; i<N; i++){
            for(int a = 0; a<3; a++){
                for(int b = 0; b<3; b++){
                    if(a == b)
                        continue;
                    dp[i][b] = Math.max(dp[i][b], dp[i-1][a] + A[i][b]);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<3; i++){
            ans = Math.max(ans, dp[N-1][i]);
        }
        System.out.print(ans);
    }
}