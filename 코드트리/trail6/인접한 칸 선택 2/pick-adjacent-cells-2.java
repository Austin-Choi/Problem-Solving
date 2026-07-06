import java.util.*;
import java.io.*;

/*
1) n최대 10이고 m이 최대 100이니까 n*m 사각형 m*n으로 입력받고
2) 첫번째줄 초기값 각자 한개씩 선택한거 board 값 자체로 선정하고 
여러개 선택한건 거기서 확장해서 구하고
3) 진행은 위에서 아래로 

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int[][] board;
    static int N,M;

    static int getSum(int col, int m){
        int sum = 0;
        for(int i= 0; i<N; i++){
            if((m & (1<<i)) != 0){
                sum += board[col][i];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();

        board = new int[M][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                board[j][i] = read();
            }
        }

        int[][] dp = new int[M][1<<N];
        for(int m = 0; m<(1<<N); m++){
            if((m & (m<<1)) != 0)
                continue;
            dp[0][m] = getSum(0, m);
        }

        int ans = 0;
        for(int i = 1; i<M; i++){
            for(int m = 0; m<(1<<N); m++){
                for(int prev = 0; prev<(1<<N); prev++){
                    if((m & prev) != 0)
                        continue;
                    if((m & (m<<1))!= 0)
                        continue;
                    dp[i][m] = Math.max(dp[i][m], dp[i-1][prev] + getSum(i,m));
                    if(i == M-1){
                        ans = Math.max(ans, dp[i][m]);
                    }
                }
            }
        }
        System.out.print(ans);
    }
}