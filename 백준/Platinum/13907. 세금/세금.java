/*
dist [ V ] [ K(<=V) ]
dist[ 도착지 ][ 사용 간선 수 ] = 최소비용
-> 사용 간선이 적을수록 유리하지만 세금이 작을때는
반드시 그런것이 아님. 따라서 모든 사용 간선 수에 대해서 구해줘야함
-> 다익스트라 전처리
세금 배열은 누적합 배열 처리로 쓰고
업데이트 될때마다 (0<=k<=V) dist[도착지][k] + k*세금 중 최소값 출력
----
prefix-min 승격
갱신을 할때 지금 갱신되는건 k+1이고
그 이상의 값들은 더해지기만 할거니까
지금의 최소값으로 덮어써버리면 쓸모없는 계산들이 없어짐
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K,S,D;
    static final long INF = Long.MAX_VALUE;
    static long[][] board;
    static ArrayList<Info>[] adj;
    // 도착지에 k개 간선을 써서 도착하는 각 k개 경로마다의 최소값
    // 세금 0 상태임
    static long[] base;
    static long dijkstra(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        long[][] dist = new long[N+1][N+1];
        for(int i = 1; i<=N; i++){
            Arrays.fill(dist[i], INF);
        }
        dist[S][0] = 0;
        pq.add(new Info(S,0,0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            int ck = cur.countK;
            long cc = cur.cost;

            if(dist[cd][ck] != cc)
                continue;

            if(ck >= N-1)
                continue;

            for(Info e : adj[cd]){
                int nd = e.dest;
                long nc = cc + e.cost;
                if(dist[nd][ck+1] > nc){
                    dist[nd][ck+1] = nc;
                    pq.add(new Info(nd, nc, ck+1));
                    for(int j = ck +2; j<=N-1; j++){
                        if(dist[nd][j] > nc)
                            dist[nd][j] = nc;
                        else
                            break;
                    }
                }
            }
        }

        long minCost = INF;
        base = dist[D];
        for(long c : base){
            minCost = Math.min(minCost, c);
        }
        return minCost;
    }
    static class Info implements Comparable<Info>{
        int dest;
        long cost;
        int countK;
        Info(int d, long c, int ck){
            this.dest = d;
            this.cost = c;
            this.countK = ck;
        }
        @Override
        public int compareTo(Info o){
            return Long.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new long[N+1][N+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(board[i], INF);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            c = Math.min(c, board[s][e]);
            board[s][e] = c;
            board[e][s] = c;
        }

        adj = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            adj[i] = new ArrayList<>();
            for(int j = 1; j<=N; j++){
                if(board[i][j] < INF)
                    adj[i].add(new Info(j, board[i][j], 0));
            }
        }

        long bill = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(dijkstra()).append("\n");

        for(int k = 0; k<K; k++){
            bill += Long.parseLong(br.readLine());
            long minCost = INF;
            for(int ck = 0; ck<=N; ck++){
                if(base[ck] == INF)
                    continue;
                minCost = Math.min(minCost, base[ck] + ck * bill);
            }
            sb.append(minCost).append("\n");
        }
        System.out.println(sb);
    }
}
