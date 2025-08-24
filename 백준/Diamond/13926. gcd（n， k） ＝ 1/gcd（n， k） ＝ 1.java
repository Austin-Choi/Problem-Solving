import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static Random rnd = new Random();
    // 오일러 피 함수 구현
    static long phi(long n) {
        if (n == 1) return 1;
        Map<Long, Integer> primeCount = new HashMap<>();
        factor(n, primeCount);
        long result = n;
        for (long p : primeCount.keySet()) {
            result -= result / p;   // result *= (1 - 1/p) 안전하게 계산
        }
        return result;
    }

    // 밀러 라빈으로 n이 소수인지 검사
    // 합성수라면 폴라드-로 로 약수 하나 찾아서 재귀적으로 호출
    // n을 소수의 곱으로 완전분해 가능
    static void factor(long n, Map<Long, Integer> cnt) {
        if (n == 1) return;
        if (isPrime(n)) {
            cnt.merge(n, 1, Integer::sum);
            return;
        }
        long d = pollardRho(n);
        factor(d, cnt);
        factor(n / d, cnt);
    }

    // 밀러-라빈 사용해서 소수 판별
    static boolean isPrime(long n) {
        if (n < 2) return false;
        // 소수 작은것들 빠르게 체크하기 위해 전처리
        int[] small = {2,3,5,7,11,13,17,19,23,29,31,37};
        for (int p : small) {
            if (n == p)
                return true;
            if (n % p == 0)
                return n == p;
        }
        long d = n - 1;
        int s = Long.numberOfTrailingZeros(d);
        d >>= s;

        long[] bases = {2, 3, 5, 7, 11, 13, 17}; // 64-bit 결정론적 커버
        BigInteger N = BigInteger.valueOf(n);
        BigInteger D = BigInteger.valueOf(d);

        for (long a : bases) {
            if (a % n == 0)
                continue;
            BigInteger A = BigInteger.valueOf(a);
            BigInteger x = A.modPow(D, N);
            if (x.equals(BigInteger.ONE) || x.equals(N.subtract(BigInteger.ONE)))
                continue;

            boolean witness = true;
            for (int r = 1; r < s; r++) {
                x = x.multiply(x).mod(N);
                if (x.equals(N.subtract(BigInteger.ONE))) {
                    witness = false;
                    break;
                }
            }
            if (witness)
                return false;
        }
        return true;
    }

    // BigInteger클래스로 폴라드-로 구현
    static long pollardRho(long n) {
        if ((n & 1L) == 0) return 2;
        BigInteger N = BigInteger.valueOf(n);

        while (true) {
            // [1, n-1]
            long cLong = 1 + (Math.abs(rnd.nextLong()) % (n - 1));
            // [2, n-2]
            long xLong = 2 + (Math.abs(rnd.nextLong()) % (n - 3));
            BigInteger c = BigInteger.valueOf(cLong);
            BigInteger x = BigInteger.valueOf(xLong);
            BigInteger y = x;
            BigInteger d = BigInteger.ONE;

            // f(z) = z^2 + c mod N
            while (d.equals(BigInteger.ONE)) {
                x = x.multiply(x).add(c).mod(N);
                y = y.multiply(y).add(c).mod(N);
                y = y.multiply(y).add(c).mod(N);
                BigInteger diff = x.subtract(y).abs();
                d = diff.gcd(N);
            }
            if (d.equals(N)) {
                // 실패함 새 상수로 다시
                continue;
            }
            long factor = d.longValue();
            if (factor > 1 && factor < n)
                return factor;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.print(phi(n));
    }
}