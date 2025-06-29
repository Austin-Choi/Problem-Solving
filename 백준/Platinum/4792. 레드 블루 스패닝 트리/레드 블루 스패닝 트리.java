import java.io.*;
import java.util.*;

public class Main {
    private static int[] R;
    private static int[] B;

    private static void init(int N) {
        R = new int[N+1];
        B = new int[N+1];
        for (int i = 1; i <= N; i++) {
            R[i] = i;
            B[i] = i;
        }
    }

    private static int findR(int x) {
        if (R[x] == x) return x;
        return R[x] = findR(R[x]);
    }

    static int findB(int x) {
        if (B[x] == x) return x;
        return B[x] = findB(B[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0 && K == 0)
                break;

            init(N);

            int minBlue = 0;
            int maxBlue = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                
                char c = st.nextToken().charAt(0);
                int f = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                if (c == 'R') {
                    int rf = findR(f);
                    int rt = findR(t);
                    if (rf != rt) {
                        R[rt] = rf;
                        minBlue++;
                    }
                } else {
                    int bf = findB(f);
                    int bt = findB(t);
                    if (bf != bt) {
                        B[bt] = bf;
                        maxBlue++;
                    }
                }
            }

            if ((N - 1 - minBlue) <= K && K <= maxBlue) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}