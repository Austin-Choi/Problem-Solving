import java.util.*;
import java.io.*;

/*
dp[col][1<<row]

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static int N,M;
    static int[][] dp;
    static final int MOD = 10_007;

    static void dfs(int row, int cm, int nm, int col, int ways){
        // 다 채움
        if(row == 4){
            dp[col+1][nm] = (dp[col+1][nm] + ways) % MOD;
            return;
        }

        // 만약 현재 상태의 row가 채워져 있으면 다음 row
        if((cm & (1<<row)) != 0){
            dfs(row+1, cm, nm, col, ways);
            return;
        }

        // 가로 채우기
        dfs(row+1, cm | (1<<row), nm | (1<<row), col, ways);
        // 세로 채우기
        if(row+1 < 4 && ((cm & (1<<(row+1))) == 0)){
            dfs(row+2, cm | (1<<row) | (1<<(row+1)), nm, col, ways);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        dp = new int[N+1][1<<4];
        dp[0][0] = 1;

        for(int i = 0; i<N; i++){
            for(int mask = 0; mask < (1<<4); mask++){
                if(dp[i][mask] == 0)
                    continue;
                dfs(0, mask, 0, i, dp[i][mask]);
            }
        }

        System.out.print(dp[N][0]);
    }   
}