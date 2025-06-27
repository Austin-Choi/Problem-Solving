import java.awt.Point;
import java.util.*;
import java.io.*;

class Info{
    Point p;
    int horse;

    public Info(Point p, int h){
        this.p = p;
        this.horse = h;
    }
}

public class Main{
    private static int[] di = {0,0,1,-1};
    private static int[] dj = {1,-1,0,0};
    private static int[] hi = {1, 2, 2, 1, -1, -2, -2, -1};
    private static int[] hj = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int K;
    private static int W;
    private static int H;
    private static int[][] board;
    private static int bfs(){
        Queue<Info> q = new ArrayDeque<>();
        q.add(new Info(new Point(0,0), 0));
        // 말처럼 뛴 수를 고려하고
        // 한번도 안 했을수 있어서 K+1
        int[][][] dist = new int[H][W][K+1];
        for(int i = 0; i<H; i++){
            for(int j = 0; j<W; j++){
                Arrays.fill(dist[i][j], -1);
            }
        }
        dist[0][0][0] = 0;

        while(!q.isEmpty()){
            Info cur = q.poll();
            int cI = cur.p.x;
            int cJ = cur.p.y;
            int horse = cur.horse;

            for(int i = 0; i<4; i++){
                int nI = cI + di[i];
                int nJ = cJ + dj[i];
                if(nI > -1 && nJ > -1 && nI < H && nJ < W && board[nI][nJ] != 1){
                    if(dist[nI][nJ][horse] == -1) {
                        dist[nI][nJ][horse] = dist[cI][cJ][horse] + 1;
                        q.add(new Info(new Point(nI, nJ), horse));
                    }
                }
            }

            if(horse < K) {
                for (int i = 0; i < 8; i++) {
                    int nI = cI + hi[i];
                    int nJ = cJ + hj[i];
                    if (nI > -1 && nJ > -1 && nI < H && nJ < W && board[nI][nJ] != 1) {
                        if (dist[nI][nJ][horse+1] == -1) {
                            dist[nI][nJ][horse + 1] = dist[cI][cJ][horse] + 1;
                            q.add(new Info(new Point(nI, nJ), horse + 1));
                        }
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<=K; i++){
            if(dist[H-1][W-1][i] == -1)
                continue;
            if(dist[H-1][W-1][i] < ans)
                ans = dist[H-1][W-1][i];
        }
        if(ans == Integer.MAX_VALUE)
            return -1;
        else
            return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];

        for(int i = 0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<W; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dist = bfs();

        bw.write(String.valueOf(dist));
        bw.flush();
        bw.close();
        br.close();
    }
}
