import java.util.*;
import java.io.*;

/*
tsp를 수행하고 마지막 방문 정점의 직접 놓는 비용까지 합한거중 최소

------------
현재 활성화된 모든 정점이 후보임 -> tsp는 path 만드는거고 이건 Tree 만들어짐
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, K;
    // 간선 cost와 물건 직접놓는 cost는 분리해야함..
    // dp 갱신할때 역할이 달라서
    static int[] thingCost;
    static int[][] cost;
    static int[] dist;
    static final int INF = 100_000 * 32;

    static void tsp(){
        dist = new int[1<<N];
        Arrays.fill(dist, INF);
        // 모든 점에서 시작가능함
        for(int i = 0; i<N; i++){
            dist[1<<i] = thingCost[i];
        }

        for(int m = 0; m<(1<<N); m++){
            for(int has = 0; has < N; has++){
                if(dist[m] == INF)
                    continue;
                if((m & (1<<has)) == 0)
                    continue;
                for(int hasNot = 0; hasNot < N; hasNot++){
                    if((m & (1<<hasNot)) != 0)
                        continue;
                    if(cost[has][hasNot] == 0)
                        continue;
                    int nm = m | (1<<hasNot);
                    // 없는 점을 집합에 추가할때 cost는 
                    // 1) 기존 집합에서 간선으로 새 물체를 연결하기
                    // 2) 기존 집합에 추가로 새 물체를 직접 놓기
                    int add = Math.min(cost[has][hasNot], thingCost[hasNot]);
                    dist[nm] = Math.min(dist[nm], dist[m]+add);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        thingCost = new int[N];
        cost = new int[N][N];
        for(int i = 0; i<N; i++){
            thingCost[i] = read();
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                cost[i][j] = read();
            }
        }

        // 플로이드 전처리 필요
        for(int k = 0; k<N; k++){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    if(cost[i][k] != 0 && cost[k][j] != 0){
                        cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                    }
                }
            }
        }

        tsp();
        int ans = INF;
        for(int m = 0; m< (1<<N); m++){
            if(Integer.bitCount(m) >= K){
                ans = Math.min(ans, dist[m]);
            }
        }
        System.out.print(ans);
    }
}