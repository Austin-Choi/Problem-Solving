/*
dist [목적지][포장도로수] = 걸린시간
다익스트라 돌리면서 포장할수 있으면 하고
포장 안한것도 둬서 마지막에
dist [N] 포장도로 경우의수 다 순회하면서 최소값 출력
--
Info -> dest, cost, k-used
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static long MAXL = 50000L * 1_000_000 +1;
    static HashMap<Integer, Long>[] board;
    static class Info implements Comparable<Info>{
        int dest;
        long cost;
        int kcount;
        Info(int d, long c, int k){
            this.dest = d;
            this.cost = c;
            this.kcount = k;
        }

        @Override
        public int compareTo(Info o){
            return Long.compare(this.cost, o.cost);
        }
    }
    static long dijkstra(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        long[][] dist = new long[N+1][K+1];
        for(int i = 1; i<=N; i++){
            Arrays.fill(dist[i], MAXL);
        }
        dist[1][0] = 0;
        pq.add(new Info(1,0, 0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            long cc = cur.cost;
            int ck = cur.kcount;

            if(cc != dist[cd][ck])
                continue;

            for(Map.Entry<Integer, Long> e : board[cd].entrySet()){
                int nd = e.getKey();
                long nc = e.getValue();

                long nnc = cc + nc;
                if(nnc < dist[nd][ck]){
                    dist[nd][ck] = nnc;
                    pq.add(new Info(nd, nnc, ck));
                }

                if(ck < K && cc < dist[nd][ck+1]){
                    dist[nd][ck+1] = cc;
                    pq.add(new Info(nd, cc, ck+1));
                }
            }
        }
        long ans = MAXL;
        for(long d : dist[N]){
            ans = Math.min(ans, d);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new HashMap[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new HashMap<>();
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            long cuv = Math.min(c, board[u].getOrDefault(v,MAXL));
            long cvu = Math.min(c, board[v].getOrDefault(u, MAXL));
            board[u].put(v, cuv);
            board[v].put(u, cvu);
        }

        System.out.print(dijkstra());
    }
}
