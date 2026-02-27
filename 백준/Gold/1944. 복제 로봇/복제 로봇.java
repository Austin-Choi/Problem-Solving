/*
k위치 List<int[]>로 저장하고
bfs돌려서 K 발견할때마다 q에 ki,kj,dist[ki][kj]로 삽입하고
dist[i][j] = i,j칸에 도착하는 최소 시간
list에 저장된 k다 돌면서 하나라도 불가능한 값이면 -1
아니라면 그중 최대값

근데 어차피 K에 가야하고 거기서부터 또다른 열쇠를 찾아서 bfs를 하는 형태인데
그러면 board에서 방문한 K를 0으로 바꾸고 또 돌고 뭐 이런식으로
백트래킹하는 형태를 생각해봄
근데 그러기엔 M의 최대값이 250이라 백트래킹하면 터질거같고

아니면 S와K를 모두 연결하는 완전그래프를 만들어서
크루스칼로 MST를 구해서 그 MST의 길이 구하기?
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int[][] board;
    static int N,M;
    static ArrayList<int[]> g;
    static int root = 2;
    static int ri, rj;
    // 특수노드 위치
    static ArrayList<int[]> li;
    /*
    모든 특수 노드들마다 BFS진행
    할때마다 하나라도 갱신안되면 -1
    최단거리 bfs이고 1이니까 visited + dist
     */
    static boolean make(int si, int sj, int snum){
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj,0});
        visited[si][sj] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            if(cnt == li.size()-1)
                break;

            for(int d=0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N || board[ni][nj] == 1)
                    continue;
                if(!visited[ni][nj]){
                    visited[ni][nj] = true;
                    q.add(new int[]{ni,nj,cd+1});
                    if(board[ni][nj] != 0){
                        int v = board[ni][nj]-2;
                        if(snum < v)
                            g.add(new int[]{snum, v, cd + 1});
                        cnt++;
                    }
                }
            }
        }

        for(int[] ii : li){
            if(!visited[ii[0]][ii[1]])
                return false;
        }
        return true;
    }
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py)
            return;
        parent[py] = px;
    }

    static int mst(){
        parent = new int[root-2];
        for(int i = 0; i<root-2; i++){
            parent[i] = i;
        }

        int tot = 0;
        int cnt = 0;
        for(int[] e : g){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            if(find(u) != find(v)){
                union(u,v);
                tot += w;
                cnt++;
            }
        }

        if(cnt != root -2 - 1)
            return -1;
        return tot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        li = new ArrayList<>();
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j= 0; j<N; j++){
                char tt = t[j];
                // 아무 정점이나 root해도 됨
                if(tt != '1' && tt != '0'){
                    if(root == 2){
                        ri = i;
                        rj = j;
                    }
                    board[i][j] = root++;
                    li.add(new int[]{i,j});
                }
                else
                    board[i][j] = tt - '0';
            }
        }

        g = new ArrayList<>();
        // 완전그래프 구성
        for(int[] ii : li){
            if(!make(ii[0], ii[1], board[ii[0]][ii[1]]-2)){
                System.out.print(-1);
                return;
            }
        }

        // 크루스칼 써서 MST 구하기
        Collections.sort(g, Comparator.comparingInt(a->a[2]));
        System.out.print(mst());

    }
}
