// X에서 시작해서 모든점 도달 + 모든점 한번씩에서 X
// -> 최단거리 경로 자체는 달라도 역방 그래프로 놓으면 X->n으로 가는 경로를 뒤집은 대응 경로가 존재하기 때문에
// -> 역방 그래프 계산용으로 놓고 다익 2번으로 최적화
import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<int[]>[] g;
    static ArrayList<int[]>[] rg;
    static int N,M,X;
    static final int INF = 100*10000+1;
    static int[] dijkstra(int start, ArrayList<int[]>[] gg){
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        q.add(new int[]{start, 0});
        dist[start] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(dist[ci] != cd)
                continue;

            for(int[] n : gg[ci]){
                if(dist[n[0]] > dist[ci] + n[1]){
                    dist[n[0]] = dist[ci] + n[1];
                    q.add(new int[]{n[0], dist[n[0]]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        g = new ArrayList[N+1];
        rg = new ArrayList[N+1];
        for(int i =1; i<=N; i++) {
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,d});
            rg[v].add(new int[]{u,d});
        }


        int ans = -1;
        int[] fromX = dijkstra(X, g);
//        for(int i = 1; i<=N; i++){
//            int[] toX = dijkstra(i);
//            if(fromX[i] == INF || toX[X] == INF)
//                continue;
//            ans = Math.max(ans, fromX[i] + toX[X]);
//        }
        int[] toX = dijkstra(X, rg);
        for(int i = 1; i<=N; i++) {
            if (fromX[i] == INF || toX[X] == INF)
                continue;
            ans = Math.max(ans, fromX[i] + toX[i]);
        }
        System.out.print(ans);
    }
}
