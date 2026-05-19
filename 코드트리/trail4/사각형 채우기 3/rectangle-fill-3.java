import java.util.*;
import java.io.*;

/*
ㅡ모양 2개 놓으면 i-2
초기
x   i
x , i
dp1[0] = 1;
dp1[1] = 2;
dp1[2] = 2 * dp1[i-1] + dp[i-2];


-> 1칸 전에서 1*1 2개, 1*2 1개로 각자 2가지 = 2*dp[i-1] 
    + 2칸 전에서 1*2 가로 2개로 1가지 = dp1[i-2]
    + 1칸 전에서 ㄴ 자 모양 2가지 = 2*dp2[i-1]
-> dp1[i] = 2 * dp1[i-1] + dp[i-2] + 2*dp2[i-1];

dp2[0] = 0;
dp2[1] = 1;
-> 1칸전에 꽉찬 상태에서 1*1 하나 붙이면 ㄴ자 
    + ㄴ상태에서 1*2 하나 붙이면 아래가 빈 ㄴ자 됨 (1*2를 붙이긴 했지만 인덱스가 길이 기준이라 1칸 전진이니까 i-1)
-> dp2[i] = dp1[i-1] + dp2[i-1]
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static final long MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException{
        int N = read();
        // i길이까지 꽉 채우는 모양으로 끝나는 경우
        long[] dp1 = new long[N+1];
        // ㄴ모양으로 1*1칸 위아래로 비우는 모양으로 끝나는 경우
        long[] dp2 = new long[N+1];

        dp1[0] = 1;
        dp1[1] = 2;

        dp2[0] = 0;
        dp2[1] = 1;
        for(int i = 2; i<=N; i++){
            dp2[i] = (dp1[i-1] + dp2[i-1]) % MOD;
            // 전체 mod해야
            dp1[i] = ((2*dp1[i-1]) % MOD + dp1[i-2] + (2*dp2[i-1]) % MOD) % MOD;
        }
        System.out.print(dp1[N]);
    }
}