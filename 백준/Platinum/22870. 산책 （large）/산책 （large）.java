import java.util.*;
import java.io.*;
public class Main {
    static int N, M, S, E;
    static final int LIMIT = 500_000 * 1_000 + 1;
    static class Info implements Comparable<Info>{
        int dest;
        int cost;
        Info(int d, int c){
            this.dest = d;
            this.cost = c;
        }

        @Override
        public int compareTo(Info o){
            if(this.cost != o.cost)
                return this.cost - o.cost;
            return this.dest - o.dest;
        }
    }
    static boolean[] removed;
    static int[] dist;
    static int[] distS;
    static int[] distE;
    static int dijkstra(int start, int end, boolean skipRemoved){
        dist = new int[N+1];
        Arrays.fill(dist, LIMIT);

        PriorityQueue<Info> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Info(start, 0));

        boolean[] used = new boolean[N+1];

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int u = cur.dest;
            int cc = cur.cost;

            if(used[u])
                continue;
            used[u] = true;

            if(u == end)
                break;

            for(Info i : board[u]){
                int v = i.dest;
                if(skipRemoved && removed[v] && v != end && v != start)
                    continue;
                int nd = cc + i.cost;
                if(nd < dist[v]){
                    dist[v] = nd;
                    pq.add(new Info(v, nd));
                }
            }
        }

        return dist[end];
    }

    // 거리배열 채우기: removed 무시, end 없이 전노드 거리 계산
    static void dijkstraAll(int start, int[] D){
        Arrays.fill(D, LIMIT);
        boolean[] used = new boolean[N+1];
        PriorityQueue<Info> pq = new PriorityQueue<>();
        D[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int u = cur.dest;
            int du = cur.cost;
            if(used[u]) continue;
            used[u] = true;
            if(du != D[u]) continue;

            for(Info e : board[u]){
                int v = e.dest;
                int nd = du + e.cost;
                if(nd < D[v]){
                    D[v] = nd;
                    pq.add(new Info(v, nd));
                }
            }
        }
    }

    // dist를 만족하는 간선만 보며 s->e로 전진,
    // 매 단계 가장 작은 v를 선택함
    // 경로 내부 정점(s,e 제외)를 removed = true로 마킹
    static void bt(int dest){
        int cur = S;
        while(cur != E){
            int next = -1;
            for(Info e : board[cur]){
                int v = e.dest;
                // S 기준 앞으로 가는 최단 간선
                if(distS[cur] + e.cost != distS[v])
                    continue;
                // V가 s->e 최단 경로 dag위에 있어야 함
                if(distS[v] + distE[v] != distS[E])
                    continue;
                // 정점 번호 사전순
                if(next == -1 || v < next)
                    next = v;
            }
            // 경로가 없을 경우
            if(next == -1)
                break;

            // next가 E가 아니라면 내부 정점이니까 제거 대상임
            if(next != S && next != E)
                removed[next] = true;

            cur = next;
        }
    }
    static ArrayList<Info>[] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[u].add(new Info(v,c));
            board[v].add(new Info(u,c));
        }

        st = new StringTokenizer(br.readLine());
        removed = new boolean[N+1];
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distS = new int[N+1];
        distE = new int[N+1];
        dijkstraAll(S, distS);
        dijkstraAll(E, distE);

        // dist를 이용해 s->e 사전순 최단 경로를 직접 복원하고 내부 정점 제거
        bt(E);
        // e->s 최단 경로(제거 정점 제외)
        int ans = distS[E];
        ans += dijkstra(E,S, true);

        System.out.println(ans);
    }
}