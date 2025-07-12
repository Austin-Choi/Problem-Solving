import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int n = s.length();

        boolean[][] isPal = new boolean[n][n];
        // 문자열의 모든 구간에 대해서 팰린드롬인지 구하기
        for(int l = 1; l <= n; l++){
            // i-> 시작점, j-> 끝점
            for(int i = 0; i+l-1 < n; i++){
                int j = i+l-1;
                if(s.charAt(i) == s.charAt(j)){
                    if(l <= 2)
                        isPal[i][j] = true;
                    else
                        isPal[i][j] = isPal[i+1][j-1];
                }
            }
        }

        // dp[i] = 0 .. i 까지 최소 몇번 잘라야 하는가 구하기
        int[] dp = new int[n];
        // 초기화 : 최악의 경우 1글자씩 잘라서 총 n번 자르게 됨
        Arrays.fill(dp, n);

        for(int i = 0; i<n; i++){
            for(int j = 0; j<=i; j++){
                if(isPal[j][i]){
                    if(j==0)
                        dp[i] = 1;
                    else
                        dp[i] = Math.min(dp[i], dp[j-1]+1);
                }
            }
        }

        bw.write(String.valueOf(dp[n-1]));
        bw.flush();
        bw.close();
        br.close();
    }
}