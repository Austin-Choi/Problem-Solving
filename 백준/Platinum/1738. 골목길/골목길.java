/*
 가능한 경우들
 1) 양의 사이클 없고 n 정점 도달 못함 -> -1
 2) 양의 사이클 있고 n 정점에 도달함 -> 최대액 부정확함 -> -1
 3) 양의 사이클 있는데 그 사이클 정점들이 n 정점에 도달하지 않음
 -> 최대액 한 가지로 정의됨 -> 경로출력
 4) 양의 사이클 없고 n 정점 도달함 -> 경로출력
 */
import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static ArrayList<int[]>[] board;
    static int[] dist;
    static final int INF = 20000*1000 + 1;
    static boolean[] isCycle;
    static int[] parent;
    static boolean hasCycle;
    static void bf(){
        isCycle = new boolean[n+1];
        parent = new int[n+1];
        Arrays.fill(parent, -1);

        dist = new int[n+1];
        Arrays.fill(dist, -INF);
        dist[1] = 0;

        for(int i = 1; i<=n; i++){
            for(int u = 1; u<=n; u++){
                for(int[] next : board[u]){
                    int v = next[0];
                    if(dist[u] != -INF){
                        int nc = dist[u] + next[1];
                        if(nc > dist[v]){
                            dist[v] = nc;
                            if(i == n){
                                hasCycle = true;
                                isCycle[v] = true;
                            }
                            // 사이클에서 같이판정해버리면 오염됨
                            else
                                parent[v] = u;
                        }
                    }
                }
            }
        }
    }

    // parent 따라서 역추적하면 됨 (사이클없을때)
    static ArrayList<Integer> bt(){
        int cur = n;
        ArrayList<Integer> rst = new ArrayList<>();
        rst.add(n);
        while(true){
            cur = parent[cur];
            rst.add(cur);
            if(cur == 1)
                break;
        }
        Collections.reverse(rst);
        return rst;
    }

    // 사이클 소속된 정점들에서 N에 도달할 수 있는가
    static boolean canReachN(){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i<=n; i++){
            if(isCycle[i]){
                q.add(i);
                visited[i] = true;
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == n)
                return true;

            for(int[] next : board[cur]){
                int nd = next[0];
                if(!visited[nd]){
                    visited[nd] = true;
                    q.add(nd);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            board[i] = new ArrayList<>();
        }

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            board[u].add(new int[]{v,w});
        }

        bf();

        if(dist[n] == -INF){
            System.out.println(-1);
        }
        else{
            if(hasCycle){
                // 사이클 있는데 도착지에 가는경우
                // 최대액 부정확한상태라 -1
                if(canReachN())
                    System.out.println(-1);
                // 사이클 있긴한데 도착지에 영향 안감!!!
                else{
                    StringBuilder sb = new StringBuilder();
                    ArrayList<Integer> li = bt();
                    for(int u : li){
                        sb.append(u).append(" ");
                    }
                    System.out.println(sb);
                }
            }
            else{
                StringBuilder sb = new StringBuilder();
                ArrayList<Integer> li = bt();
                for(int u : li){
                    sb.append(u).append(" ");
                }
                System.out.println(sb);
            }
        }
    }
}
