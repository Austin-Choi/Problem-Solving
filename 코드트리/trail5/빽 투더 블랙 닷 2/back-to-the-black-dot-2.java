import java.util.*;
import java.io.*;

/*
비트마스크는 안될거같은게 중복 방문 허용해서 거리가 꼬일거같음
빨간 점에서 모든 검은 점까지의 최단거리 두개 더하기?
돌아와야하니까 최단거리 2배씩해서 더하기?


*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int A,B;
    static ArrayList<int[]>[] g;
    static final int INF = 1000 * 100_000 + 1;

    static int[] dijkstra(int si){
        int[] dist =new int[N+1];
        Arrays.fill(dist, INF);
        dist[si] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        q.add(new int[]{si, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nd = n[1];
                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci] + nd;
                    q.add(new int[]{ni, dist[ni]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        A = read();
        B = read();
        g =new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int i = read();
            int j = read();
            int w = read();
            g[i].add(new int[]{j,w});
            g[j].add(new int[]{i,w});
        }

        int[] distSA = dijkstra(A);
        int[] distSB = dijkstra(B);
        int red = distSA[B];
        int min = INF;
        for(int i = 1; i<=N; i++){
            if(i == A || i == B)
                continue;
            if(distSA[i] == INF || distSB[i] == INF)
                continue;
            min = Math.min(min, distSA[i] + distSB[i]+red);
        }
        System.out.print(min == INF ? -1 : min);
    }
}