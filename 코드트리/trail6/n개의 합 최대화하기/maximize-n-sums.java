import java.util.*;
import java.io.*;

/*
위->아래 방향으로 누적하면 1111111 N개의 비트마스크 형태임
dp[m] = [1<<N]
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
            }
        }

        // dp[m] = 현재 위->아래방향 누적 선택 상태가 m일때 얻을 수 잇는 최대 합
        // row는 Integer.bitCount()로
        int[] dp = new int [1<<N];

        for(int m = 0; m<(1<<N); m++){
            int ci = Integer.bitCount(m);
            for(int j = 0; j<N; j++){
                if((m & (1<<j)) != 0)
                    continue;
                int nm = m | (1<<j);
                // 따로 초기화는 필요없는 이유는
                // 첫번째 열에서 하나 고르는건 0에서 1<<j로 하나 고르고 이때 ci는 0임
                dp[nm] = Math.max(dp[nm], dp[m] + board[ci][j]);
            }
        }
        // N개의 1로 이루어진 마스크상태가 답
        System.out.print(dp[(1<<N)-1]);
    }
}
