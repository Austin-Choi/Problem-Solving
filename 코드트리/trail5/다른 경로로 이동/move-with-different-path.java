import java.util.*;
import java.io.*;

/*
1.N->1 방향으로 갱신하는 dist 구함
-> 양방향 그래프라 따로 역그래프 만들지는 않음
-> 대신 정점 작은 순서로 각 ArrayList 정렬함
2. dist 정보로 dfs하면서 최단경로 DAG 만듬
-> 1번에서 정렬해놔서 사전순 앞선 경로 채택될거임
-> 이때 i,j를 long형 key로 갖고 w를 value로 갖는 map에 저장
4. 다시 원래의 그래프에서 1->N 방향으로 최단거리 구하는데 map에 저장된 간선은 피하면서 dist 갱신
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[][] cost;
    static ArrayList<int[]>[] g;
    static Map<Long, Integer> map = new HashMap<>();
    static final long INF = 100_000 * 100_000L + 1;

    static long itol(int i, int j){
        return (((long)i)<<32) + j;
    }

    static long[] dijkstra(int s, boolean isSecond){
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        q.add(new long[]{s, 0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];

            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];
                if(isSecond && map.containsKey(itol(ci, ni)))
                    continue;
                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci] + nd;
                    q.add(new long[]{ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        cost = new int[N+1][N+1];
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            if(cost[u][v] != 0)
                cost[u][v] = cost[v][u] = Math.min(cost[u][v], w);
            else
                cost[u][v] = cost[v][u] = w;
        }

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(cost[i][j] == 0)
                    continue;
                g[i].add(new int[]{j,cost[i][j]});
            }
        }

        long[] distSE = dijkstra(1,false);
        long[] distES = dijkstra(N,false);
        if(distSE[N] == INF){
            System.out.print(-1);
            return;
        }

        int cur = 1;
        while(cur != N){
            for(int[] n : g[cur]){
                int ni = n[0];
                int nd = n[1];
                if(distSE[cur] + nd + distES[ni] == distSE[N]){
                    map.put(itol(cur, ni), nd);
                    map.put(itol(ni, cur), nd);
                    cur = ni;
                    break;
                }
            }
        }

        long[] dist2 = dijkstra(1, true);
        System.out.print(dist2[N]);
    }
}