import java.util.*;
import java.io.*;

/*
연속된게 아니고 전체에서 가장 긴 팰린드롬 길이 찾으면 됨
그리고 나서 N-dp[0][N-1]
*/

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int N = s.length;
        int[][] dp = new int[N][N];
        for(int i = 0; i<N; i++)
            dp[i][i] = 1;
        
        for(int len = 2; len <= N; len++){
            for(int i= 0; i+len-1<N; i++){
                int j = i+len-1;
                if(s[i] == s[j]){
                    if(len == 2)
                        dp[i][j] = 2;
                    else
                        dp[i][j] = Math.max(dp[i][j], dp[i+1][j-1]+2);
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        System.out.print(N - dp[0][N-1]);
    }
}