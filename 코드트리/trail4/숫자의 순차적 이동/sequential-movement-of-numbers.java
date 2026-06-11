import java.util.*;
import java.io.*;

/*
15 13  1 11
 4  8  3  5
 2 12 16  7
14  6  9 10

15  1 13 11
 4  8  3  5
 2 12 16  7
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static int[][] board;
    static SortedMap<Integer, int[]> toMove;
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};

    static void move(){
        for(int k : toMove.keySet()){
            int ci = toMove.get(k)[0];
            int cj = toMove.get(k)[1];

            int max = 0;
            int mi = -1;
            int mj = -1;
            for(int d = 0; d<8; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(board[ni][nj] > max){
                    max = board[ni][nj];
                    mi = ni;
                    mj = nj;
                }
            }

            int t = board[ci][cj];
            board[ci][cj] = board[mi][mj];
            board[mi][mj] = t;

            // 원인 : 한번 스왑할때마다 바로 tomove에서 위치 스왑해줘야함.
            toMove.put(max, new int[]{ci,cj});
            toMove.put(t, new int[]{mi,mj});
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][N];
        // [n][i,j]= 숫자 n의 좌표 위치 i,j 저장
        toMove = new TreeMap<>();

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int cn = read();
                board[i][j] = cn;
                toMove.put(cn, new int[]{i,j});
            }
        }

        for(int t = 0; t<M; t++){
            move();
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}