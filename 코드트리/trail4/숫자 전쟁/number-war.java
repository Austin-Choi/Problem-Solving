import java.util.*;
import java.io.*;

// 상태 정의
/*
1) A > B
B만 증가시키기 
2) A < B
A 증가시키거나 A,B 증가시키기
3) A == B
A,B 증가시키기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        int[] B = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }
        for(int i = 0; i<N; i++){
            B[i] = read();
        }

        // ai, bi 일때 남우의 최대 점수
        long[][] dp = new long[N + 1][N + 1];

        for(int i = N - 1; i >= 0; i--){
            for(int j = N - 1; j >= 0; j--){
                if(A[i] > B[j]){
                    dp[i][j] = B[j] + dp[i][j + 1];
                }
                else if(A[i] < B[j]){
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
                }
                else{
                    dp[i][j] = dp[i + 1][j + 1];
                }
            }
        }
        System.out.println(dp[0][0]);
    }
}