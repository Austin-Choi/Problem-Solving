import java.util.*;
import java.io.*;

/*
터짐을 반복한다는게 4방향 분열을 계속한다는거겟지??
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
    static int N,M,si,sj;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        si = read()-1;
        sj = read()-1;
        board = new int[N][N];

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj});
        board[si][sj] = 1;

        for(int ct = 1; ct<=M; ct++){
            int size = q.size();
            for(int n = 0; n<size; n++){
                int[] cur = q.poll();
                int ci = cur[0];
                int cj = cur[1];
                
                int nl = 1<<(ct-1);
                for(int d = 0; d<4; d++){
                    int ni = ci + nl*di[d];
                    int nj = cj + nl*dj[d];
                    if(ni < 0 || nj < 0 || ni >= N || nj >= N || board[ni][nj] == 1)
                        continue;
                    board[ni][nj] = 1;
                    q.add(new int[]{ni,nj});
                }
                // 원시 폭탄이 유지되고 t의 변화에 따라 새로운 위치에 폭탄 생성
                q.add(cur);
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                ans += board[i][j];
            }
        }
        System.out.print(ans);
    }
}