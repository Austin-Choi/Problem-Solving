import java.util.*;
import java.io.*;

/*
T는 고정 상수니까 따로 차수로 빼주고 
B를 연속으로 세번 받지 않고 T가 3미만인 문자열의 가짓수 세기
->long dp[N][T(4)] = n일까지의 평가 문자열을 만들었을때 T가 세번 미만인 문자열 경우의 수를 1_000_000_009로 나눈 나머지

------------------
N을 굳이 dp 상태로 둘 필요가 없음
long dp[B][T] = B가 연속 몇번이고 T 상태가 저럴때 문자열 경우의 수를 1_000_000_009로 나눈 나머지
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
        long[][] dp = new long[3][3];
        dp[0][0] = 1;

        for(int i = 0; i<N; i++){
            long[][] next = new long[3][3];

            for(int b = 0; b<3; b++){
                for(int t = 0; t<3; t++){
                    long cnt = dp[b][t];

                    // G -> B 연속을 끊음
                    next[0][t] = (next[0][t] + cnt) % MOD; 
                    // B -> t는 그대로고 B가 연속적으로 증가함
                    if(b < 2)
                        next[b+1][t] = (next[b+1][t] + cnt) % MOD;
                    // T -> B 연속을 끊음
                    if(t < 2)
                        next[0][t+1] = (next[0][t+1] + cnt) % MOD; 
                }
            }

            dp = next;
        }

        long sum = 0;
        for(int b = 0; b<3; b++){
            for(int t = 0; t<3; t++){
                sum = (sum + dp[b][t]) % MOD;
            }
        }
        System.out.print(sum);
    }
}