/*
plum [T] = 0 or 1
dp i,j = i초에 j%2 나무위치에 존재할때 이제까지 받은 자두의 최대값
dp i,j = 그 전까지의 2가지의 최대값 + 현재 위치에 자두 있으면 1 없으면 0
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T,W;
    static int[] plum;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        plum = new int[T+1];
        for(int i = 1; i<=T; i++){
            plum[i] = Integer.parseInt(br.readLine())-1;
        }
        dp = new int[T+1][W+1];
        dp[1][0] = plum[1]==0 ? 1 : 0;
        dp[1][1] = plum[1]==1 ? 1 : 0;

        for(int i = 2; i<=T; i++){
            for(int j = 0; j<=W && j <= i; j++){
                if(j == 0)
                    dp[i][j] = dp[i-1][j] + (plum[i] == 0 ? 1 : 0);
                else
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + (plum[i]==j%2 ? 1 : 0);
            }
        }
        int ans = 0;
        for(int j = 0; j<=W; j++){
            ans = Math.max(ans, dp[T][j]);
        }
        System.out.print(ans);
    }
}
