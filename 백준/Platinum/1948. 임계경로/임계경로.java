import java.util.*;
import java.io.*;
class Info{
    int from;
    int to;
    int cost;
    public Info(int f, int t, int c){
        this.from = f;
        this.to = t;
        this.cost = c;
    }
}
public class Main {
    static int N, M, S, E;
    static ArrayList<Info>[] board;
    static ArrayList<Info>[] boardReversed;
    static int[] indegree;
    static int[] dist;
    // 도로수
    static int ans = 0;
    static void topologySort(){
        Queue<Integer> q = new ArrayDeque<>();
        // 최장거리 구할거니까 초기값 0으로 ㄱㅊ
        dist = new int[N+1];
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // 노드꺼낼때마다 최장거리 갱신
        while (!q.isEmpty()) {
            int cur = q.poll();

            for(Info i : board[cur]){
                if(dist[i.to] < dist[cur] + i.cost)
                    dist[i.to] = dist[cur] + i.cost;
                if(--indegree[i.to] == 0)
                    q.add(i.to);
            }
        }
    }

    // dist[prev] + weight = dist[cur]
    // 역방향 그래프 따라가며 도착지점부터 역추적
    // 임계 경로 갯수 구하기
    // 간선 기준 중복체크해야함
    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.add(E);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(Info i : boardReversed[cur]){
                if(dist[i.to] + i.cost == dist[i.from]){
                    String s = i.to +"->"+i.from;
                    if(!visited.contains(s)){
                        visited.add(s);
                        ans++;
                        q.add(i.to);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        board = new ArrayList[N+1];
        boardReversed = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
            boardReversed[i] = new ArrayList<>();
        }
        indegree = new int[N+1];

        for(int m = 0; m<M; m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[s].add(new Info(s,e,c));
            boardReversed[e].add(new Info(e,s,c));
            indegree[e]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        topologySort();
        bfs();

        bw.write(dist[E]+"\n"+ans);
        bw.flush();
        bw.close();
        br.close();
    }
}
