import java.io.*;
import java.util.*;

public class Main {

    static int N, K, M;
    static int BLOCK;

    static long[][] multiply(long[][] A, long[][] B) {

        long[][] C = new long[N][BLOCK];

        for (int i = 0; i < N; i++) {

            for (int b = 0; b < BLOCK; b++) {

                long mask = A[i][b];

                while (mask != 0) {

                    long lsb = mask & -mask;
                    int k = (b << 6) + Long.numberOfTrailingZeros(lsb);

                    for (int bb = 0; bb < BLOCK; bb++)
                        C[i][bb] |= B[k][bb];

                    mask ^= lsb;
                }
            }
        }

        return C;
    }

    static void set(long[] arr, int idx) {
        arr[idx >> 6] |= (1L << (idx & 63));
    }

    static boolean get(long[] arr, int idx) {
        return (arr[idx >> 6] & (1L << (idx & 63))) != 0;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        BLOCK = (N + 63) >> 6;

        long[][] base = new long[N][BLOCK];
        long[][] result = new long[N][BLOCK];

        // base 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            set(base[i], u);
            set(base[i], v);
        }

        // result = identity
        for (int i = 0; i < N; i++)
            set(result[i], i);

        // fast exponentiation
        int step = K;

        while (step > 0) {

            if ((step & 1) == 1)
                result = multiply(result, base);

            base = multiply(base, base);
            step >>= 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            if (get(result[u], v))
                sb.append("death\n");
            else
                sb.append("life\n");
        }

        System.out.print(sb);
    }
}