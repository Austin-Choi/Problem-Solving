/*
LCA + 누적 거리 문제
두 노드의 가장 가까운 공통 조상을 말함
1) 트리 구성
2) DFS 돌면서
각 노드의 깊이, 루트부터 해당 노드까지의 누적 거리, 부모 정보 저장
3) LCA 전처리 (Binary lifting)
각 노드의 2^K번째 조상을 저장 (최대 log N 만큼)
4) 각 쿼리에서 두 노드의 LCA를 찾고
dist[u] + dist[v] - 2 * dist [ lca (u,v) ]
-> 루트부터 공통 조상까지는 같은 경로로 오기 때문에 (중복 제거용)

트리는 어느 정점을 루트로 잡아도 잘 구성되므로
1번 ㄱㄱ

dist 1차원 배열인건
루트부터 node까지의 누적 거리이고
아까 말한 4번에서의 원리를 이용하면 2차원 배열 안 써도 거리 계산 가능
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static ArrayList<Node>[] board;
    static int[] depth;
    static int[][] parent;
    static int[] dist;

    static int MAXK;
    static class Node{
        int to;
        int weight;

        Node(int to, int w){
            this.to = to;
            this.weight = w;
        }
    }
    static void dfs(int cur, int from, int deep, int distance){
        depth[cur] = deep;
        parent[cur][0] = from;
        dist[cur] = distance;

        for(Node next : board[cur]){
            if(next.to != from)
                dfs(next.to, cur, deep+1, distance + next.weight);
        }
    }

    static int lca(int u, int v){
        // 깊이 맞추기
        if(depth[u] < depth[v]){
            int temp = u;
            u = v;
            v = temp;
        }

        for(int k = MAXK-1; k>=0; k--){
            if(depth[u] - (1<<k) >= depth[v])
                u = parent[u][k];
        }

        // 이미 같으면 공통 조상
        if(u==v)
            return u;

        // 위로 올리기
        for(int k = MAXK-1; k>=0; k--){
            if(parent[u][k] != 0 && parent[u][k] != parent[v][k]){
                u = parent[u][k];
                v = parent[v][k];
            }
        }

        // 바로 위가 공통 조상
        return parent[u][0];
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int i = 0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            board[s].add(new Node(e,w));
            board[e].add(new Node(s,w));
        }

        // Binary Lifting에서 필요한 최대 점프
        // => 2^k <=N 인 가장 큰 K
        MAXK = (int) (Math.log(N) / Math.log(2))+1;
        depth = new int[N+1];
        parent = new int[N+1][MAXK];
        dist = new int[N+1];
        // 트리 연결 후 거리 계산(전처리)
        dfs(1,0,0,0);

        // Binary Lifting 테이블 만들기
        // parent[node][k]

        for(int k = 1; k < MAXK; k++){
            for(int i = 1; i<=N; i++){
                if(parent[i][k-1] != 0)
                    parent[i][k] = parent[parent[i][k-1]][k-1];
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int lcaSE = lca(s,e);
            int ans = dist[s] + dist[e] - 2*dist[lcaSE];
            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
