import java.util.*;
import java.io.*;

/*
dp[n][i] = n번 마법진까지 봤을때 반시계 회전 누적량 mod 10 = i일때 최소 회전수
-> 근데 시계방향은 현재 상태만 바꾸지만
반시계방향은 i~N 상태가 1씩 증가해서 전체 영향을 끼침
-> 현재 상태 = 현재 상태 + 앞에서 누적된 반시계 회전 횟수

*/

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] cur = br.readLine().toCharArray();
        char[] dest = br.readLine().toCharArray();

        int[][] dp = new int[N+1][10];
        for(int i= 0; i<=N; i++){
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<10; j++){
                if(dp[i][j] == INF)
                    continue;
                
                // 현재 상태 = 현재 + 누적 반시계 영향
                int now = (cur[i] - '0' + j) % 10;
                int target = dest[i] - '0';

                // 시계방향 -> 현재값에서 목표값으로 감소
                int cw = (now - target + 10) % 10;
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + cw);

                // 반시계방향 -> 현재값에서 목표값으로 증가
                int ccw = (target - now + 10) % 10;
                // 누적반시계방향량 
                int nj = (j+ ccw) % 10;
                dp[i+1][nj] = Math.min(dp[i+1][nj], dp[i][j] + ccw);

            }
        }

        int ans = INF;
        for(int i = 0; i<10; i++){
            ans = Math.min(ans, dp[N][i]);
        }
        System.out.print(ans);
    }
}