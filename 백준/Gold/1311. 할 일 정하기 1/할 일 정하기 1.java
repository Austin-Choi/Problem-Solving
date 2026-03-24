/*
Assignment Problem -> 비트마스크 dp
dp[mask] = mask에 해당하는 일을 사람들에게 배정했을때 최소 비용
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] dist;
    static final int INF = 21 * 10000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dp = new int[(1<<N)];
        Arrays.fill(dp,INF);
        dp[0] = 0;

        for(int mask = 0; mask < (1<<N); mask++){
            if(dp[mask] == INF)
                continue;
            // 배정된 일 갯수 = 배정된 사람 수
            int p = Integer.bitCount(mask);
            // 현재
            for(int i = 0; i<N; i++){
                // 현재 마스크에서 i번 일을 해 버린 상태면 넘어감
                if((mask & (1<<i)) != 0)
                    continue;
                int next = mask | (1<<i);
                dp[next] = Math.min(dp[next], dp[mask] + dist[p][i]);
            }
        }

        int full = (1<<N)-1;
        System.out.print(dp[full]);
    }
}
