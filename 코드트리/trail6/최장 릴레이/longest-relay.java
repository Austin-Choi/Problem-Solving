import java.util.*;
import java.io.*;

/*
항상 간선 cost 커지는 순으로 진행
bitCount가 최대가 될때의 그 값
dp[1<<N][i][c] = 현재 집합상태 m, 마지막으로 방문한 정점 i일때 마지막 방문 cost가 c일때 최대 사람수
-> 사람 수는 불가능 상태를 작성해서 가능상태인 bitCount 값을 최대로 갱신
-> 가능한지만 알아도 될듯
-> boolean dp로 하니까 bitcount 가능한 마스크마다 수행해서 느려짐

dp[1<<N][i] = 현재 집합상태 m, 마지막으로 방문한 정점 i일때 최소 cost 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] cost;
    static final int INF = 21;

    public static void main(String[] args) throws IOException{
        N = read();
        cost = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cost[i][j] = read();
            }
        }

        // i를 먼저쓰는게 캐시친화적
        // 최소 cost 저장하는거니까 max로초기화
        int[][] dp = new int[N][1<<N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][1<<0] = 0;

        for(int m = 0; m<(1<<N); m++){
            for(int last = 0; last < N; last++){
                if((m & (1<<last)) == 0)
                    continue;
                for(int next = 0; next < N; next++){
                    if((m & (1<<next)) != 0)
                        continue;
                    if(cost[last][next] <= dp[last][m])
                        continue;
                    int nm = m | (1<<next);
                    dp[next][nm] = Math.min(dp[next][nm], cost[last][next]);
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int m = 0; m<(1<<N); m++){
                if(dp[i][m] == INF)
                    continue;
                ans = Math.max(ans, Integer.bitCount(m));
            }
        }
        System.out.print(ans);
    }
}