import java.util.*;
import java.io.*;

/*
0 1 1 2 3 5 8 13 21

0 // 1 0
1 // 0 1
2 // 1 1
3 // 1 2
4 // 2 3
5 // 3 5
6 // 5 8
 */

public class Main {
    private static int[][] dp = new int[41][2];
    private static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for(int i = 2; i<41; i++){
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append(dp[n][0])
                    .append(" ")
                    .append(dp[n][1])
                    .append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
