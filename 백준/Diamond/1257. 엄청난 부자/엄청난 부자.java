import java.util.*;
import java.io.*;
/*
주어진 M을 N 종류의 동전을 활용해서
최소의 동전 갯수로 돈을 전부 바꾸고 싶어함.

그냥 젤큰거 base로 잡기
어떤 경우엔 base를 하나 덜 쓰고 다른 동전들로 채우는게 이득일 수 있음
dist[r] = r을 만들기 위해 필요한 base 사용 횟수 감소량
-> base를 최대한 쓰는데 보정을 다익스트라로
 */
public class Main {
    static long M;
    static int N;
    static int[] A;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    static void dijkstra(int base) {
        dist = new int[base];
        Arrays.fill(dist, INF);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        dist[0] = 0;
        pq.add(0);

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            // 가장 큰 동전 제외
            for (int i = 0; i < N - 1; i++) {
                int next = cur + A[i];
                int nr = next % base;

                // cur + coin < base
                // -> cost = 1 (손해)
                // cur + coin >= base
                // -> cost = 0 (이득)
                int cost = (next < base) ? 1 : 0;
                if (dist[nr] > dist[cur] + cost) {
                    dist[nr] = dist[cur] + cost;
                    pq.add(nr);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Long.parseLong(br.readLine());
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        int base = A[N-1];
        dijkstra(base);

        int r = (int)(M % base);
        long answer = M / base + dist[r];
        System.out.println(answer);
    }
}
