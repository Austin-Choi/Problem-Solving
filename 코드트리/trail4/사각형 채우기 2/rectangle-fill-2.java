/*
그 전꺼가 3가지니까 3곱하고 겹치는거 빼주고
겹치는거 4*i-3 해야함

상태를 정리하면
전이 기준은 가로 길이임
dp[i] = i길이까지 경우의 수
1) i-1에서 1*2 세로로 붙이기
2) 
*/
import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final int MOD = 10_007;
    public static void main(String[] args) throws IOException{
        int N = read();
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        if(N > 1)
            dp[2] = 3;
        for(int i= 3; i<=N; i++){
            dp[i] = (dp[i-1] + (2*dp[i-2]) % MOD) % MOD;
        }
        System.out.print(dp[N]);
    }
}