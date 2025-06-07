
import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    private static int[] di = {0,0,1,-1};
    public static int[] dj = {1,-1,0,0};

    private static int N;
    private static boolean[][] visited;

    private static int countArea(int count, char[][] boardType){
        visited = new boolean[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(!visited[i][j]){
                    bfs(new Point(i,j), boardType);
                    count++;
                }
            }
        }
        return count;
    }

    // bfs, dfs는 연결된 곳을 탐색함
    private static void bfs(Point p, char[][] board){
        Queue<Point> q = new ArrayDeque<>();
        q.add(p);
        visited[p.x][p.y] = true;
        char color = board[p.x][p.y];

        while(!q.isEmpty()){
            Point curP = q.poll();
            int curI = curP.x;
            int curJ = curP.y;

            for(int n=0; n<4; n++){
                int nextI = curI + di[n];
                int nextJ = curJ + dj[n];
                if(nextI > -1 && nextJ > -1 && nextI < N && nextJ < N){
                    if(!visited[nextI][nextJ] && board[nextI][nextJ] == color){
                        visited[nextI][nextJ] = true;
                        q.add(new Point(nextI, nextJ));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        char[][] blindBoard = new char[N][N];

        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                board[i][j] = temp[j];
                blindBoard[i][j] = temp[j];
                if(temp[j] == 'G'){
                    blindBoard[i][j] = 'R';
                }
            }
        }

        sb.append(countArea(0,board)).append(" ");
        sb.append(countArea(0, blindBoard));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
