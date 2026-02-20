/*
D -> 완전 제곱수, n이하 서로 다른 자연수들의 곱, 최대값
가장 크게 만드려면 1*2*3..*n
완전제곱수 -> 모든 소인수의 지수가 짝수임
소인수중 홀수가 있으면 하나 제거해서 짝수로 만듬

parity가 홀수인 소수들의 곱만 알면 됨
 */
import java.util.*;
import java.io.*;
public class Main {
    static final int MAX = 10_000_000;
    static final long MOD = 1_000_000_007L;

    // 어떤 수를 나누는 가장 작은 소수
    static int[] spf = new int[MAX + 1];
    // true = 현재까지 홀수 지수
    static boolean[] parity = new boolean[MAX + 1];
    static long[] ans = new long[MAX+1];
    static void sieve() {
        for (int i = 2; i <= MAX; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= MAX; j += i) {
                    if (spf[j] == 0)
                        spf[j] = i;
                }
            }
        }
    }

    //페르마 소정리
    static long modInverse(long a) {
        return pow(a, MOD - 2);
    }

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> input = new ArrayList<>();
        sieve();
        int maxN = 0;

        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0)
                break;
            input.add(n);
            maxN = Math.max(maxN, n);
        }

        long fact = 1;
        long oddP = 1;

        for(int i = 1; i<=maxN; i++){
            fact = (fact*i) % MOD;

            int x = i;
            while(x>1){
                int p = spf[x];

                if(!parity[p]){
                    parity[p] = true;
                    oddP = (oddP * p) % MOD;
                }
                else{
                    parity[p] = false;
                    oddP = (oddP * modInverse(p)) % MOD;
                }
                x/=p;
            }
            ans[i] = (fact * modInverse(oddP)) % MOD;
        }

        for(int n : input)
            sb.append(ans[n]).append("\n");
        System.out.print(sb);
    }
}
