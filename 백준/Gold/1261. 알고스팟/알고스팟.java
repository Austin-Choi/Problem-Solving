import java.awt.Point;
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static boolean[][] board;
    static int INF;
    static int[][] dist;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static void bfs(){
        dist[0][0] = 0;
        Deque<Point> dq = new ArrayDeque<>();
        dq.addLast(new Point(0,0));

        while(!dq.isEmpty()){
            Point cur = dq.pollFirst();
            int ci = cur.x;
            int cj = cur.y;

            for(int d=0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;

                int w;
                if(board[ni][nj])
                    w = 1;
                else
                    w = 0;

                if(dist[ni][nj] > dist[ci][cj] + w){
                    dist[ni][nj] = dist[ci][cj] + w;
                    if(w == 1)
                        dq.addLast(new Point(ni,nj));
                    else
                        dq.addFirst(new Point(ni,nj));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        INF = N*M + 1;
        board = new boolean[N][M];
        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                if(temp[j] == '1')
                    board[i][j] = true;
            }
        }

        dist = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }
        bfs();

        System.out.println(dist[N-1][M-1]);
    }
}
