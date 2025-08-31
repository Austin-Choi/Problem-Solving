/*
양방향 그래프
같은 섬 사이에 여러개의 다리가 있을수 있음 -> 그중 큰걸로
공장이 있는 두섬 사이 경로 출력
경로는 항상 개중 큰거 하는데 경로중에 최소값이 정답임

minimax path 문제
변형 다익스트라 or 이분탐색 + BFS
여러 쿼리 -> MST + LCA

입력받을때 인접리스트로 최소값 최대값 받기
-> Map<정점, cost>[] 배열로 받아서
getOrDefault로 불러와서 업데이트

다익스트라
-> 최대값 찾는거니까 dist는 모두 -1, 시작은 S, MAX_VALUE로 큐에 넣기
entrySet으로 순회하면서
costSoFar랑 현재 edge의 cost중 작은거 nextCost로 저장
nextCost가 dist[next] 보다 크면
dist[next] = nextCost해주고
큐에 nest, nextCost 넣기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int S, E;
    static Map<Integer, Long>[] board;
    static long[] dist;

    static void bfs(){
        dist = new long[N+1];
        Arrays.fill(dist, -1);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(b[1], a[1]));
        dist[S] = 0;
        pq.add(new long[]{S, Long.MAX_VALUE});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int now = (int) cur[0];
            long costSoFar = cur[1];

            if(dist[now] > costSoFar)
                continue;

            for(Map.Entry<Integer, Long> e : board[now].entrySet()){
                int next = e.getKey();
                long eCost = e.getValue();

                // 병목지점 골라내기
                long nextCost = Math.min(costSoFar, eCost);
                // 간선 중에는 가장 큰 값으로 업데이트
                if(dist[next] < nextCost){
                    dist[next] = nextCost;
                    pq.add(new long[]{next, nextCost});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new HashMap[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new HashMap<>();
        }
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            board[s].put(e, Math.max(board[s].getOrDefault(e, 0L), c));
            board[e].put(s, Math.max(board[e].getOrDefault(s, 0L), c));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(dist[E]);
    }
}
