
/*
각자의 섬 한 뭉텅이에서 bfs로 뻗어나가야 함

1) ids[][] 배열을 만들어서 id를 붙여줌
-> 아직 id를 붙이지 않은 상태여야 하고 board에서 1이어야 함
-> bfsID를 돌때 시작점에도 id를 먼저 넣고 시작

2) 1부터 id-1까지 다른 id를 만날때까지 bfs를 돌려줌
ni nj 계산할때 id가 0이 아니고 기준id와 다른 경우 리턴하는데
현재 칸이 바다일때 현재 칸의 dist를 리턴하면
다른 대륙을 잇는 최소 다리 길이임

3) 모든 섬에 대해서 돌려주고 그중 최소 다리 값을 찾아서 리턴함
 */

import java.util.*;
import java.io.*;
import java.awt.Point;
public class Main {
    static int N;
    static int[][] board;
    // 섬 id 매기기
    static int[][] ids;
    // 다리 bfs 시작용 q
    static Queue<Point>[] starts;
    static int[][] dist;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    // 다른 id를 만날때의 최소 dist를 반환하기
    static int bfs(int id){
        dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], 20000);
        }
        Queue<Point> q = new ArrayDeque<>();
        for(Point p : starts[id]){
            q.add(p);
            dist[p.x][p.y] = 0;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(ids[ni][nj] != 0 && ids[ni][nj] != id){
                    if (board[ci][cj] == 0)
                        return dist[ci][cj];
                }

                if(board[ni][nj] == 0 && dist[ni][nj] > dist[ci][cj] + 1){
                    dist[ni][nj] = dist[ci][cj] + 1;
                    q.add(new Point(ni, nj));
                }
            }
        }
        return 0;
    }

    static void bfsID(int si, int sj, int id){
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(si,sj));
        ids[si][sj] = id;

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;

                if(board[ni][nj] == 1 && ids[ni][nj] == 0){
                    ids[ni][nj] = id;
                    q.add(new Point(ni, nj));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        ids = new int[N][N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int id = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(ids[i][j] == 0 && board[i][j] != 0)
                    bfsID(i,j,id++);
            }
        }
        starts = new Queue[id+1];
        for(int i = 1; i<starts.length; i++){
            starts[i] = new ArrayDeque<>();
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(ids[i][j] == 0)
                    continue;
                // 경계면만 start큐에 넣기
                for(int d = 0; d<4; d++){
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                        continue;
                    if(ids[ni][nj] == 0){
                        starts[ids[i][j]].add(new Point(i,j));
                        break;
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<id; i++){
            ans = Math.min(ans, bfs(i));
        }
        System.out.println(ans);
    }
}
