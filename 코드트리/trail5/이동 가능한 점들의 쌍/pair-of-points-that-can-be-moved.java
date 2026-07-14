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

==========
layered DP + 다익스트라
-> 쿼리수가 많아서 tle남 
+ 플로이드 
-> 될듯?
*/

import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M, P, Q;
    static final long INF = 10_000L * 1_000_000 + 1;
    static long[][] dist;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        P = read();
        Q = read();

        // 1~N: layer0, N+1~2N: layer1
        int NN = 2 * N;                    
        dist = new long[NN + 1][NN + 1];
        for (int i = 1; i <= NN; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        while (M-->0) {
            int u = read();
            int v = read();
            int w = read();

            int u0 = u;
            int u1 = u + N;
            int v0 = v;
            int v1 = v + N;

            if (u <= P) 
                dist[u0][v1] = Math.min(dist[u0][v1], w);
            else {
                if (v <= P) 
                    dist[u0][v1] = Math.min(dist[u0][v1], w);
                else
                    dist[u0][v0] = Math.min(dist[u0][v0], w);
            }

            dist[u1][v1] = Math.min(dist[u1][v1], w);
        }

        // 빨간 점 자기 자신 layer 전환
        for (int i = 1; i <= P; i++) {
            dist[i][i + N] = Math.min(dist[i][i + N], 0);
        }

        // Floyd-Warshall (Layered)
        for (int k = 1; k <= NN; k++) {
            for (int i = 1; i <= NN; i++) {
                for (int j = 1; j <= NN; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int cnt = 0;
        long sum = 0;

        while (Q-->0) {
            int a = read();
            int b = read();

            long cost = dist[a][b + N];
            if (a <= P) 
                cost = Math.min(cost, dist[a + N][b + N]);

            if (cost != INF) {
                cnt++;
                sum += cost;
            }
        }
        System.out.println(cnt);
        System.out.println(sum);
    }
}