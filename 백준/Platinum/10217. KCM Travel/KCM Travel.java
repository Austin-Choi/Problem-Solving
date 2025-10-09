// dist[도착지][경비] = 도착시간
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static ArrayList<Info>[] board;
    static final int maxT = 10_000_001;
    static class Info implements Comparable<Info>{
        int dest;
        int cost;
        int time;
        Info(int d, int c, int t){
            this.dest = d;
            this.cost = c;
            this.time = t;
        }
        @Override
        public int compareTo(Info o){
            return this.time - o.time;
        }
    }
    static int dijkstra(){
        int[][] dist = new int[N+1][M+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=M; j++){
                dist[i][j] = maxT;
            }
        }
        dist[1][0] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0,0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            int cc = cur.cost;
            int ct = cur.time;

            if(cc > M)
                continue;
            if(dist[cd][cc] < ct)
                continue;

            for(Info i : board[cd]){
                int nd = i.dest;
                int nc = cc + i.cost;
                int nt = ct + i.time;
                if(nc > M)
                    continue;
                if(dist[nd][nc] > nt){
                    dist[nd][nc] = nt;
                    pq.add(new Info(nd, nc, nt));
                }
            }
        }

        int ans = maxT;
        for(int i = 0; i<=M; i++){
            ans = Math.min(ans, dist[N][i]);
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int k = 0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board[u].add(new Info(v,c,d));
        }
        for(int i = 1; i<=N; i++){
            Collections.sort(board[i]);
        }

        int ans = dijkstra();
        if(ans == maxT){
            System.out.println("Poor KCM");
        }
        else
            System.out.println(ans);
    }
}
