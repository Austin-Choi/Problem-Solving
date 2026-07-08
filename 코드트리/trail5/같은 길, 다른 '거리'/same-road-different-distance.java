import java.util.*;
import java.io.*;

/*

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    // 입력 역그래프 2개로 최단경로에 속하는지 보고
    static ArrayList<int[]>[] ga;
    static ArrayList<int[]>[] gb;
    // 패널티를 cost로 갖는 그래프
    static ArrayList<int[]>[] g;
    static final long INF = 50000 * 100_000L + 1;

    static long[] dijkstra(ArrayList<int[]>[] g){
        // 번호, 가중치
        PriorityQueue<long[]> q = new PriorityQueue<>((a,b)->{
            if(a[1] != b[1])
                return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[N] = 0;
        q.add(new long[]{N, 0});

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci = (int) cur[0];
            long cd = cur[1];

            if(cd != dist[ci])
                continue;
            
            for(int[] n : g[ci]){
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
        ga = new ArrayList[N+1];
        gb = new ArrayList[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            ga[i] = new ArrayList<>();
            gb[i] = new ArrayList<>();
            g[i] = new ArrayList<>();
        }

        ArrayList<int[]> li = new ArrayList<>();
        while(M-->0){
            int s = read();
            int e = read();
            int a = read();
            int b = read();
            li.add(new int[]{s,e,a,b});
            ga[e].add(new int[]{s, a});
            gb[e].add(new int[]{s, b});
        }

        long[] da = dijkstra(ga);
        long[] db = dijkstra(gb);
        for(int[] l : li){
            int s = l[0];
            int e = l[1];
            int a = l[2];
            int b = l[3];

            int pa = (da[e] + a == da[s]) ? 0 : 1;
            int pb = (db[e] + b == db[s]) ? 0 : 1;
            g[e].add(new int[]{s, pa+pb});
        }

        long[] dg = dijkstra(g);
        System.out.print(dg[1]);
    }
}