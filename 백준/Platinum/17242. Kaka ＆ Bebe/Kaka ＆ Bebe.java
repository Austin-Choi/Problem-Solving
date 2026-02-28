/*
스트레스 값은 도착하고 나서의 ka 총마리수 * be 총마리수
-> 어느쪽이 더 좋은지 중간에는 판단 불가
dist[v] = u에서 시작해서 v에 도달할때의 ka 총마리와 be 총마리
-> queue <다음정점, 현재까지 총 ka, 현재까지 총 be>

다익스트라 돌릴때 목적지에 도달하는 상태들 중 상태 확장 가능한 집합 List[] 형태로 유지
-> dist
1) 경로 확장 불가능 : 기존ka <= new ka && 기존 be <= new be
-> 스트레스값 무조건 커서 볼필요도 없음
2) 경로 확장 가능
-> 새 ka be에 대해서 이거보다 같거나 큰애들은 다 리스트에서 제거해야함
3) 새 ka be dist에 삽입함
4) ans는 N-1에 도착했을때 현재 ka*be
 */
import java.util.*;
import java.io.*;
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N,M;
    static ArrayList<int[]>[] g;
    // 각 정점의 파레토 프론티어
    // -> 경로 채택 가능성이 있는 집합
    static ArrayList<int[]>[] dist;
    static long ans = INF;
    /*
    정점 v에 새 상태 (ka, be)로 도착함
    v에 도착하는 다른 상태들 dist[v] 에 대해서
    둘 다 더 크면 스트레스값이 커져서 미래 가능성이 전혀 없음 (true)
     */
    static boolean isDominated(int v, int ka, int be){
        for(int[] p : dist[v]){
            if(p[0] <= ka && p[1] <= be)
                return true;
        }
        return false;
    }
    // 현재 값보다 둘다 큰 (ka,be) 쌍 dist에서 제거해서
    // 걔네로부터 확장 막음
    static void removeDominated(int v, int ka, int be){
        Iterator<int[]> it = dist[v].iterator();
        while(it.hasNext()){
            int[] p = it.next();
            if(ka <= p[0] && be <= p[1])
                it.remove();
        }
    }
    static void dijkstra(){
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b)->Integer.compare(a[1] * a[2], b[1]* b[2])
        );

        pq.add(new int[]{0,0,0});
        dist[0].add(new int[]{0,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            // 목적지에 여러 파레토 상태 가능해서 끝까지
            if(cur[0] == N-1){
                ans = Math.min(ans, cur[1] * cur[2]);
                continue;
            }

            for(int[] e : g[cur[0]]){
                int nv = e[0];
                int nka = cur[1] + e[1];
                int nbe = cur[2] + e[2];

                // ka와 be 각각 총 마리 수 는 각각 1000 넘으면 안됨
                if(nka > 1000 || nbe > 1000)
                    continue;

                // 경로 확장 가능성 없으면 prune
                if(isDominated(nv, nka, nbe))
                    continue;

                // 경로 확장 가능함
                removeDominated(nv, nka, nbe);

                dist[nv].add(new int[]{nka, nbe});
                pq.add(new int[]{nv, nka, nbe});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new ArrayList[N];
        dist = new ArrayList[N];
        for(int i =0 ; i<N; i++){
            g[i] = new ArrayList<>();
            dist[i] = new ArrayList<>();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int ka = Integer.parseInt(st.nextToken());
            int be = Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,ka,be});
            g[v].add(new int[]{u,ka,be});
        }

        dijkstra();
        System.out.print(ans== INF? -1 : ans);
    }
}
