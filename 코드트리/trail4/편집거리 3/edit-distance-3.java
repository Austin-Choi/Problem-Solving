import java.util.*;
import java.io.*;

// dp[i][j] = A의 앞 i개를 B의 앞 j개로 바꾸는 최소 횟수

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int N = A.length;
        int M = B.length;
        int[][] dp = new int[N+1][M+1];
        for(int i = 0; i<=N; i++){
            dp[i][0] = i;
        }
        for(int j =0; j<=M; j++){
            dp[0][j] = j;
        }

        for(int i =1 ; i<=N; i++){
            for(int j =1; j<=M; j++){
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }
                // A 문자 삭제 vs B 문자 A에 추가
                else
                    dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1);
            }
        }
        System.out.print(dp[N][M]);
    }
}