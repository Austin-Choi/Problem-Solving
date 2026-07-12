import java.util.*;
import java.io.*;

/*
방향 그래프고 원래 그래프에서는 X에서 시작해서 N으로 가는 다익스트라 한번
-> X에서 각자 N으로 가는 최단거리
역그래프 하나 만들어서 X 에서 시작해서 N으로 가는 다익스트라 한번
-> 원래 그래프로 치면 각자 N에서 X로 가는 최단거리
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M, X;
    static ArrayList<int[]>[] g;
    static ArrayList<int[]>[] rg;
    static final long INF = 10_000 * 100_000 + 1;

    static long[] dijkstra(ArrayList<int[]>[] gg){
        long[] dist =new long[N+1];
        Arrays.fill(dist, INF);
        dist[X] = 0;
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        q.add(new long[]{X, 0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];

            if(dist[ci] != cd)
                continue;

            for(int[] n : gg[ci]){
                int ni = n[0];
                int nd = n[1];
                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci] + nd;
                    q.add(new long[]{(long) ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        X = read();
        g = new ArrayList[N+1];
        rg = new ArrayList[N+1];
        for(int i= 1; i<=N; i++){
            g[i] = new ArrayList<>();
            rg[i] = new ArrayList<>();
        }

        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            g[u].add(new int[]{v,w});
            rg[v].add(new int[]{u,w});
        }

        long[] distNX = dijkstra(g);
        long[] distXN = dijkstra(rg);
        for(int i = 1; i<=N; i++){
            distNX[i] += distXN[i];
        }

        long max = 0;
        for(int i = 1; i<=N; i++){
            if(i == X)
                continue;
            if(distNX[i] > max){
                max = distNX[i];
            }
        }
        System.out.print(max);
    }
}