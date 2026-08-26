import java.util.*;
import java.io.*;

/*
모든 문자 위치 N에 삽입 (현재는 뒤로 밀려남), 삭제, 그 위치에서 바꾸기
dp[i][j] = A의 앞 i개 문자를 B의 앞 j개 문자로 바꾸는 최소 횟수

Levenstein distance DP
*/

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
        for(int j = 0; j<=M; j++){
            dp[0][j] = j;   
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                // B의 문자를 삽입 vs 삭제
                dp[i][j] = Math.min(dp[i][j-1] + 1, dp[i-1][j] + 1);
                
                // vs 변경
                int cost = 0;
                if(A[i-1] != B[j-1])
                    cost++;

                dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + cost);
            }
        }
        System.out.print(dp[N][M]);
    }
}