// dist[도착지][경비] = 도착시간
// pq에 넣고 더 비싼 간선들도 nt로 덮어씌움
// -> 비용이 더 크고 느린 상태 (비효율적인 탐색 줄이기)
// 입력 정렬하면 더 많은 비용 범위를 한번에 덮음
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static ArrayList<int[]>[] board;
    static final int maxT = 10_000_001;
    static int dijkstra(){
        int[][] dist = new int[N+1][M+1];
        for(int i = 0; i<=N; i++){
            Arrays.fill(dist[i], maxT);
        }
        dist[1][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        pq.add(new int[]{1,0,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cd = cur[0];
            int cc = cur[1];
            int ct = cur[2];

            if(cc > M)
                continue;
            if(dist[cd][cc] != ct)
                continue;
            if(cd == N)
                return ct;

            for(int[] i : board[cd]){
                int nd = i[0];
                int nc = cc + i[1];
                int nt = ct + i[2];
                
                if(nc > M)
                    continue;
                if(dist[nd][nc] <= nt)
                    continue;

                dist[nd][nc] = nt;
                pq.add(new int[]{nd, nc, nt});
                for(int c = nc + 1; c<=M && dist[nd][c] > nt; c++)
                    dist[nd][c] = nt;
            }
        }

        int ans = maxT;
        for(int i = 0; i<=M; i++)
            ans = Math.min(ans, dist[N][i]);
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
        for(int i = 1; i<=N; i++)
            board[i] = new ArrayList<>();
        for(int k = 0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            board[u].add(new int[]{v,c,d});
        }
        for(int i = 1; i<=N; i++)
            Collections.sort(board[i], Comparator.comparingInt(o->o[2]));

        int ans = dijkstra();
        if(ans == maxT){
            System.out.println("Poor KCM");
        }
        else
            System.out.println(ans);
    }
}
