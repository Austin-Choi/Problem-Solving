/*
bfs를 돌리는데 lit -> 불킴 2차원 배열
visited -> 방문 2차원 배열

일단 들어가면 현재 board에 있는 거 lit이 false일때만 lit = true 하고 ans ++ 함
그리고 새로 켠 방에 인접한 동서남북이 visit == true면 방문 가능한곳이니까 큐에 넣음
그리고 인접한 방에 이동함 (lit == true && visit == false)
 */
import java.awt.*;
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static boolean[][] toVisit;
    static ArrayList<Point>[][] board;
    static boolean[][] lit;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static int ans;
    static void bfs(){
        toVisit = new boolean[N][N];
        lit = new boolean[N][N];
        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(0,0));
        toVisit[0][0] = true;
        lit[0][0] = true;
        ans = 1;

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            // 불 켬
            // -> 불 들어온 리스트 중에 이미 탐색한 칸에서 갈 수 있는애면
            // 걔도 큐에 넣음
            for(Point p : board[ci][cj]){
                // 여러개의 칸에서 트리거할수 있으니까 중복체크해야함
                if(!lit[p.x][p.y]){
                    lit[p.x][p.y] = true;
                    ans++;

                    // 새로 켠 방 p가 인접하면 큐에 넣기
                    for(int d = 0; d<4; d++){
                        int ni = p.x + di[d];
                        int nj = p.y + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        if(toVisit[ni][nj]){
                            toVisit[p.x][p.y] = true;
                            q.add(new Point(p.x, p.y));
                            break;
                        }
                    }
                }
            }

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                // 아직 방문 안한 곳에서
                // 불 켜진 곳중에
                if(!toVisit[ni][nj] && lit[ni][nj]) {
                    toVisit[ni][nj] = true;
                    q.add(new Point(ni,nj));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = new ArrayList<>();
            }
        }
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken());
            int sj = Integer.parseInt(st.nextToken());
            int ei = Integer.parseInt(st.nextToken());
            int ej = Integer.parseInt(st.nextToken());
            board[si-1][sj-1].add(new Point(ei-1, ej-1));
        }

        bfs();
        System.out.println(ans);
    }
}
