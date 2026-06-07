import java.util.*;
import java.io.*;

/*
di[2][4]
dj[2][4]
로 반시계 방항 [0], 시계방향[1] 나타내고
prev = 시작점

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static int[][] di = {{-1,-1,1,1},{-1,-1,1,1}};
    static int[][] dj = {{1,-1,-1,1},{-1,1,1,-1}};

    // 
    //
    //   1
    static void rotate(int si, int sj, int m1, int m2, int m3, int m4, int dir){
        int[] len;
        if(dir == 0)
            len = new int[] {m1,m2,m3,m4};
        else
            len = new int[] {m4,m3,m2,m1};

        int start = board[si][sj];
        int prev = board[si][sj];

        int ci = si;
        int cj = sj;
        for(int d = 0; d<4; d++){
            for(int n = 0; n<len[d]; n++){
                ci += di[dir][d];
                cj += dj[dir][d];
                int t = board[ci][cj];
                board[ci][cj] = prev;
                prev = t;
            }
        }
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }
        rotate(read()-1,read()-1,read(),read(),read(),read(),read());
        print();
    }
}