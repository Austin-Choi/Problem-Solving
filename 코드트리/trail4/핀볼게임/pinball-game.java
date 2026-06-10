import java.util.*;
import java.io.*;

/*
북동남서 
1 / 2 \

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    /*
    type = 1 -> /
    2 -> \
    (들어오는 방향, board[ni][nj])
    return 바뀐 방향
    */
    static int changeDir(int dir, int type){
        if(type == 2){
            if(dir == 0)
                return 3;
            if(dir == 1)
                return 2;
            if(dir == 2)
                return 1;
            return 0;
        }
        else if(type == 1){
            if(dir == 0)
                return 1;
            if(dir == 1)
                return 0;
            if(dir == 2)
                return 3;
            return 2;
        }
        else
            return dir;
    }

    static int ans = 0;
    static void dfs(int ci, int cj, int dir, int len){
        if(ci < 0 || cj < 0 || ci >= N || cj >= N){
            ans = Math.max(ans, len+1);
            return;
        }

        int nd = changeDir(dir, board[ci][cj]);
        int ni = ci + di[nd];
        int nj = cj + dj[nd];
        dfs(ni, nj, nd, len+1);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j= 0; j<N; j++){
                board[i][j] = read();
            }
        }

        /*
        입력할때
          2
        1  3
          0
        2->3->0->1
        */
        // si, sj, dir, len = 0
        for(int j = 0; j<N; j++){
            dfs(0, j, 2, 0);
        }
        for(int i = 0; i<N; i++){
            dfs(i, N-1, 3, 0);
        }
        for(int j = 0; j<N; j++){
            dfs(N-1, j, 0, 0);
        }
        for(int i = 0; i<N; i++){
            dfs(i, 0, 1, 0);
        }
        System.out.print(ans);
    }
}