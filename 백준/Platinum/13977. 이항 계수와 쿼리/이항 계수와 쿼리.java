/*
필요 :
조합경우의수
팩토리얼(구간곱)
곱셈역원(페르마의 소정리)
빠른거듭제곱(분할정복)

C(n,k) 한번만 돌리는 경우에는 top-down 방식이었지만
이번 꺼는 여러 쿼리를 처리해야해서
시간을 많이 먹는 반복되는 작업을 전처리해주고
bottom-up 방식으로 진행해야한다.
Factorial과 곱셈역원을 채워준다.

곱셈역원을 채울때는 뒤에서부터 계산해야함
n! = n * (n-1)!

양변의 역원을 취하면
n! ^ -1 = (n * (n-1)!) ^ -1 mod MOD

모듈러에서 곱셈역원 성질 -> (a*b) ^ -1 = a ^ -1 * b ^ -1
n! ^ -1 = n ^ -1 * (n-1)!) ^ -1 mod MOD

양변에 n 곱하면
n * n! ^ -1 = (n-1)! ^ -1 mod MOD

점화식으로 나타내면
invFac[n-1] = invFac[n] * n mod MOD

그리고 fac이랑 invFac 활용해서 파스칼 삼각형 값 구하기
n! / k! (n-k)!
-> fac[n] * invFac[k] mod MOD * invFac[n-k] mod MOD
 */

import java.util.*;
import java.io.*;
public class Main {
    static final long MOD = 1_000_000_007;
    static final int MAX = 4_000_000;
    static long[] fac = new long[MAX+1];
    static long[] invFac = new long[MAX+1];

    static long fastExp(long base, long exp){
        long rst = 1;
        base %= MOD;
        while(exp > 0){
            if(exp % 2 == 1)
                rst = (rst * base)  % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return rst;
    }

    static void init(){
        fac[0] = 1;
        for(int i = 1; i<=MAX; i++){
            fac[i] = (fac[i-1]*i) % MOD;
        }

        invFac[MAX] = fastExp(fac[MAX], MOD-2);
        for(int i = MAX - 1; i>=0; i--){
            invFac[i] = (invFac[i+1] * (i+1)) % MOD;
        }
    }

    static long paskal(int n, int k){
        if(k==0 || n==k)
            return 1;
        return fac[n] * invFac[k] % MOD * invFac[n-k] % MOD;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        init();
        for(int i =0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(paskal(n,k)));
            bw.newLine();
        }
        bw.flush();
    }
}
