import java.util.*;
import java.io.*;

/*
tsp해주고 
모든 마스크에 대해 >>1 하고 bitCount 한 결과가 K인 마스크의 N개에 대해서 최소 비용 구하기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,K;
    static int[][] cost;
    static final int INF = 16*10_000 + 1;
    static int[][] dist;
    
    static void tsp(){
        dist = new int [1<<N][N];
        for(int i = 0; i<(1<<N); i++)
            Arrays.fill(dist[i], INF);
        dist[1<<0][0] = 0;

        for(int m = 0; m<(1<<N); m++){
            for(int last = 0; last < N; last++){
                if(dist[m][last] == INF)
                    continue;
                if((m & (1 << last)) == 0)
                    continue;

                for(int next = 0; next < N; next++){
                    if((m & (1 << next)) != 0)
                        continue;
                    if(cost[last][next] == 0)
                        continue;
                    int nm = m | (1<<next);
                    if(dist[nm][next] > dist[m][last] + cost[last][next]){
                        dist[nm][next] = dist[m][last] + cost[last][next];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        cost = new int[N][N];
        for(int i= 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cost[i][j] = read();
            }
        }

        tsp();

        int ans = INF;
        for(int m = 0; m< (1<<N); m++){
            if(Integer.bitCount((m>>1)) == K){
                for(int i = 1; i<N; i++){
                    if(dist[m][i] == INF)
                        continue;
                    if(cost[i][0] == 0)
                        continue;
                    ans = Math.min(ans, dist[m][i]+ cost[i][0]);
                }
            }
        }
        System.out.print(ans);
    }
}