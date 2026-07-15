import java.util.*;
import java.io.*;

/*
같은 정점으로 가는 간선이 없다는 소리가 없음
최단 거리 값을 각 정점에서 정점별로 가능한 경우를 공백을 두고 출력하는데 만약 불가능하다면 -1을 출력
-> 최단경로 배열을 따로 생성?
-> 그냥 g를 INF로 초기화하고 최단거리 자체를 구하는거니까 i->i는 항상 0임
이거 방향 그래프임
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static final int INF = 10_000* 10_000 + 1;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        int[][] g = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            Arrays.fill(g[i], INF);
            g[i][i] = 0;
        }
        
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            g[u][v] = w;
        }

        for(int k = 1; k<=N; k++){
            for(int u = 1; u<=N; u++){
                if(g[u][k] == INF)
                    continue;
                for(int v = 1; v<=N; v++){
                    if(g[k][v] == INF)
                        continue;
                    if(g[u][v] > g[u][k] + g[k][v])
                        g[u][v] = g[u][k] + g[k][v];
                }
            }
        }

        int ans = INF;
        for(int u = 1; u<=N; u++){
                for(int v = 1; v<=N; v++){
                    if(g[u][v] == INF || g[v][u] == INF)
                        continue;
                    if(u == v)
                        continue;
                    ans = Math.min(ans, g[u][v]+g[v][u]);
                }
            }
        System.out.print(ans);
    }
}