import java.util.*;
import java.io.*;

/*
항상 간선 cost 커지는 순으로 진행
bitCount가 최대가 될때의 그 값
dp[1<<N][i][c] = 현재 집합상태 m, 마지막으로 방문한 정점 i일때 마지막 방문 cost가 c일때 최대 사람수
-> 사람 수는 불가능 상태를 작성해서 가능상태인 bitCount 값을 최대로 갱신
-> 가능한지만 알아도 될듯
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] cost;

    public static void main(String[] args) throws IOException{
        N = read();
        cost = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cost[i][j] = read();
            }
        }

        boolean[][][] dp = new boolean[1<<N][N][21];
        dp[1<<0][0][0] = true;

        int ans = 0;
        // 순회 돌 때 많이 걸러지는걸 최상단에 배치해야 함
        // dp m last lc 가 false일때
        for(int m = 0; m<(1<<N); m++){
            for(int last = 0; last<N; last++){
                if((m & (1<<last)) == 0)
                    continue;
                for(int lc = 0; lc<=20; lc++){
                    if(!dp[m][last][lc])
                        continue;
                    for(int next = 0; next < N; next++){
                        if((m & (1<<next)) != 0)
                            continue;
                        if(cost[last][next] == 0)
                            continue;
                        if(cost[last][next] <= lc)
                            continue;
                        int nm = m | (1<<next);
                        dp[nm][next][cost[last][next]] = true;
                        ans = Math.max(ans, Integer.bitCount(nm));
                }
                }
            }
        }
        System.out.print(ans);
    }
}