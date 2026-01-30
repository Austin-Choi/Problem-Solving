/*
바이러스 최대 갯수 M개
크기 N*N
바이러스 pos저장하는 배열 만들어서 저장하고
최대 M개 선택하는 dfs만들어서
멀티소스 bfs
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[][] board;
    static ArrayList<int[]> pool;
    static ArrayList<int[]> starts = new ArrayList<>();
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static final int INF = 50*50+1;
    static int ans = INF;
    static void dfs(int idx, int depth){
        if(depth == M){
            int t = bfs();
            if(t != -1)
                ans = Math.min(ans, t);
            return;
        }

        if(idx == pool.size())
            return;

        starts.add(pool.get(idx));
        dfs(idx+1, depth + 1);
        starts.remove(starts.size()-1);
        dfs(idx+1, depth);
    }
    static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }

        for(int[] s : starts){
            dist[s[0]][s[1]] = 0;
            q.add(new int[]{s[0], s[1], 0});
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cc = cur[2];

            if(dist[ci][cj] != cc)
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni <0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(board[ni][nj] == 1)
                    continue;

                if(dist[ni][nj] > cc+1){
                    dist[ni][nj] = cc + 1;
                    q.add(new int[]{ni,nj,cc+1});
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] != 1){
                    if(dist[i][j] == INF)
                        return -1;
                    if(ans < dist[i][j]){
                        ans = dist[i][j];
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        pool = new ArrayList<>();
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
                if(cur == 2){
                    pool.add(new int[]{i,j});
                }
            }
        }
        dfs(0,0);
        if(ans == INF)
            System.out.print(-1);
        else
            System.out.print(ans);
    }
}
