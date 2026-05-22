import java.util.*;
import java.io.*;

/*
dp[i][j] = i,j 칸까지 왔을때 최대 증가 수열의 크기

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[][] board;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N][M];
        dp = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j= 0; j<M; j++){
                board[i][j] = read();
            }
        }

        dp[0][0] = 1;

        for(int ai = 0; ai < N; ai++){
            for(int aj = 0; aj<M; aj++){
                if(dp[ai][aj] == 0)
                    continue;
                for(int bi = ai+1; bi<N; bi++){
                    for(int bj = aj+1; bj<M; bj++){
                        if(board[bi][bj] > board[ai][aj])
                            dp[bi][bj] = Math.max(dp[bi][bj] , dp[ai][aj]+1);
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j= 0; j<M; j++){
                if(dp[i][j] > ans)
                    ans = dp[i][j];
            }
        }
        System.out.print(ans);
    }
}