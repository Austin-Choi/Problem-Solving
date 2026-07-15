import java.util.*;
import java.io.*;

/*
플로이드로 최단경로 간선정보 만들고
 그래프 만들어서 e에서 A 가는거 하나 다익스트라로 만들고 
 이때 간선들 가능한거 모두 체크해놓고
 e에서 B가는거 다익스트라 하는데 A에서 체크된 간선이면 비용 0으로 진행하고 
 이때 나온 dist[B] 더하기

 ---------------------------------------------------
 공유 지점 찾아서 다익스트라 3번하기
-> N이 작으니까 공유 지점 찾는거니
플로이드로 
A->i, B-i, i->C 의 최솟값 찾기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int A,B,C,N,M;
    static int[][] cost;
    static long INF = 10_000L * 100_000 + 1;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        cost = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            Arrays.fill(cost[i], -1);
            cost[i][i] = 0;
        }

        A = read();
        B = read();
        C = read();

        while(M-->0){
            int u = read();
            int v = read();
            int c = read();
            cost[u][v] = cost[v][u] = c;
        }

        for(int k = 1; k<=N; k++){
            for(int i = 1; i<=N; i++){
                if(cost[i][k] == -1)
                    continue;
                for(int j = 1; j<=N; j++){
                    if(cost[k][j] == -1)
                        continue;
                    if(cost[i][j] == -1 || cost[i][j] > cost[i][k] + cost[k][j])
                        cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }
        
        long ans = INF;
        for(int i = 1; i<=N; i++){
            if(cost[A][i] != -1 && cost[B][i] != -1 && cost[i][C] != -1)
                ans = Math.min(ans, cost[A][i] + cost[B][i] + cost[i][C]);
        }
        System.out.print(ans == INF ? -1 : ans);
    }
}