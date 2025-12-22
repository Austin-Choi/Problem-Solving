/*
N-1개의 간선이니까 트리임 cost long
양방향인듯
binary lifting?
가장 가까운 조상까지 각자 올라갈때 나오는 경로들 싹 모아서 최소 최대값 출력하기

 */
import java.util.*;
import java.io.*;
public class Main{
    static int N,K;
    static class Info{
        int dest;
        long cost;
        Info(int d, long c){
            this.dest = d;
            this.cost = c;
        }
    }
    static int LOG = 1;
    static int[][] parent;
    static long[][] minCost;
    static long[][] maxCost;
    // 노드별 깊이
    static int[] depth;
    static ArrayList<Info>[] board;
    static boolean[] visited;
    static final long INF = Long.MAX_VALUE;
    static void dfs(int cur){
        visited[cur] = true;
        for(Info n : board[cur]){
            if(!visited[n.dest]){
                parent[n.dest][0] = cur;
                minCost[n.dest][0] = n.cost;
                maxCost[n.dest][0] = n.cost;
                depth[n.dest] = depth[cur]+1;
                dfs(n.dest);
            }
        }
    }

    static void binarylifting(){
        for(int k = 1; k<LOG; k++){
            for(int v = 1; v<=N; v++){
                int mid = parent[v][k-1];
                parent[v][k] = parent[mid][k-1];
                minCost[v][k] = Math.min(minCost[v][k-1], minCost[mid][k-1]);
                maxCost[v][k] = Math.max(maxCost[v][k-1], maxCost[mid][k-1]);
            }
        }
    }

    static long[] lca(int a, int b){
        long min = INF;
        long max = 0;

        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int k = LOG-1; k>=0; k--){
            if(depth[a] - (1<<k) >= depth[b]){
                min = Math.min(min, minCost[a][k]);
                max = Math.max(max, maxCost[a][k]);
                a = parent[a][k];
            }
        }

        if(a == b)
            return new long[]{min, max};

        for(int k = LOG-1; k>=0; k--){
            if(parent[a][k] != parent[b][k]){
                min = Math.min(min, Math.min(minCost[a][k], minCost[b][k]));
                max = Math.max(max, Math.max(maxCost[a][k], maxCost[b][k]));
                a = parent[a][k];
                b = parent[b][k];
            }
        }

        min = Math.min(min, Math.min(minCost[a][0], minCost[b][0]));
        max = Math.max(max, Math.max(maxCost[a][0], maxCost[b][0]));

        return new long[]{min, max};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while((1<<LOG) <= N)
            LOG++;
        visited = new boolean[N+1];
        parent = new int[N+1][LOG];
        minCost = new long[N+1][LOG];
        maxCost = new long[N+1][LOG];
        depth = new int[N+1];
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int i = 1; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            board[a].add(new Info(b,c));
            board[b].add(new Info(a,c));
        }

        depth[1] = 0;
        parent[1][0] = 0;
        minCost[1][0] = INF;
        maxCost[1][0] = 0;

        dfs(1);
        binarylifting();

        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        while(K-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            long[] rst = lca(d,e);
            sb.append(rst[0]).append(" ").append(rst[1]).append("\n");
        }
        System.out.println(sb);
    }
}
