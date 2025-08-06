import java.awt.Point;
import java.io.*;
import java.util.*;
public class Main {
    static int R,C;
    static int maxLimit;
    static int[][] distj;
    static int[][] distf;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static char[][] board;
    static List<Point> firestart = new ArrayList<>();
    static Point jihoon;
    static void bfsFire(){
        Queue<Point> q = new ArrayDeque<>();
        distf = new int[R][C];
        for(int i = 0; i<R; i++){
            Arrays.fill(distf[i], maxLimit);
        }
        for(Point p : firestart){
            q.add(p);
            distf[p.x][p.y] = 0;
        }

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci+di[d];
                int nj = cj+dj[d];
                if(ni < 0 || nj < 0 || ni >= R || nj >= C)
                    continue;
                if(board[ni][nj] == '#') continue;
                if(distf[ni][nj] == maxLimit) {
                    distf[ni][nj] = distf[ci][cj] + 1;
                    q.add(new Point(ni,nj));
                }
            }
        }
    }
    static int bfsJihoon(){
        Queue<Point> q = new ArrayDeque<>();
        distj = new int[R][C];
        for(int i = 0; i<R; i++){
            Arrays.fill(distj[i], maxLimit);
        }
        q.add(jihoon);
        distj[jihoon.x][jihoon.y] = 0;

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci+di[d];
                int nj = cj+dj[d];
                if(ni < 0 || nj < 0 || ni >= R || nj >= C){
                    return distj[ci][cj] + 1;
                }
                if(board[ni][nj] == '#')
                    continue;
                if(distj[ni][nj] != maxLimit)
                    continue;

                // 불보다 먼저 갈 수 있어야함
                if(distj[ci][cj] + 1 >= distf[ni][nj])
                    continue;

                distj[ni][nj] = distj[ci][cj] + 1;
                q.add(new Point(ni,nj));
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maxLimit = R*C + 1;
        board = new char[R][C];

        // 가장자리
        // -> i==0 j any
        // -> i any j==0
        // -> i==R-1 j any
        // -> i any j==C-1
        for(int i = 0; i<R; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<C; j++){
                board[i][j] = temp[j];
                if(board[i][j] == 'J'){
                    jihoon = new Point(i,j);
                }
                else if(board[i][j] == 'F'){
                    firestart.add(new Point(i,j));
                }
            }
        }

        bfsFire();
        int escapeTime = bfsJihoon();
        if(escapeTime == -1)
            bw.write("IMPOSSIBLE");
        else
            bw.write(String.valueOf(escapeTime));

        bw.flush();
        bw.close();
        br.close();
    }
}
