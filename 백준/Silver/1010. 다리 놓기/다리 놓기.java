/*
M개에서 N개 뽑는데 idx가 증가하는 순서로 뽑아야함
하나 고르면 나머지가 자동으로 정해짐
조합

최대 30이고 조합 구하는거니까
파스칼의 삼각형 이용
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[31][31];
        for(int i = 0; i<=30; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
            for(int j = 1; j<i; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(dp[M][N]).append("\n");
        }
        System.out.print(sb);
    }
}
