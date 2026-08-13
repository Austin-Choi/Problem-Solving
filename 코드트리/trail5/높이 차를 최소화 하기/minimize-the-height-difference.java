import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[][] board;
    // 북동남서
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int[][] v;
    static int gmin = 501;
    static int gmax = 0;
    static int visitId = 0;

    static boolean can(int X){
        for(int low = gmin; low <= gmax; low++){
            int high = low + X;

            if(board[0][0] < low || board[0][0] > high)
                continue;

            int id = ++visitId;

            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{0, 0});
            v[0][0] = id;

            while(!q.isEmpty()){
                int[] cur = q.poll();

                int ci = cur[0];
                int cj = cur[1];

                if(ci == N-1 && cj == M-1)
                    return true;

                for(int d = 0; d < 4; d++){
                    int ni = ci + di[d];
                    int nj = cj + dj[d];

                    if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                        continue;

                    if(v[ni][nj] == id)
                        continue;

                    if(board[ni][nj] < low || board[ni][nj] > high)
                        continue;

                    v[ni][nj] = id;
                    q.add(new int[]{ni, nj});
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][M];
        v = new int[N][M];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
                gmin = Math.min(gmin, board[i][j]);
                gmax = Math.max(gmax, board[i][j]);
            }
        }

        int l = 0;
        int r = gmax - gmin;
        int ans = 0;

        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid + 1;
        }
        System.out.print(ans);
    }
}