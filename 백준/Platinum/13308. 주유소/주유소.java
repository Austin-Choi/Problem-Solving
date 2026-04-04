import java.util.*;
import java.io.*;

/*
이동할때 dist[cur] + minprice * 가중치(해당 도착지로 가기 위한 추가 비용)
-> dist에 주유소 가격이 영향을 주기 때문에 최단경로 DAG 구성 불가능
-> dist에 상태 추가
-> dist[노드][최소 기름 가격 주유소 인덱스]
-> 다익스트라 한번
 */

public class Main {
    static final long INF = 4000 * 2500 * 2500L + 1;
    //1-based
    static int N,M;
    static long[] price;
    //무방향
    static ArrayList<int[]>[] g;

    static long dijkstra(int start){
        long[][] dist = new long[N+1][N+1];
        for(int i = 1; i<=N; i++)
            Arrays.fill(dist[i], INF);
        dist[start][start] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a->a[2]));
        // 현재, 최소주유소, dist
        pq.add(new long[]{start, start, 0});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int ci = (int) cur[0];
            int minIdx = (int) cur[1];
            long cd = cur[2];

            if(cd != dist[ci][minIdx])
                continue;

            for(int[] n : g[ci]){
                int ni = n[0];
                long nw = n[1];
                int nIdx = minIdx;
                // 다음 주유소가 새로운 최소가격 주유소인지 확인
                if(price[ni] < price[minIdx]){
                    nIdx = ni;
                }
                // 지금의 다음 cost는 현재까지 지나온 주유소중
                // 최소 가격의 주유소로 계산함
                // 경로를 따라가며 넣는거라
                // 뒤에 최소 가격 주유소가 나와도 앞에서는
                // 그 전의 최소 가격 주유소에서 주유하고 가야하는거라
                long nextCost = dist[ci][minIdx] + (nw * price[minIdx]);

                if(dist[ni][nIdx] > nextCost){
                    dist[ni][nIdx] = nextCost;
                    pq.add(new long[]{ni, nIdx, nextCost});
                }
            }
        }
        long ans = INF;
        for(int i = 1; i<=N; i++){
            ans = Math.min(ans, dist[N][i]);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        price = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            price[i] = Integer.parseInt(st.nextToken());
        }
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }
        System.out.println(dijkstra(1));
    }
}