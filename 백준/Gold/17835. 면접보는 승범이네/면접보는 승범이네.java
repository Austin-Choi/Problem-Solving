/*
단방향, 같은 uv사이 간선 여러가지
dist[도시] = 도시 -> 가장 가까운 면접장의 최단거리
도시 -> 면접장으로 하면 너무 오래걸려서 입력을 u,v,c를 v,u,c로 받고
면접장 (다중 다익)으로 면접장 -> 도시
-> 다익스트라 1번으로 끝
 */
import java.util.*;
import java.io.*;
public class Main {
    static HashMap<Integer, Long>[] board;
    static int N,M,K;
    static HashSet<Integer> interview;
    static final long MAXD = 100000000001L;
    static long[] dijkstra(Set<Integer> starts){
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        long[] dist = new long[N+1];
        Arrays.fill(dist, MAXD);
        Iterator<Integer> it = starts.iterator();
        while(it.hasNext()){
            int n = it.next();
            dist[n] = 0;
            pq.add(new long[]{(long) n, 0});
        }

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int cu = (int) cur[0];
            long cc = cur[1];

            if(dist[cu] < cc)
                continue;

            for(Map.Entry<Integer, Long> e : board[cu].entrySet()){
                int nu = e.getKey();
                long nc = e.getValue();
                if(dist[nu] > dist[cu]+nc){
                    dist[nu] = dist[cu]+nc;
                    pq.add(new long[]{(long) nu, dist[nu]});
                }
            }
        }

        long[] rst = new long[2];
        long d = 0L;
        for(int i = 1; i<=N; i++){
            if(starts.contains(i))
                continue;
            if(dist[i] == MAXD)
                continue;
            if(dist[i] > d){
                rst[0] = i;
                rst[1] = dist[i];
                d = dist[i];
            }
        }
        return rst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new HashMap[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new HashMap<>();
        }
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(board[v].containsKey(u)){
                c = Math.min(c, board[v].get(u));
            }
            board[v].put(u,c);
        }
        st = new StringTokenizer(br.readLine());
        interview = new HashSet<>();
        for(int k = 0; k<K; k++){
            interview.add(Integer.parseInt(st.nextToken()));
        }
        long[] ans = dijkstra(interview);
        System.out.println(ans[0]+"\n"+ans[1]);
    }
}
