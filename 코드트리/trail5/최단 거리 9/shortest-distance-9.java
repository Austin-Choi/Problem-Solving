import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<int[]>[] g;
    static final long INF = 100_000 * 100_000 + 1;

    static long[] dijkstra(int si){
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[si] = 0;
        q.add(new long[]{si, 0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nw = n[1];

                if(dist[ni] > dist[ci] + nw){
                    dist[ni] = dist[ci] + nw;
                    q.add(new long[]{(long) ni, dist[ni]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        int s = read();
        int e = read();
        long[] dist = dijkstra(s);
        StringBuilder sb = new StringBuilder();
        sb.append(dist[e]).append("\n");

        // 경로 역추적해서 복원 뒤에서부터 하는거라 뒤집어야함
        ArrayList<Integer> li = new ArrayList<>();
        li.add(e);
        int cur = e;

        while(cur != s){
            for(int[] n : g[cur]){
                int ni = n[0];
                int nw = n[1];
                //cur->ni 방향이라 ni+nw == cur
                if(dist[ni] + nw == dist[cur]){
                    li.add(ni);
                    cur = ni;
                    break;
                }
            }
        }
        for(int i = li.size()-1; i>=0; i--){
            sb.append(li.get(i)).append(" ");
        }

        System.out.print(sb);
    }
}