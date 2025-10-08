/*
1) 1~C 이분탐색으로 최대 수금액 정하기(mid)
2) A-B 경로에서 mid이하의 경로만 택해서 경로를 만들었을때 그 dist가 C이하인지 보기 (결정함수)
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,A,B;
    static long C;
    static ArrayList<Info>[] board;
    static class Info implements Comparable<Info>{
        int dest;
        long cost;
        Info(int d, long c){
            this.dest = d;
            this.cost = c;
        }
        @Override
        public int compareTo(Info o){
            return Long.compare(this.cost, o.cost);
        }
    }
    static boolean can(long limit){
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[A] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(A,0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            long cc = cur.cost;

            if(dist[cd] < cc)
                continue;

            for(Info l : board[cd]){
                int nd = l.dest;
                long nc = l.cost;
                if(nc <= limit){
                    if(dist[nd] > dist[cd] + nc){
                        dist[nd] = dist[cd] + nc;
                        pq.add(new Info(nd, dist[nd]));
                    }
                }
            }
        }

        return dist[B] <= C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            board[s].add(new Info(e,c));
            board[e].add(new Info(s,c));
        }

        long l = 1;
        long r = C;
        long ans = -1;
        while(l<=r){
            long mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid - 1;
            }
            else
                l = mid + 1;
        }
        System.out.println(ans);
    }
}
