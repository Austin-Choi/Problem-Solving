import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        char[] c = br.readLine().toCharArray();

        int[][][] dp = new int[a.length+1][b.length+1][c.length+1];

        for(int i = 1; i<=a.length; i++){
            for(int j = 1; j<=b.length; j++){
                for(int k = 1; k<=c.length; k++){
                    if(a[i-1] == b[j-1] && b[j-1] == c[k-1])
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else
                        dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                }
            }
        }
        
        int ans = dp[a.length][b.length][c.length];
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}