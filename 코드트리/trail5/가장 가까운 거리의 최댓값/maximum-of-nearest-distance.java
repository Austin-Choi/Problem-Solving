import java.util.*;
import java.io.*;

// 다중 시작점 다익스트라????
// -> a,b,c를 큐에 넣고 시작하면 dist[i]는 a,b,c에서 도달하는 가장 가까운 거리임
// -> 이중 최대 출력

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,A,B,C;
    static ArrayList<int[]>[] g;
    static ArrayList<Integer> sl = new ArrayList<>();
    static final long INF = 10000 * 100_000L + 1;

    static long dijkstra(){
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        for(int si : sl){
            dist[si] = 0;
            q.add(new long[]{si, 0});
        }

        while(!q.isEmpty()){
            long[] cur = q.poll();
            int ci =(int) cur[0];
            long cd = cur[1];
            
            if(dist[ci] != cd)
                continue;
            
            for(int[] n : g[ci]){
                int ni = n[0];
                int nl = n[1];

                if(dist[ni] > dist[ci] + nl){
                    dist[ni] = dist[ci] + nl;
                    q.add(new long[]{(long)ni, dist[ni]});
                }
            }
        }
        
        long ans = 0;
        for(int i = 1; i<=N; i++){
            ans = Math.max(ans, dist[i]);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        for(int i = 0; i<3; i++){
            sl.add(read());
        }

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

        System.out.print(dijkstra());
    }
}