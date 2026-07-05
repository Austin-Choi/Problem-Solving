import java.util.*;
import java.io.*;

/*
>??
dp[i][mask] = 현재 i번째 행까지 봤을때, 선택 상태가 mask인 상태에서 최대 선택 갯수

검사해야하는거 m
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    // 벽 상태 비트마스크로 행단위로 나타냄
    static int[] board;
    

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N];
        for(int i = 0; i<N; i++){
            int m = 0;
            for(int j = 0; j<M; j++){
                int cur = read();
                if(cur == 1)
                    m |= (1<<j);
            }
            board[i] = m;
        }

        int[][] dp = new int[N][1<<M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], -1);
        }
        // 벽이 아닌 모든 구멍이 비어있고
        // <<, >> 둘다 안해도 <<만으로도 인접한 11 검출함
        for(int m = 0; m<(1<<M); m++){
            if((m & board[0]) != 0)
                continue;
            if((m & (m<<1)) != 0)
                continue;
            dp[0][m] = Integer.bitCount(m);
        }

        for(int i = 1; i<N; i++){
            for(int m = 0; m<(1<<M); m++){
                // 벽선택 막기
                if((m & board[i]) != 0)
                    continue;
                // 가로 인접 검사
                if((m & (m<<1)) != 0)
                    continue;
                for(int prev = 0; prev < (1<<M); prev++){
                    // prev 상태가 불가능 상태면 전이하면 안됨
                    if(dp[i-1][prev] == -1)
                        continue;
                    // 세로로 겹치는거 있으면 안됨 and 연산
                    if((m & prev) != 0)
                        continue;
                    dp[i][m] = Math.max(dp[i][m], dp[i-1][prev] + Integer.bitCount(m));
                }
            }
        }

        int ans = 0;
        // 조건상 가능한 것 중에 N-1 번째행까지 봤을때 선택 상태들중 
        // 최대의 경우의 수 고르기
        for(int m = 0; m<(1<<M); m++){
            if((m & board[N-1]) != 0)
                continue;
            if((m & (m << 1)) != 0)
                continue;
            ans = Math.max(ans, dp[N-1][m]);
        }
        System.out.print(ans);
    }
}