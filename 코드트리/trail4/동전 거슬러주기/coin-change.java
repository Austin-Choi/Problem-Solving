import java.util.*;
import java.io.*;

/*
무한 배낭
dp[금액] = 최소 동전의 수

dp 초기값 -> 금액을 최대 동전의 수로 하려면 동전 값이 1일때 최대임 -> M+1,2
0원을 만드는 최소 동전의 수는 0개임
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] coin = new int[N];
        int M = read();
        int INF = M+2;

        for(int i =0; i<N; i++){
            coin[i] = read();
        }

        int[] dp = new int[M+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i<N; i++){
            int cur = coin[i];
            for(int j = cur; j<=M; j++){
                dp[j] = Math.min(dp[j], dp[j-cur]+1);
            }
        }

        System.out.print(dp[M] == INF ? -1 : dp[M]);
    }
}