import java.util.*;
import java.io.*;



public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int N = A.length;
        int M = B.length;
        // A를 n까지 보고 B를 m까지 봤을 때 최장 공통 부분 수열의 길이
        int[][] dp = new int[N+1][M+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        System.out.print(dp[N][M]);
    }
}