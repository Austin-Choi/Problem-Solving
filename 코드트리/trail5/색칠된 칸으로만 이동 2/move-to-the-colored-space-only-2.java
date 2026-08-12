import java.util.*;
import java.io.*;

/*
칸에 존재하는 최대값 - 최솟값 = r
mid 값으로 모든 1 칸 탐색가능한지 
아무 1칸에서 시작해서 1칸 만날때마다 count++
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // 북동남서
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int[][][] board;
    // [M][N]
    static int M,N;
    static int si = -1;
    static int sj = -1;
    static int ones = 0;

    static boolean can(int X){
        boolean[][] v = new boolean[M][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si, sj});
        v[si][sj] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            if(board[ci][cj][1] == 1)
                cnt++;
            
            if(cnt == ones)
                return true;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= M || nj >= N)
                    continue;
                if(v[ni][nj])
                    continue;
                if(Math.abs(board[ci][cj][0] - board[ni][nj][0]) <= X){
                    v[ni][nj] = true;
                    q.add(new int[]{ni, nj});
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException{
        M = read();
        N = read();
        board = new int[M][N][2];
        int min = 100_000_001;
        int max = 0;

        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                board[i][j][0] = read();
                min = Math.min(min, board[i][j][0]);
                max = Math.max(max, board[i][j][0]);
            }
        }
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                board[i][j][1] = read();
                if(board[i][j][1] == 1){
                    ones++;
                    if(si == -1 && sj == -1){
                        si = i;
                        sj = j;
                    }
                }
            }
        }

        int l = 0;
        int r = max-min;
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid+1;
        }
        System.out.print(ans);
    }
}