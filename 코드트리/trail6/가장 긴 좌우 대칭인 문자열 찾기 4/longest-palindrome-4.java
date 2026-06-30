import java.util.*;
import java.io.*;

/*
가장 긴 연속한 팰린드롬 찾기
boolean으로 dp[i][j] = [i,j] 구간에서 팰린드롬인지
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int N = s.length;

        // [i,j] 가 팰린드롬인가
        boolean[][] dp = new boolean[N][N];
        for(int i = 0; i<N; i++)
            dp[i][i] = true;

        int si = 0;
        int max = 1;
        for(int len = 2; len <=N; len++){
            for(int i = 0; i+len-1<N; i++){
                int j = i + len -1;
                if(s[i] == s[j]){
                    if(len == 2)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i+1][j-1];
                }
                else
                    continue;
                    
                if(dp[i][j] && len > max){
                    max = len;
                }
            }
        }
        System.out.print(max);
    }
}