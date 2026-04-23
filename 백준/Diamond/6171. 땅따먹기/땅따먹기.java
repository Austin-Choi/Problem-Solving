import java.io.*;
import java.util.*;

public class Main {
    static class Line {
        long m, b; // y = m*x + b

        Line(long m, long b) {
            this.m = m;
            this.b = b;
        }

        long f(long x) {
            return m * x + b;
        }
    }

    // l1, l2, l3 중 l2가 필요 없는지 판단
    static boolean isBad(Line l1, Line l2, Line l3) {
        return (l3.b - l1.b) * (l1.m - l2.m)
             <= (l2.b - l1.b) * (l1.m - l3.m);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // W
            arr[i][1] = Integer.parseInt(st.nextToken()); // H
        }

        // W 내림차순, H 내림차순
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return b[0] - a[0];
        });

        // 필터링 (H 증가만 남김)
        ArrayList<int[]> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (arr[i][1] > list.get(list.size() - 1)[1]) {
                list.add(arr[i]);
            }
        }

        int M = list.size();
        long[] dp = new long[M];

        Deque<Line> dq = new ArrayDeque<>();

        // 초기값 (j = 0)
        dq.addLast(new Line(list.get(0)[0], 0));

        for (int i = 0; i < M; i++) {
            long x = list.get(i)[1]; // H[i]
            // query
            while (dq.size() >= 2) {
                Line l1 = dq.pollFirst();
                Line l2 = dq.peekFirst();
                if (l1.f(x) <= l2.f(x)) {
                    dq.addFirst(l1);
                    break;
                }
            }

            dp[i] = dq.peekFirst().f(x);

            // 다음 line 추가 (i+1에서 사용됨)
            if (i + 1 < M) {
                Line newLine = new Line(list.get(i + 1)[0], dp[i]);

                while (dq.size() >= 2) {
                    Line l2 = dq.pollLast();
                    Line l1 = dq.peekLast();
                    if (isBad(l1, l2, newLine)) continue;
                    dq.addLast(l2);
                    break;
                }
                dq.addLast(newLine);
            }
        }

        System.out.println(dp[M - 1]);
    }
}