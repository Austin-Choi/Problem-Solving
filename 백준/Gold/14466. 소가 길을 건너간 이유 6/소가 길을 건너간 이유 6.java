import java.awt.Point;
import java.util.*;
import java.io.*;
public class Main {
    static int N,K,R;
    static ArrayList<Point>[][] board;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static Point[] cows;

    static boolean[][] bfs(Point start){
        int si = start.x;
        int sj = start.y;
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][N+1];
        visited[si][sj] = true;
        q.add(start);

        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(ni < 1 || nj < 1 || ni > N || nj > N)
                    continue;

                boolean temp = false;
                for(Point p : board[ci][cj]){
                    if(p.x == ni && p.y == nj){
                        temp = true;
                        break;
                    }
                }
                if(temp)
                    continue;

                if(!visited[ni][nj]){
                    visited[ni][nj] = true;
                    q.add(new Point(ni,nj));
                }
            }
        }
        return visited;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1][N+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=N; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(int r= 0; r<R;r++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            board[r1][c1].add(new Point(r2,c2));
            board[r2][c2].add(new Point(r1,c1));
        }

        cows = new Point[K];
        for(int k = 0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            cows[k] = new Point(i,j);
        }

        int ans = 0;
        for(int i = 0; i<K; i++){
            boolean[][] visited = bfs(cows[i]);

            for(int j = i+1; j<K; j++){
                int ci = cows[j].x;
                int cj = cows[j].y;
                if(!visited[ci][cj])
                    ans++;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
