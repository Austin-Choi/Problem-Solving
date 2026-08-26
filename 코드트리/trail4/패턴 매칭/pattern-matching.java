import java.util.*;
import java.io.*;

/*
S는 기준문자열
.은 아무문자 하나랑 같음
*은 아무문자 아무 길이와 같음
특정 문자는 특정 문자하나와 같음
S가 패턴에 속하는지 true false

boolean dp[S.len][P.len] = s번째까지 보고 p번째까지 봤을때 모두 같은 문자인지
OR로 경로 합치기
*/

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int N = A.length;
        int M = B.length;
        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][0] = true;
        // B의 j번째 문자가 *일때 그 앞 문자까지 사용하지 않는 경우로 초기화
        for(int j = 2; j<=M; j++){
            if(B[j-1] == '*')
                dp[0][j] = dp[0][j-2];
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                // .이거나 같은 문자일때
                if(A[i-1] == B[j-1] || B[j-1] == '.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                // *일때
                else if(B[j-1] == '*'){
                    // 0개 사용
                    dp[i][j] = dp[i][j-2];
                    // 1개 이상 사용
                    if(A[i-1] == B[j-2] || B[j-2] == '.'){
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }

        System.out.print(dp[N][M] ? "true" : "false");
    }
}