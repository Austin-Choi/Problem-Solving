import java.util.*;
import java.io.*;
/*
굳이 그래프 안 그려도 됨
dist[bitmask][last]
mask & (1 << i) 가 0이면 방문 안한거
1이면 방문한거

모든 정점 i에 대해 모든 정점 j를 살펴보는데
i는 마지막에 방문한 정점이라 방문 상태여야 하고 (!= 0)
j는 다음에 방문할 정점이라 미방문 상태여야 함. (== 0)
-> 무조건 0으로만 비교함
dist[nextMask][j] = Math.min(dist[nextMask][j]
    , dist[curMask][i] + getDist(pos[i], pos[j])
 */

public class Main {
    static int N;
    static int[][] pos;
    static ArrayList<int[]>[] g;

    static double getCost(int[] p1, int[] p2){
        int a = p1[0]-p2[0];
        int b = p1[1] - p2[1];
        return Math.sqrt(a*a + b*b);
    }

    // [mask][last]
    static double[][] dist;

    static void tsp(){
        int size = 1<<N;
        dist = new double[size][N];
        for(int i = 0; i<size; i++){
            Arrays.fill(dist[i], Double.MAX_VALUE);
        }

        dist[1][0] = 0;

        for(int m = 1; m<size; m++){
            for(int i = 0; i<N; i++){
                // i가 last니까 방문 안한 상태면 진행 못함
                if((m & (1<<i)) == 0)
                    continue;
                // dist m i가 계산할수 있는 상태여야 함
                if(dist[m][i] == Double.MAX_VALUE)
                    continue;

                for(int j = 0; j<N; j++){
                    // j가 이미 방문한 상태면 또 할 필요가 없음
                    if((m & (1<<j)) != 0)
                        continue;
                    // 다음 방문 상태
                    int next = m | (1<<j);
                    double cost = getCost(pos[i], pos[j]);

                    dist[next][j] = Math.min(dist[next][j], dist[m][i] + cost);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pos = new int[N][2];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작점 0으로 고정
        tsp();

        double ans = Double.MAX_VALUE;
        //마지막에 시작점으로 돌아와야함
        for(int i = 0; i<N; i++){
            ans = Math.min(ans, dist[(1<<N)-1][i] + getCost(pos[i], pos[0]));
        }
        System.out.printf("%.7f", ans);
    }
}
