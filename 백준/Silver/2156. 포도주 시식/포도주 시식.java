/*
dp n,2 = n자리가 마지막으로 뽑앗때 연속으로 j개를 마셧을때 최대 와인 합
0 = dp[i-1] 0,1,2 셋중 최대값
-> 지금꺼 안마심 이전상태 아무거나 전이 ㄱㄴ
1 = dp[i-1][0] + a[i]
-> 지금꺼 마심, 이번께 1번째잔이 되려면 전 잔은 안마심
2 = dp[i-1][1] + a[i]
-> 지금꺼 마심, 이번께 2번째잔이 되려면 전잔마심
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for(int i = 0; i<n; i++){
            a[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n][3];
        dp[0][1] = a[0];
        for(int i = 1; i<n; i++){
            dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
            dp[i][1] = dp[i-1][0] + a[i];
            dp[i][2] = dp[i-1][1] + a[i];
        }
        int ans = 0;
        for(int i= 0; i<3; i++){
            ans = Math.max(ans, dp[n-1][i]);
        }
        System.out.print(ans);
    }
}