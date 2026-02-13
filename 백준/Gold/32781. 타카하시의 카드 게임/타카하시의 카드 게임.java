/*
뽑은 카드의 부분집합으로 어떻게 나열하든 팰린드롬이어야 함
-> 그럼 뽑힌게 전부 같은 알파벳 종류거나 각자 하나씩 뽑혀야함
크기 2이면서 각자 다른 종류 조합
+
2개 이상 조합 = 2^k - 1 - k
nC2 + sum(2^k - 1 - k - kC2)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static char[] input;

    // a,b,c .. A,B,C
    static int[] cnt = new int[52];
    static final long inv2 = 500000004;
    static long pow(int k){
        long rst = 1;
        long base = 2;

        while(k > 0){
            if(k%2 == 1)
                rst = (rst*base) % MOD;
            base = (base*base) % MOD;
            k /= 2;
        }
        return rst;
    }
    static long c2(long x){
        return x * (x-1) % MOD * inv2 % MOD;
    }
    static final int MOD = (int) 1e9 + 7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
        for(char c : input){
            if(Character.isLowerCase(c))
                cnt[c-'a']++;
            else
                cnt[c-'A'+26]++;
        }
        long ans = c2(N);

        for(int c : cnt){
            if(c == 0)
                continue;

            long t = pow(c);
            t = (t-1-c) % MOD;
            t = (t - c2(c)) % MOD;

            ans = (ans + t) % MOD;
        }
        if(ans < 0)
            ans += MOD;
        System.out.print(ans);
    }
}
