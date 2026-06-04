import java.util.*;
import java.io.*;

/*
2차원 슬라이딩 윈도
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] board = new int[N+1][N+1];
        int[][] psum = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                board[i][j] = read();
            }
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                psum[i][j] = psum[i-1][j] + psum[i][j-1] - psum[i-1][j-1] + board[i][j];
            }
        }

        int cur = psum[3][3];
        int ans = Math.max(0, cur);
        for(int i = 3; i<=N; i++){
            for(int j= 3; j<=N; j++){
                cur = psum[i][j] - psum[i-3][j] - psum[i][j-3] + psum[i-3][j-3];
                ans = Math.max(ans, cur);
            }
        }
        System.out.print(ans);
    }
}