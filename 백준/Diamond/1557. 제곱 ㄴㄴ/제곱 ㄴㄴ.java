
/*
포함 배제
여러 조건 동시 만족시 중복 제거용
조건1개 -2개 +3개-4개

8 - 3
15 - 8 + 3

포함배제 함수의 값을 K이상으로 할수 있는 최소 x를 찾기
f(x)
-> 파라메트릭

1이 아닌 제곱수로 나눠어지지 않을때 -> 몫으로 빼야함

일단 X를 sqrt하고 나오는 정수값으로 X 나눴을때 몫
sqrt는 2부터

몫 합산한걸 뺀 건 이제 전체 값이니까
거기서 포함 배제로 제곱 ㄴㄴ수 조건들 다시 처리하기
(뫼비우스 함수)

에라토스테네스의 체 + 뫼비우스
m(n) 전처리로 포함배제의 빼고 더할거 처리를 해야하는데
숫자 인수에 제곱수가 있으면 0
제곱수는 없고 소인수로만 구성된 경우에
그 종류의 갯수가 짝수면 + 1
그 종류의 갯수가 홀수면 - 1
 */

import java.util.*;
import java.io.*;
public class Main {
    static int MAX = 1000000;
    static int[] mu = new int[MAX+1];
    static boolean[] isPrime = new boolean[MAX+1];

    static void preprocessing(){
        Arrays.fill(isPrime, true);
        int[] primes = new int[MAX+1];
        int pIdx = 0;

        mu[0] = 0;
        mu[1] = 1;

        for(int i = 2; i<=MAX; i++){
            if(isPrime[i]){
                primes[pIdx++] = i;
                mu[i] = -1;
            }

            for(int j = 0; j<pIdx && i * primes[j] <= MAX; j++){
                // 발견된 소수 곱한 것들은 소수 아님
                int x = i*primes[j];
                isPrime[x] = false;

                if(i % primes[j] == 0){
                    //제곱 인수 있음
                    mu[x] = 0;
                    break;
                }
                else {
                    // i와 primes[j]가 서로소일때
                    mu[x] = mu[i] * -1;
                }
            }
        }
    }
    static long K;
    static boolean can(long x){
        long maxsqrt = (long) Math.floor(Math.sqrt(x));
        long total = 0;
        for(int i = 1; i<=maxsqrt; i++){
            if(mu[i] == 0)
                continue;
            total += (long) mu[i] * (x / ((long) i * i));
        }
        return total >= K;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Long.parseLong(br.readLine());

        preprocessing();
        
        long l = 0;
        long r = 2L*K;
        while(l<r){
            long mid = (l+r)/2;
            if(can(mid))
                r = mid;
            else
                l = mid + 1;
        }

        System.out.println(l);
    }
}
