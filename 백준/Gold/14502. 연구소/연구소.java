
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    private static int[] di = {0,0,1,-1};
    private static int[] dj = {1,-1,0,0};
    private static int[][] board;
    private static int N;
    private static int M;
    private static ArrayList<Point> el = new ArrayList<>();
    private static int ans = 0;
    private static void dfs(int depth, int start){
        if(depth == 3){
            bfs();
            return;
        }

        for(int i = start; i<el.size(); i++){
            Point cur = el.get(i);
            board[cur.x][cur.y] = 1;
            dfs(depth+1, i+1);
            board[cur.x][cur.y] = 0;
        }
    }

    private static void bfs(){
        int[][] copy = new int[N][M];
        for(int i = 0; i<N; i++){
            copy[i] = Arrays.copyOf(board[i], M);
        }

        Queue<Point> q2 = new ArrayDeque<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(copy[i][j] == 2){
                    q2.add(new Point(i, j));
                }
            }
        }

        while(!q2.isEmpty()){
            Point cur = q2.poll();
            int curI = cur.x;
            int curJ = cur.y;

            for(int i = 0; i<4; i++){
                int nextI = curI + di[i];
                int nextJ = curJ + dj[i];

                if(nextJ > -1 && nextJ < M && nextI > -1 && nextI < N){
                    if(copy[nextI][nextJ] == 0){
                        copy[nextI][nextJ] = 2;
                        q2.add(new Point(nextI, nextJ));
                    }
                }
            }
        }

        int sum = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(copy[i][j] == 0)
                    sum++;
            }
        }

        ans = Math.max(ans, sum);
    }
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == 0)
                    el.add(new Point(i,j));
                board[i][j] = tmp;
            }
        }

        dfs(0,0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
