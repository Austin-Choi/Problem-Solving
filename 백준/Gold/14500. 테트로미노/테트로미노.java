import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    private static int N;
    private static int M;

    private static int[] di = {0,0,1,-1};
    private static int[] dj = {1,-1,0,0};
    private static int maxSum = 0;
    private static int maxBNum = -1;
    private static boolean[][] visited;

    private static int[][] board;
    private static void dfs(Point p, int depth, int sum){
        // 정답조건
        if(depth == 4){
            maxSum = Math.max(sum, maxSum);
            return;
        }

        //가지치기
        if(sum + maxBNum * (4-depth) < maxSum)
            return;

        for(int n = 0; n<4; n++){
            int nextI = p.x + di[n];
            int nextJ = p.y + dj[n];
            if(nextI > -1 && nextJ > -1 && nextI < N && nextJ < M){
                if(!visited[nextI][nextJ]){
                    if(depth == 2){
                        visited[nextI][nextJ] = true;
                        dfs(new Point(p.x,p.y), depth+1, sum + board[nextI][nextJ]);
                        visited[nextI][nextJ] = false;
                    }
                    visited[nextI][nextJ] = true;
                    dfs(new Point(nextI,nextJ), depth+1, sum + board[nextI][nextJ]);
                    visited[nextI][nextJ] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        // 가지치기용 보드에서 가장 큰 값
        maxBNum = -1;
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                maxBNum = Math.max(board[i][j], maxBNum);
            }
        }

        for(int n = 0; n<N; n++){
            for(int m = 0; m<M; m++){
                visited[n][m] = true;
                dfs(new Point(n,m), 1,board[n][m]);
                visited[n][m] = false;
            }
        }

        bw.write(String.valueOf(maxSum));
        bw.flush();
        bw.close();
        br.close();
    }
}
