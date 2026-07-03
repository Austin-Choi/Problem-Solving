import java.util.*;
import java.io.*;

/*
프로파일링 dp -> 도달 가능한 모든 상태 가정하기
이미 세로로 하나 놓으면 i+1로 가려면
맨 아래 튀어나오거나 맨 위 튀어나온 상태가 됨.
가운데 튀어나온건 어차피 위 아래로 가로로 놓는 방법밖에 없음
-> 이건 [1][1]로 표현 가능함
-> 상태 2개인게 3칸이니까 [8]
dp[pos][mask] = 현재 처리중인 열이 pos일 때 현재 열에서 채워진 상태가 mask
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static final int MOD = 10_007;
    static int N;
    static int[][] dp;

    static void dfs(int row, int cm, int nm, int col, int ways){
        // 세줄 다 채움
        if(row == 3){
            dp[col+1][nm] = (dp[col+1][nm] + ways) % MOD;
            return;
        }

        // 현재 상태가 현재 줄을 다 채운 상태라면 다음 줄로 넘어감
        if((cm & (1<<row)) != 0){
            dfs(row+1, cm, nm, col, ways);
            return;
        }

        // 가로 타일 놓기
        // 가로로 하나 놓으면 다음 열에도 영향을 미치므로 다음 마스크에도 추가
        dfs(row+1, cm | (1<<row), nm | (1<<row), col, ways);
        // 세로 타일 놓기
        // 세로로 놓으면 다음줄 스킵되니까 row+1 검사하고
        // 해당 줄이 비어있어야 함
        // 세로로 놓으면 현재 열에서 끝나므로 다음 마스크에는 추가하지 않음
        if(row+1 < 3 && (cm & (1<<(row+1))) == 0){
            dfs(row+2, cm | (1<<row) | (1<<(row+1)), nm, col, ways);
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        // N까지 채운 상태를 봐야하므로
        dp = new int[N+1][8];
        dp[0][0] = 1;

        for(int i = 0; i<N; i++){
            for(int mask = 0; mask<8; mask++){
                if(dp[i][mask] == 0)
                    continue;
                dfs(0, mask, 0, i, dp[i][mask]);
            }
        }

        System.out.print(dp[N][0]);
    }
}