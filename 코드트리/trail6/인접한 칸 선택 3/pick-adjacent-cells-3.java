import java.util.*;
import java.io.*;

/*
dp[i][mask][k] = i행까지 봤을 때 현재 열 선택상태가 mask이고 이제까지 선택한 칸 수가 k일때 최대 합
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M,K;
    static int[][] board;
    static int[][] cost;

    static int getSum(int row, int m){
        int sum = 0;
        for(int j =0; j<M; j++){
            if((m & (1<<j)) != 0){
                sum += board[row][j];   
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        K = read();
        board = new int[N][M];
        cost = new int[N][1<<M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[i][j] = read();
            }
        }
        for(int i = 0; i<N; i++){
            for(int m = 0; m<(1<<M); m++){
                if((m & (m<<1)) != 0)
                    continue;
                cost[i][m] = getSum(i, m);
            }
        }

        // 불가능상태 설정
        int[][][] dp = new int[N][1<<M][K+1];
        for(int i = 0; i<N; i++){
            for(int m = 0; m<(1<<M); m++){
                Arrays.fill(dp[i][m], -1);
            }
        }

        for(int m = 0; m<(1<<M); m++){
            if((m & (m<<1)) == 0){
                int kCnt = Integer.bitCount(m);
                if(kCnt <= K){
                    dp[0][m][kCnt] = cost[0][m];
                }
            }
        }

        for(int i = 1; i<N; i++){
            for(int m = 0; m<(1<<M); m++){
                if((m & (m<<1)) != 0)
                    continue;
                for(int prev = 0; prev < (1<<M); prev++){
                    if((prev & (prev<<1)) != 0)
                        continue;
                    if((m & prev) != 0)
                        continue;
                    for(int k = 0; k<=K; k++){
                        if(dp[i-1][prev][k] == -1)
                            continue;
                        int nk = Integer.bitCount(m) + k;
                        if(nk > K)
                            continue;
                        dp[i][m][nk] = Math.max(dp[i][m][nk], dp[i-1][prev][k] + cost[i][m]);
                    }
                }
            }
        }

        int ans = 0;
        for(int m=0;m<(1<<M);m++){
            for(int k=0;k<=K;k++){
                ans = Math.max(ans, dp[N-1][m][k]);
            }
        }
        System.out.print(ans);
    }
}