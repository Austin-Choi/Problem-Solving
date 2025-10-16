/*
모듈러 연산은 나눗셈 빼고 분배법칙 가능
a,bc = a * 10^2 + b * 10^1 + c * 10 ^ 0 이니까
abc mod K = a mod K * 10^2 mod K + b mod K * 10^1 mod K + c mod K * 10 ^ 0 mod K 가 무조건 성립..

N개의 수의 길이중 최대 길이를 구해서 10의 제곱을 K로 나눈 배열 만들고
입력받으면서 자릿수별로 K 나머지 구해두고 위의 수식으로 표현

새로 만든 수 mod 값 = (기존 mod 값 * powten (새로 붙일수 자릿수) + 새로 붙일 수 mod) mod K

순열 경우의수 비트마스크로 나타내서 DP 돌리기?
dp[mask][r]
= N개의 문자열 중 어떤걸 썻는지 비트로 표현했을 때(mask)
나머지가 r이 되는 경우의 수
*/
import java.io.*;
public class Main {
    static int N,K;
    static String[] input;
    static int[] powten;
    static int[] mod;

    static long gcd(long a, long b){
        while(b!=0){
            long t = a%b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new String[N];
        mod = new int[N];

        int maxLen = 0;
        for(int i = 0; i<N; i++){
            input[i] = br.readLine();
            maxLen = Math.max(maxLen, input[i].length());
        }
        K = Integer.parseInt(br.readLine());

        // 10 거듭제곱용 나머지 계산
        powten = new int[maxLen+1];
        powten[0] = 1 % K;
        for(int i = 1; i<=maxLen; i++){
            powten[i] = (powten[i-1]*10)%K;
        }

        // 각 자릿수 나머지 계산
        for(int i = 0; i<N; i++){
            String s = input[i];
            int m = 0;
            for(char c : s.toCharArray()){
                m = (m * 10 + (c - '0')) % K;
            }
            mod[i] = m;
        }

        long[][] dp = new long[1<<N][K];
        // 아무것도 안씀 1가지로 시작
        dp[0][0] = 1;

        for(int m = 0; m<(1<<N); m++){
            for(int r = 0; r<K; r++){
                if(dp[m][r] == 0)
                    continue;

                for(int i = 0; i<N; i++){
                    // 이미 존재함
                    if((m & (1<<i)) != 0)
                        continue;

                    int nm = m | (1<<i);
                    int l = input[i].length();

                    int nr = (int) (((long)r * powten[l] + mod[i]) % K);

                    dp[nm][nr] += dp[m][r];
                }
            }
        }

        long p = dp[(1<<N)-1][0];
        long q = 1;
        for(int i = 2; i<=N; i++)
            q *= i;

        long g = gcd(p,q);
        System.out.println(p/g + "/" + q/g);
    }
}