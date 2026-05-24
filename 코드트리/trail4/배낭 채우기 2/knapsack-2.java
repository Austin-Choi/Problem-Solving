import java.util.*;
import java.io.*;

/*
여러개 쓸수있음
dp[w] = 현재 무게 제한이 m일때 최대 가치합
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int M = read();
        int[][] A = new int[N][2];
        for(int i = 0; i<N; i++){
            A[i] = new int[]{read(), read()};
        }

        int[] dp = new int[M+1];

        for(int i = 0 ; i<N; i++){
            int cw = A[i][0];
            int cv = A[i][1];
            for(int j = cw; j <= M; j++){
                dp[j] = Math.max(dp[j], dp[j-cw]+cv);
            }
        }
        System.out.print(dp[M]);
    }
}