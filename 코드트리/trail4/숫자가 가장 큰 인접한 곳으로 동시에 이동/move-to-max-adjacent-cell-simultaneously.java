import java.util.*;
import java.io.*;

/*
값은 안변하니까 board로 입력받고
ball[][]로 1까지는 ㄱㅊ, 2이상부터는 충돌한거라 싹 사라져야함 
그냥 2 이상은 0으로 하기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,T;
    static int[][] ballBoard;
    static int[][] board;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static void move(){
        int[][] copyBall = new int[N][N];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                copyBall[i][j] = ballBoard[i][j];
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(ballBoard[i][j] == 1){
                    int max = 0;
                    int mi = -1;
                    int mj = -1;

                    for(int d = 0; d<4; d++){
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                            continue;
                        // 원인 : 문제에서 인접한 4개 값중 상하좌우로 찾았을때 최대값이어야함
                        // max = 0, di,dj 상하좌우로
                        if(board[ni][nj] > max){
                            max = board[ni][nj];
                            mi = ni;
                            mj = nj;
                        }
                    }

                    if(mi != -1){
                        copyBall[i][j]--;
                        copyBall[mi][mj]++;
                    }
                }
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(copyBall[i][j] > 1){
                    copyBall[i][j] = 0;
                }
            }
        }

        ballBoard = copyBall;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        T = read();
        ballBoard = new int[N][N];
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        while(M-->0){
            int i = read()-1;
            int j = read()-1;
            ballBoard[i][j] = 1;
        }

        for(int t = 0; t<T; t++){
            move();
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(ballBoard[i][j] == 1)
                    ans++;
            }
        }
        System.out.print(ans);
    }
}