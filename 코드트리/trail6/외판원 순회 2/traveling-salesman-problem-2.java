import java.util.*;
import java.io.*;


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] cost;
    static final int INF = 16 * 10_000+1;
    static int[][] dist;

    static void tsp(){
        // dist[m][i] = 현재 방문상태가 m일때, i를 마지막으로 방문했을 때의 최소 비용
        dist = new int[(1<<(N+1))][N+1];
        for(int i = 0; i<(1<<N); i++){
            Arrays.fill(dist[i], INF);
        }
        dist[1<<0][1] = 0;
        
        for(int m = 0; m<(1<<N); m++){
            for(int last = 1; last <= N; last++){
                if(dist[m][last] == INF)
                    continue;
                // next를 1-based로 쓰려면
                // bit은 0부터 시작하니까 -1로 표현해야 함.
                for(int next = 1; next <=N; next++){
                    if((m & (1 << (next-1))) != 0)
                        continue;
                    if(cost[last][next] == 0)
                        continue;
                    int nm = m | (1<<(next-1));
                    dist[nm][next] = Math.min(dist[nm][next], dist[m][last] + cost[last][next]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        cost = new int[N+1][N+1];
        
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                int cur = read();
                if(cur == 0)
                    continue;
                cost[i][j] = cur;
            }
        }

        tsp();

        int ans = INF;
        for(int i =1; i<=N; i++){
            // 0은 움직일수 없는 것이므로 돌아가는 비용 계산 불가능
            if(cost[i][1] == 0)
                continue;
            ans = Math.min(ans, dist[(1<<N)-1][i] + cost[i][1]);
        }
        System.out.print(ans);
    }
}