import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] dist = new long[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        int[] S = new int[M];
        int[] E = new int[M];
        long[] W = new long[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            S[i] = s; E[i] = e; W[i] = w;

            // 최단거리용 인접행렬에는 "두 점 사이 최소 간선"만 반영
            if (w < dist[s][e]) {
                dist[s][e] = w;
                dist[e][s] = w;
            }
        }

        // Floyd-Warshall (모든 쌍 최단거리)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 1; j <= N; j++) {
                    if (dist[k][j] == INF) continue;
                    long cand = dist[i][k] + dist[k][j];
                    if (cand < dist[i][j]) dist[i][j] = cand;
                }
            }
        }

        long answerTwice = INF; // 최종 시간의 2배를 정수로 보관

        for (int start = 1; start <= N; start++) {
            long maxTwice = 0;

            for (int eIdx = 0; eIdx < M; eIdx++) {
                int u = S[eIdx], v = E[eIdx];
                long w = W[eIdx];

                // start에서 이 간선 양 끝점까지 모두 도달 불가하면 "무한 시간"
                if (dist[start][u] == INF || dist[start][v] == INF) {
                    maxTwice = INF;
                    break;
                }

                // (dist[start][u] + dist[start][v] + w) / 2 가 실제 시간
                long timeTwice = dist[start][u] + dist[start][v] + w;
                if (timeTwice > maxTwice) maxTwice = timeTwice;
            }

            if (maxTwice < answerTwice) answerTwice = maxTwice;
        }

        // 정수부.소수부(.0 또는 .5)로 출력
        if (answerTwice >= INF / 2) {
            // 이 케이스는 일반적으로 나오지 않지만, 안전하게 처리
            System.out.println("INF");
        } else {
            long intPart = answerTwice / 2;
            long half = answerTwice % 2; // 0이면 .0, 1이면 .5
            System.out.println(intPart + "." + (half == 0 ? 0 : 5));
        }
    }
}