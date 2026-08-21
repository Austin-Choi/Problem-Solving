import java.util.*;
import java.io.*;

/*
다시보기
자릿수 dp
박수를 치지 않는 수의 갯수로 해도 mod 연산 특성상 가능

dp[i][t][j] = i번째 자리까지 결정했을때 tight값(N에 대해 상대적 대소여부, 0,1)와 3으로 나눈 나머지가 j인 경우의 수
3,6,9는 구성할때 안넣음
*/

public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int len = s.length;

        // pos, t, mod 지만 pos는 rolling dp로 제거
        // t=0
        long[][] dp = new long[2][3];
        // 아무 자리도 결정 x
        dp[1][0] = 1;

        for(int i = 0; i<len; i++){
            // rolling dp
            long[][] next = new long[2][3];

            int limit = s[i] - '0';
            for(int t = 0 ; t<=1; t++){
                int maxD = (t==1) ? limit : 9;

                for(int mod = 0; mod < 3; mod++){
                    if(dp[t][mod] == 0)
                        continue;
                    
                    for(int d = 0; d<= maxD; d++){
                        // 박수 안치는 수 세기
                        if(d ==3 || d == 6 || d == 9)
                            continue;
                        int nextT;

                        if(t == 1 && d == limit)
                            nextT = 1;
                        else
                            nextT = 0;
                        
                        int nextM = (mod * 10 + d) % 3;

                        next[nextT][nextM] = (next[nextT][nextM] + dp[t][mod]) % MOD;
                    }
                }
            }
            dp = next;
        }

        // 박수 안치는 수 
        // -> 3,6,9 없고 3의 배수도 아니어야 함
        long no = (dp[0][1] + dp[0][2] + dp[1][1] + dp[1][2]) % MOD;

        // 전체 경우의 수
        long all = 0;
        for(char c : s)
            all = (all * 10 + (c - '0')) % MOD;
        
        long ans = (all - no + MOD) % MOD;
        System.out.print(ans);
    }
}