import java.util.*;
import java.io.*;

class Info{
    public int i;
    public int j;
    public int k;

    Info(int i, int j, int k){
        this.i = i;
        this.j = j;
        this.k = k;
    }
}
public class Main {
    private static int M;
    private static int N;
    private static int H;

    private static int[] dk = {0,0, -1, 1, 0, 0};
    private static int[] dj = {-1, 1, 0,0,0,0};
    private static int[] di = {0,0,0,0,-1,1};

    private static int[][][] board;
    private static int[][][] dist;
    private static boolean[][][] visited;

    private static int maxDist = 0;

    private static void bfs(Queue<Info> q){
        while(!q.isEmpty()){
            Info info = q.poll();
            int curI = info.i;
            int curJ = info.j;
            int curK = info.k;

            for(int n= 0; n<6; n++){
                int nextI = curI + di[n];
                int nextJ = curJ + dj[n];
                int nextK = curK + dk[n];

                if(nextI > -1 && nextJ > -1 && nextK > -1
                    && nextI < H && nextJ < N && nextK < M
                    && board[nextI][nextJ][nextK] != -1){
                    if(!visited[nextI][nextJ][nextK]){
                        visited[nextI][nextJ][nextK] = true;
                        dist[nextI][nextJ][nextK] = dist[curI][curJ][curK] + 1;
                        maxDist = Math.max(dist[nextI][nextJ][nextK], maxDist);
                        q.add(new Info(nextI, nextJ, nextK));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][N][M];
        visited = new boolean[H][N][M];
        dist = new int[H][N][M];
        for(int h = 0; h<H; h++){
            for(int n = 0; n<N; n++){
                Arrays.fill(dist[h][n], -1);
            }
        }

        Queue<Info> q = new ArrayDeque<>();

        boolean alreadyRipeFlag = true;

        for(int i = H-1; i >=0; i--){
            for(int j = 0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k<M; k++){
                    int tomato = Integer.parseInt(st.nextToken());
                    board[i][j][k] = tomato;
                    if(tomato == 1){
                        q.add(new Info(i,j,k));
                        visited[i][j][k] = true;
                        dist[i][j][k] = 0;
                    }
                    if(tomato == 0)
                        alreadyRipeFlag = false;
                }
            }
        }

        bfs(q);

        boolean unripeFlag = false;
        for(int i = 0; i<H; i++){
            for(int j = 0; j<N; j++){
                for(int k = 0; k<M; k++){
                    if (board[i][j][k] == 0 && dist[i][j][k] == -1) {
                        unripeFlag = true;
                        break;
                    }
                }
            }
        }

        // 토마토가 모두 익지는 못하는 상태
        // board[i][j[k] == 0인데 dist[i][j][k] == -1인게 하나라도 있으면 해당
        if(unripeFlag)
            bw.write(String.valueOf(-1));
        // 저장될 때부터 모든 토마토가 익어있는 상태
        // -> 안익은 토마토가 입력된 적이 없음
        else if(alreadyRipeFlag)
            bw.write(String.valueOf(0));
        // 일반적인 상태 maxDist 출력
        else
            bw.write(String.valueOf(maxDist));
        bw.flush();
        bw.close();
        br.close();
    }
}
