import java.util.*;
import java.io.*;
public class Main{
    static int N,M,Q;
    static ArrayList<Integer>[] board;
    static int[][] dist;
    static final int INF = 4001;
    static int[] dijkstra(int start){
        int[] rst = new int[N+1];
        Arrays.fill(rst, INF);
        rst[start] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cd = cur[0];
            int cc = cur[1];

            if(rst[cd] != cc)
                continue;

            for(int n : board[cd]){
                int nc = rst[cd]+1;
                if(rst[n] > nc){
                    rst[n] = nc;
                    q.add(new int[]{n,nc});
                }
            }
        }

        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u].add(v);
        }

        dist = new int[N+1][N+1];
        for(int u = 1; u<=N; u++){
            dist[u] = dijkstra(u);
        }

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int ans = INF;
            for(int i = 1; i<=N; i++){
                if(dist[i][u] < INF && dist[i][v] < INF){
                    int max = Math.max(dist[i][u], dist[i][v]);
                    if(max < ans)
                        ans = max;
                }
            }
            sb.append(ans == INF ? -1 : ans).append("\n");
        }
        System.out.println(sb);
    }
}