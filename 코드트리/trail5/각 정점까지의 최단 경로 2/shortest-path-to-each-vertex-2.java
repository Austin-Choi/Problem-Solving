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
    static final long INF = 100_000L * 100_000 + 1;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        long[][] ans = new long[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    ans[i][j] = 0;
                else
                    ans[i][j] = INF;
            }
        }
        
        while(M-->0){
            int u = read();
            int v = read();
            int w = read();
            if(ans[u][v] < w)
                w = (int) ans[u][v];
            ans[u][v] = w;
        }

        for(int k = 1; k<=N; k++){
            for(int u = 1; u<=N; u++){
                if(ans[u][k] == INF)
                    continue;
                for(int v = 1; v<=N; v++){
                    if(ans[k][v] == INF)
                        continue;
                    if(ans[u][v] > ans[u][k] + ans[k][v]){
                        ans[u][v] = ans[u][k] + ans[k][v];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(ans[i][j] == INF)
                    sb.append(-1);
                else
                    sb.append(ans[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}