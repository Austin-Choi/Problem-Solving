// 문자열이 2개니까 배열도 2개 써야함
// 2차원배열로 나타내면
// 두 문자가 같을 때 [i-1][j-1] 값의 + 1 한게 dp[i][j]값
// 같지 않을때는 바로 전 행과 전 열 값의 최대값

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] str1 = br.readLine().split("");
        String[] str2 = br.readLine().split("");

        int len1 = str1.length;
        int len2 = str2.length;
        int[][] dp = new int[len1+1][len2+1];

        for(int i = 1; i<=len1; i++){
            for(int j = 1; j<=len2; j++){
                if(str1[i-1].equals(str2[j-1]))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        bw.write(dp[len1][len2]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}