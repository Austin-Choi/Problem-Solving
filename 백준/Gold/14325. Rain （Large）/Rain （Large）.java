import java.util.*;
import java.io.*;
/*
높이가 다른 격자칸 땅에 비가 옴
바깥은 높이 0의 바다,
물은 동서남북으로 움직이고 높은곳에서 낮은곳으로 흘러감

제한이 시작되는 바깥에서부터 시작해서 minimax
dist[i][j] = 해당 칸이 최대로 찰 수 있는 물 높이
next = max(현재 높이, 다음칸 높이)
 */
public class Main {
    static int T, R, C;
    static int[][] H;
    static int[][] dist;
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    static void dijkstra(){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));

        // 바다에 맞닿은 지점에서부터 시작함
        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(i == 0 || j == 0 || i == R-1 || j == C-1){
                    dist[i][j] = H[i][j];
                    pq.add(new int[]{i,j,H[i][j]});
                }
            }
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            if(dist[ci][cj] != cd)
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= R || nj >= C)
                    continue;
                int nd = Math.max(cd, H[ni][nj]);

                // 최소 bottleneck 찾아야 해서 max로 갱신함
                // 최댓값을 최소화
                // 모든 경로 중에서 경로 높이들의 최소값
                if(dist[ni][nj] > nd){
                    dist[ni][nj]= nd;
                    pq.add(new int[]{ni,nj,nd});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        int test = 1;

        while(test <= T){
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            H = new int[R][C];
            dist = new int[R][C];
            int ans = 0;

            for(int i = 0; i<R; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<C; j++){
                    H[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            for(int i = 0; i<R; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            dijkstra();

            for(int i = 0; i<R; i++){
                for(int j = 0; j<C; j++){
                    ans += dist[i][j] - H[i][j];
                }
            }

            sb.append("Case #").append(test).append(": ").append(ans).append("\n");
            test++;
        }
        System.out.print(sb);
    }
}
