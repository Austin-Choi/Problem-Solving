import java.util.*;
import java.io.*;

/*
이동비용 그래프로 빨간 점이 포함된 경로 그래프 boolean으로 하나 만들고 
-> 플로이드로 i,k,j 중 하나라도 빨간점이면 true
-> 최단 비용 그래프 따로 갱신

isRed[a][b] && minPath[a][b] != INF 면 
도달가능하고 a->b 경로에 빨간점이 1개 이상이니까 
cnt ++, sum +=

----------------------
저렇게 하면 최단경로 이면서 빨간점 포함이 아니고 서로 독립적이게 되버림
mp를 3차원으로 만들고
mp[i][j][2] = i->j 로 가는 최단경로인데 빨간점 포함 여부(0,1)
-> 걍 g로 통일
아 그냥 ㅈㄴ 안됨

-------------------------------
그냥 2차원 플로이드 한번 돌리고 
쿼리 들어왔을때 모든 빨간 점에 대해서 A->B가 갱신 가능하면
A->red->B 가 가능한 경로가 있다는거고 거기에 대해서 최솟값을 구하면
A에서 시작해서 B로 끝나는데 red를 1개 이상 지나가는 최소 경로가 있다는거임
그래서 min값이 INF가 아니면(갱신 성공하면) cnt++ sum+=
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M, P, Q;
    static long[][] dist;
    static final long INF = 10_000L * 1_000_000 + 1;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        P = read();
        Q = read();

        dist = new long[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        while (M-- > 0) {
            int u = read();
            int v = read();
            int w = read();
            if (w < dist[u][v]) 
                dist[u][v] = w;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int cnt = 0;
        long sum = 0;
        // 쿼리에서 빨간점 피봇으로 삼아서 최단경로 새로 구해주기
        // 여기서 INF면 도달불가니까 cnt x
        while (Q-- > 0) {
            int a = read();
            int b = read();
            
            long minCost = INF;
            for (int r = 1; r <= P; r++) {
                if (dist[a][r] != INF && dist[r][b] != INF) 
                    minCost = Math.min(minCost, dist[a][r] + dist[r][b]);  
            }
            
            if (minCost != INF) {
                cnt++;
                sum += minCost;
            }
        }
        System.out.println(cnt);
        System.out.println(sum);
    }
}