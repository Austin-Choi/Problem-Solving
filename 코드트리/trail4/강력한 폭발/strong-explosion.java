import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static ArrayList<int[]> li = new ArrayList<>();

    static int[][][] bomb = {
        {{-1,0},{-2,0},{1,0},{2,0}},
        {{-1,0},{1,0},{0,1},{0,-1}},
        {{-1,-1},{-1,1},{1,1},{1,-1}}
    };

    static int ans = 0;

    static void bt(int len, int[][] board, int depth, int cnt){
        if(depth == len){
            ans = Math.max(ans, cnt);
            return;
        }

        int ci = li.get(depth)[0];
        int cj = li.get(depth)[1];

        for(int b = 0; b<3; b++){
            int cc = 0;
            if(board[ci][cj] == 0)
                cc++;
            // 폭탄 종류마다 복사
            int[][] copy = new int[N][N];
            for(int i =0; i<N; i++){
                for(int j = 0; j<N; j++){
                    copy[i][j] = board[i][j];
                }
            }

            for(int i = 0; i<4; i++){
                int ni = ci + bomb[b][i][0];
                int nj = cj + bomb[b][i][1];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(copy[ni][nj] == 0){
                    cc++;
                    copy[ni][nj] = 1;
                }
            }

            bt(len, copy, depth+1, cnt + cc);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
                if(board[i][j] == 1)
                    li.add(new int[]{i,j});
            }
        }
        bt(li.size(), board, 0, 0);
        System.out.print(ans+li.size());
    }
}