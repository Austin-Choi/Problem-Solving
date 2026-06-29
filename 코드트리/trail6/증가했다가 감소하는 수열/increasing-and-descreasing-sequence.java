import java.util.*;
import java.io.*;

/*
처음과 끝은 항상 들어있음 
1 xxxx N xxx 1 이런식으로
나머지는 증가햇다가 감소하면서 한번씩 골라야함
둘 다 증가한다고 치고
2부터 N-1번째까지 
dp[pos][i][j]
dp[1][1] = 1 -> 둘다 1번째에서 시작함 
스킵하는것도 있어서 max(i,j)로 판독 안됨
스킵수 제한 없어서 4차원까지는 필요없음
사실 스킵수 = pos - i - j 로 유도 가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] card;
    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException{
        N = read();
        card = new int[N];
        for(int i = 0; i<N; i++){
            card[i] = read();
        }

        if(N == 1){
            System.out.print(1);
            return;
        }
        // pos : 마지막에 본 인덱스, i : 왼쪽, j : 오른쪽
        // 어차피 마지막 고정이니까 N-1로
        // 왼쪽은 오름차순 오른쪽은 내림차순
        int M = N-1;
        int[][][] dp = new int[M][M][M];
        dp[0][0][0] = 1;
        for(int p = 0; p<M-1; p++){
            for(int i = 0; i<M; i++){
                for(int j = 0; j<M; j++){
                    if(dp[p][i][j] == 0)
                        continue;
                    if(card[j] < card[p+1])
                        dp[p+1][i][p+1] = (dp[p+1][i][p+1] + dp[p][i][j]) % MOD;
                    if(card[i] < card[p+1])
                        dp[p+1][p+1][j] = (dp[p+1][p+1][j]+ dp[p][i][j]) % MOD;
                    dp[p+1][i][j] = (dp[p+1][i][j]+ dp[p][i][j]) % MOD; 
                }
            }
        }

        int ans = 0;
        // 마지막 위치를 오름차순 끝에 추가하고
        // 내림차순 시작과 연결 확인
        for(int i= 0; i<N-1; i++){
            for(int j= 0; j<N-1; j++){
                if(dp[N-2][i][j] == 0)
                    continue;
                if(card[i] < card[N-1]){
                    if(card[j] < card[N-1]){
                        ans = (ans + dp[N-2][i][j]) % MOD;
                    }
                }
            }
        }
        System.out.print(ans);
    }
}