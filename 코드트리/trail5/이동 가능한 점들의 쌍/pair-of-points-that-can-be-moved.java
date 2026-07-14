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
[0] = [0] + [0], [1] = 0+1, 1+0, 1+1
아 그냥 ㅈㄴ 안됨
-> 여러번 돌리면 충분히 전파되는 이상한 성질..???? -> 확인 필요

-------------------------------
그냥 2차원 플로이드 한번 돌리고 
쿼리 들어왔을때 모든 빨간 점에 대해서 A->B가 갱신 가능하면
A->red->B 가 가능한 경로가 있다는거고 거기에 대해서 최솟값을 구하면
A에서 시작해서 B로 끝나는데 red를 1개 이상 지나가는 최소 경로가 있다는거임
그래서 min값이 INF가 아니면(갱신 성공하면) cnt++ sum+=
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
    static long[][][] g;
    static final long INF = 10_000L * 1_000_000 + 1;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        P = read();
        Q = read();

        g = new long[N + 1][N + 1][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Arrays.fill(g[i][j], INF);
                if (i == j) {
                    if (i <= P) 
                        g[i][i][1] = 0;
                    else 
                        g[i][i][0] = 0;
                }
            }
        }

        while (M-- > 0) {
            int u = read();
            int v = read();
            int w = read();
            if (u > P && v > P) {
                g[u][v][0] = Math.min(g[u][v][0], w);
            } else {
                g[u][v][1] = Math.min(g[u][v][1], w);
            }
        }

        // Floyd를 여러 번 반복하여 상태가 충분히 전파되도록 함
        for (int iter = 0; iter < 3; iter++) {
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (g[i][k][0] != INF && g[k][j][0] != INF) 
                            g[i][j][0] = Math.min(g[i][j][0], g[i][k][0] + g[k][j][0]);

                        if (g[i][k][0] != INF && g[k][j][1] != INF) 
                            g[i][j][1] = Math.min(g[i][j][1], g[i][k][0] + g[k][j][1]);

                        if (g[i][k][1] != INF && g[k][j][0] != INF) 
                            g[i][j][1] = Math.min(g[i][j][1], g[i][k][1] + g[k][j][0]);

                        if (g[i][k][1] != INF && g[k][j][1] != INF) 
                            g[i][j][1] = Math.min(g[i][j][1], g[i][k][1] + g[k][j][1]);
                    }
                }
            }
        }

        int cnt = 0;
        long sum = 0;
        while (Q-- > 0) {
            int a = read();
            int b = read();
            if (g[a][b][1] != INF) {
                cnt++;
                sum += g[a][b][1];
            }
        }
        System.out.println(cnt);
        System.out.println(sum);
    }
}