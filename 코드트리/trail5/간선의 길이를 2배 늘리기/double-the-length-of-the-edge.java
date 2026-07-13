import java.util.*;
import java.io.*;

/*
양방향 그래프이고 
1->i, j->N 거리 구해놓고 모든 간선에 대해 순회할때 1->i + 2*(i,j) + j->N 을 max로
max - distSE[N]
---------------------------------

최단 경로 복원해서 최단경로 위 간선들을 2배한 상태 한번씩 다익스트라 돌려보기

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static ArrayList<int[]>[] g;
    static HashMap<Long, Integer> map;
    static final long INF = 25_000L * 1_000_000 + 1;

    static long[] dijkstra(int si, boolean isSecond, long key){
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[si] = 0;
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        q.add(new long[]{si, 0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];

            if(dist[ci] != cd)
                continue;

            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];

                // 비교하기 전에 2배 간선 가중치 해야함..
                if(isSecond){
                    int[] rst = ltoi(key);
                    if((ci == rst[0] && ni == rst[1]) || (ci == rst[1] && ni == rst[0]))
                    nd += map.get(key);
                }

                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci] + nd;
                    q.add(new long[]{(long) ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    static long itol(int i, int j){
        return ((long) i)<<32 | j;
    }

    static int[] ltoi(long key){
        int[] rst = new int[2];
        rst[0] = (int) (key >> 32);
        rst[1] = (int)(key - ((key >> 32) << 32));
        return rst;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        int[][] li = new int[N+1][N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int i = read();
            int j = read();
            int w = read();
            li[i][j] = li[j][i] = w;
            g[i].add(new int[]{j,w});
            g[j].add(new int[]{i,w});
        }

        long[] distSI = dijkstra(1, false, 0);
        map = new HashMap<>();

        int cur = N;
        while(cur != 1){
            for(int[] n : g[cur]){
                int ni = n[0];
                int nd = n[1];
                if(distSI[cur] == distSI[ni] + nd){
                    map.put(itol(cur,ni), nd);
                    cur = ni;
                }
            }
        }

        long max = 0;
        for(long k : map.keySet()){
            max = Math.max(max, dijkstra(1, true, k)[N]);
        }


        System.out.print(max - distSI[N]);
    }
}