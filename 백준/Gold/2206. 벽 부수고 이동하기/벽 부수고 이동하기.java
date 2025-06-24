import java.util.*;
import java.io.*;

class Point{
    int x;
    int y;
    boolean isDestroyedWall;

    public Point(int x, int y, boolean isDestroyedWall){
        this.x = x;
        this.y = y;
        this.isDestroyedWall = isDestroyedWall;
    }
}

public class Main {
    private static int[] di = {0, 0, 1, -1};
    private static int[] dj = {1,-1,0,0};
    private static int N;
    private static int M;
    private static int[][] board;
    private static int ans = 1000001;
    private static int bfs(){
        Queue<Point> q = new ArrayDeque<>();
        int[][][] dist = new int[N][M][2];
        dist[0][0][0] = 1; // 벽 안부수고 시작함
        q.add(new Point(0,0, false));

        while(!q.isEmpty()){
            Point cur = q.poll();
            int curI = cur.x;
            int curJ = cur.y;
            boolean isDestroyed = cur.isDestroyedWall;
            // 부순게 1, 안부순게 0
            int wall = isDestroyed ? 1 : 0;

            for(int i = 0; i<4; i++){
                int nextI = curI + di[i];
                int nextJ = curJ + dj[i];

                if(nextI > -1 && nextI < N && nextJ > -1 && nextJ < M){
                    // 벽이 아닐때
                    if(board[nextI][nextJ] == 0 && dist[nextI][nextJ][wall] == 0){
                        dist[nextI][nextJ][wall] = dist[curI][curJ][wall]+1;
                        q.add(new Point(nextI, nextJ, isDestroyed));
                    }

                    // 벽인데 아직 안 부쉈을 때
                    // isDestroyed가 false이고
                    // dist[nI][nj][1]은 ni nj 좌표에서 벽이 부숴진 거리를 저장하는 것이므로
                    // 이게 0이면 안 부숴진 벽이므로 부수고 안 부쉈던 전 좌표의 값에서 1 더한거로 갱신
                    if(board[nextI][nextJ] == 1 && !isDestroyed && dist[nextI][nextJ][1] == 0){
                        dist[nextI][nextJ][1] = dist[curI][curJ][0] + 1;
                        q.add(new Point(nextI, nextJ, true));
                    }
                }
            }
        }

        int broke = dist[N-1][M-1][1];
        int notBroke = dist[N-1][M-1][0];

        if(broke == 0 && notBroke == 0)
            return -1;
        if(broke == 0)
            return notBroke;
        if(notBroke == 0)
            return broke;
        return Math.min(broke, notBroke);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for(int i = 0; i<N; i++){
            String[] tmps = br.readLine().split("");
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(tmps[j]);
            }
        }

        ans = bfs();

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
