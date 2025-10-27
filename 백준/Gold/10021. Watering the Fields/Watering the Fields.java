/*
N이 2000개
정점 좌표는 0~1000 0~1000
간선 cost는 유클리드 거리로 측정
C이상의 cost를 가진 간선만 사용가능하고 그걸로 MST 구성
-> 총 cost 얼마?
prim 쓰기 n^2 -> 간선수 많아서
크루스칼은 간선 전부 정의해야하고 간선 정렬까지 해야함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,C;
    static int[] x;
    static int[] y;
    static long ans = 0L;
    static final long INF = Long.MAX_VALUE;
    static long[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = new int[N];
        y = new int[N];
        visited = new boolean[N];
        dist = new long[N];
        for(int n = 0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            x[n] = Integer.parseInt(st.nextToken());
            y[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dist, INF);
        dist[0] = 0;

        for(int i = 0; i<N; i++){
            int s = -1;
            long minDist = INF;
            for(int n = 0; n<N; n++){
                if(!visited[n] && minDist > dist[n]){
                    minDist = dist[n];
                    s = n;
                }
            }

            if(s == -1){
                System.out.println(-1);
                return;
            }

            visited[s] = true;
            ans += dist[s];

            for(int j = 0; j<N; j++){
                if(visited[j])
                    continue;
                long dx = (long) x[s] - x[j];
                long dy = (long) y[s] - y[j];
                long cost = dx * dx + dy * dy;
                if(cost<C)
                    continue;
                dist[j] = Math.min(dist[j], cost);
            }

        }
        System.out.println(ans);
    }
}

