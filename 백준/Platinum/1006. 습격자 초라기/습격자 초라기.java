import java.io.*;
import java.util.*;

public class Main {
    static int N, W;
    static int[] zone1, zone2;

    static void recur(int start, int[] a, int[] b, int[] c) {
        for (int i = start; i < N; i++) {
            a[i + 1] = Math.min(b[i] + 1, c[i] + 1);

            if (zone1[i] + zone2[i] <= W)
                a[i + 1] = Math.min(a[i + 1], a[i] + 1);

            if (i > 0 &&
                zone1[i - 1] + zone1[i] <= W &&
                zone2[i - 1] + zone2[i] <= W)
                a[i + 1] = Math.min(a[i + 1], a[i - 1] + 2);

            if (i < N - 1) {
                // b[i+1]
                b[i + 1] = a[i + 1] + 1;
                if (zone1[i + 1] + zone1[i] <= W)
                    b[i + 1] = Math.min(b[i + 1], c[i] + 1);

                // c[i+1]
                c[i + 1] = a[i + 1] + 1;
                if (zone2[i + 1] + zone2[i] <= W)
                    c[i + 1] = Math.min(c[i + 1], b[i] + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            zone1 = new int[N];
            zone2 = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) 
                zone1[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) 
                zone2[i] = Integer.parseInt(st.nextToken());

            int[] a = new int[N + 1];
            int[] b = new int[N + 1];
            int[] c = new int[N + 1];

            a[0] = 0;
            b[0] = 1;
            c[0] = 1;

            recur(0, a, b, c);
            int res = a[N];

            // 위 연결
            if (N > 1 && zone1[0] + zone1[N - 1] <= W) {
                a[1] = 1;
                b[1] = 2;
                c[1] = (zone2[0] + zone2[1] <= W) ? 1 : 2;

                recur(1, a, b, c);
                res = Math.min(res, c[N - 1] + 1);
            }

            // 아래 연결
            if (N > 1 && zone2[0] + zone2[N - 1] <= W) {
                a[1] = 1;
                c[1] = 2;
                b[1] = (zone1[0] + zone1[1] <= W) ? 1 : 2;

                recur(1, a, b, c);
                res = Math.min(res, b[N - 1] + 1);
            }

            // 둘 다 연결
            if (N > 1 &&
                zone1[0] + zone1[N - 1] <= W &&
                zone2[0] + zone2[N - 1] <= W) {

                a[1] = 0;
                b[1] = 1;
                c[1] = 1;

                recur(1, a, b, c);
                res = Math.min(res, a[N - 1] + 2);
            }

            sb.append(res).append("\n");
        }

        System.out.print(sb);
    }
}