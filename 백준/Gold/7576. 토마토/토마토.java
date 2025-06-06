
import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    private static int N;
    private static int M;

    private static int[] di = {1, -1, 0, 0};
    private static int[] dj = {0, 0, 1, -1};

    private static int[][] board;
    private static int[][] dist;
    private static boolean[][] visited;

    private static void bfs(Queue<Point> q){
        while(!q.isEmpty()){
            Point curPoint = q.poll();
            int curI = curPoint.x;
            int curJ = curPoint.y;

            for(int i = 0; i<4; i++){
                int nextI = curI+di[i];
                int nextJ = curJ+dj[i];
                if(nextI > -1 && nextJ > -1 && nextI < N && nextJ < M
                    && board[nextI][nextJ] != -1){
                    if(!visited[nextI][nextJ]){
                        visited[nextI][nextJ] = true;
                        dist[nextI][nextJ] = dist[curI][curJ] + 1;
                        q.add(new Point(nextI, nextJ));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dist = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], -1);
        }
        visited = new boolean[N][M];

        Queue<Point> q = new ArrayDeque<>();
        /*
        1 = 익은 토마토
        0 = 익지 않은 토마토
        -1 = 빈칸
         */
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                int tomato = Integer.parseInt(st.nextToken());
                board[i][j] = tomato;
                if(tomato == 1){
                    q.add(new Point(i,j));
                    dist[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }

        // 다중 시작점 bfs는 큐에 미리 시작점들을 넣어놓고 돌림
        // q를 매개변수로 전달하기
        bfs(q);

        boolean flag = false;
        int ans = -1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] == 0 && dist[i][j] == -1)
                    flag = true;
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                ans = Math.max(ans, dist[i][j]);
            }
        }

        if(flag)
            ans = -1;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
