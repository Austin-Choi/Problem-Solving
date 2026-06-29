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

dp[i][j]로 가능한 이유는 max(i,j)가 완전히 확정된 상태로 보고 있기 때문임.
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

        // -> pos는 필요없음 max(i,j)까지 확정됨.
        // -> 경우의 수 세는거니까 초기화는 0
        int[][] dp = new int[N][N];
        dp[0][0] = 1;
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(dp[i][j] == 0)
                    continue;
                int next = Math.max(i,j)+1;
                if(next == N)
                    continue;
                for(int k = next; k<N; k++){
                    if(k == N-1){
                        // 마지막 위치를 내림차순 끝에 추가하고
                        // 내림차순 시작과 연결 확인
                        if(card[i] < card[k] && card[j] < card[k]){
                            dp[k][j] = (dp[k][j] + dp[i][j]) % MOD;
                            //dp[i][k] = (dp[i][k] + dp[i][j]) % MOD;
                        }
                        break;
                    }

                    if(card[i] < card[k]){
                        dp[k][j] = (dp[k][j] + dp[i][j]) % MOD;
                    }
                    if(card[j] < card[k]){
                        dp[i][k] = (dp[i][k] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        int ans = 0;
        /*
        마지막 정점을 붙일때 내림차순한테 붙였으면 i,N-1
        오름차순한테 붙였으면 N-1, i
        */
        for(int i= 0; i<N-1; i++){
            //ans = (ans + dp[i][N-1]) % MOD;
            ans = (ans + dp[N-1][i]) % MOD;
        }
        System.out.print(ans);
    }
}