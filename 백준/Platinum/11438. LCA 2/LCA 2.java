/*
그냥 bfs로 연결된 정점 따라서 parent 채우기
up[k][v] = v의 2^k만큼 위의 부모 저장

두 노드 중 깊은쪽을 깊이 차만큼 이진점프시키고
같으면 한쪽이 다른쪽의 LCA임
다르면 둘 다 up ka != up kb 일때
이진점프하면서 공통조상 찾기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static ArrayList<Integer>[] board;
    static int[] parent;
    static int[] depth;
    static int LOG;

    static int[][] up;
    static void bfs(){
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);

        parent = new int[N+1];
        Arrays.fill(parent, -1);

        depth = new int[N+1];
        parent[1] = 0;
        depth[1] = 0;

        while(!q.isEmpty()){
            int v = q.poll();
            for(int next : board[v]){
                if(parent[v] == next)
                    continue;
                parent[next] = v;
                depth[next] = depth[v] + 1;
                q.add(next);
            }
        }
    }

    static void binarylifting(){
        LOG = (int) (Math.log(N) / Math.log(2)) + 1;

        up = new int[LOG][N+1];

        for(int i = 1; i<=N; i++){
            up[0][i] = parent[i];
        }

        for(int k = 1; k<LOG; k++){
            for(int v = 1; v<=N; v++){
                up[k][v] = up[k-1][up[k-1][v]];
            }
        }
    }

    static int sol(int a, int b){
        if(depth[a] < depth[b]){
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];
        for(int k = LOG-1; k>=0; k--){
            if(diff >= (1<<k)){
                diff -= (1<<k);
                a = up[k][a];
            }
        }

        if(a == b)
            return a;

        for(int k = LOG -1; k>=0; k--){
            if(up[k][a] != up[k][b]){
                a = up[k][a];
                b = up[k][b];
            }
        }

        return parent[a];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new ArrayList[N+1];
        for(int i = 1; i<N+1; i++){
            board[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s].add(e);
            board[e].add(s);
        }

        bfs();
        binarylifting();

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(sol(a,b)).append("\n");
        }
        System.out.println(sb);
    }
}
