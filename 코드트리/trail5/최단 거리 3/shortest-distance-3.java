// import java.util.*;
// import java.io.*;

// public class Main {
//     static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

//     static int read() throws IOException{
//         sst.nextToken();
//         return (int) sst.nval;
//     }

//     static int N,M,A,B;
//     static ArrayList<int[]>[] g;
//     static final long INF = 100_000 * 100_000 + 1;

//     static long dijkstra(){
//         PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
//         long[] dist = new long[N+1];
//         Arrays.fill(dist, INF);
//         dist[A] = 0;
//         q.add(new long[]{A, 0});

//         while(!q.isEmpty()){
//             long[] cur = q.poll();
//             int ci =(int) cur[0];
//             long cd = cur[1];
            
//             if(dist[ci] != cd)
//                 continue;
            
//             for(int[] n : g[ci]){
//                 int ni = n[0];
//                 int nl = n[1];

//                 if(dist[ni] > dist[ci] + nl){
//                     dist[ni] = dist[ci] + nl;
//                     q.add(new long[]{(long)ni, dist[ni]});
//                 }
//             }
//         }
//         return dist[B];
//     }

//     static String parser(int i, int j){
//         return i + "," + j;
//     }

//     public static void main(String[] args) throws IOException{
//         N = read();
//         M = read();
//         g = new ArrayList[N+1];
//         for(int i = 1; i<=N; i++)
//             g[i] = new ArrayList<>();

//         // 중복간선 처리해야함
//         // N이 1000이라서 사실 배열로 min 잡아도 되긴하는데
//         // 10만이라 가정하면 그렇게는 못하니깐
//         // 해시맵씀
//         // -> 원래는 입력할때 바로 최솟값 비교하고 업뎃하고 했는데
//         // 최솟값이 나중 간선에 나올땐 전께 갱신이 안돼서 한번에 map에 기록해주고 
//         // 나중에 map 돌면서 인접리스트로 그래프 완성하기
//         HashMap<String, Integer> m = new HashMap<>();
//         while(M-->0){
//             int u = read();
//             int v = read();
//             int w = read();
//             String k = parser(Math.min(u,v), Math.max(u,v));
//             m.put(k, Math.min(m.getOrDefault(k, Integer.MAX_VALUE), w));
//         }

//         for(Map.Entry<String, Integer> e : m.entrySet()){
//             String[] s = e.getKey().split(",");
//             int u = Integer.parseInt(s[0]);
//             int v = Integer.parseInt(s[1]);
//             int w = e.getValue();

//             g[u].add(new int[]{v,w});
//             g[v].add(new int[]{u,w});
//         }
//         A = read();
//         B = read();
//         System.out.print(dijkstra());
//     }
// }

import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,A,B;
    static ArrayList<int[]>[] g;
    static final long INF = 100_000 * 100_000 + 1;

    static long dijkstra(){
        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(a->a[1]));
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[A] = 0;
        q.add(new long[]{A, 0});

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
        return dist[B];
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
        A = read();
        B = read();
        System.out.print(dijkstra());
    }
}
