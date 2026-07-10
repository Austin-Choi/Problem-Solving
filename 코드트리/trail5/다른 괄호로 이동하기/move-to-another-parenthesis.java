import java.util.*;
import java.io.*;

/*
간선 가중치가 같다 다르다 밖에 없으므로 0-1 BFS (다익스트라의 특수한 형태) 쓰고
모든 칸에 대해 임의로 출발과 도착을 정해서 두칸 사이의 이동시간은 최소이면서
모든 경우에 대해서 최댓값을 구해야하니까 최댓값 제한을 해두고 모든 가능한 가중치 값에 대해 해볼까 파라메트릭 서치 느낌으로
-> 근데 이래도 모든 칸에 대해서 다익스트라 돌려야하는건 똑같고
0-1 bfs는 A시간 B시간있어서 실제 가중치는 그거 계산해야하니까 

*/

public class Main {

    static int N,A,B;
    static char[][] board;
    static ArrayList<int[]>[] g;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static final int INF = 30 * 30 * 1_000_000 + 1;

    static int dijkstra(int si){
        PriorityQueue<int[]> pq =new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int[] dist = new int[N*N];
        Arrays.fill(dist, INF);
        pq.add(new int[]{si, 0});
        dist[si] = 0;

        while(!pq.isEmpty()){
            int[] cur =pq.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(dist[ci] != cd)
                continue;

            for(int[] n : g[ci]){
                int ni =n[0];
                int nd = n[1];
                if(dist[ni] > dist[ci] + nd){
                    dist[ni] = dist[ci]+nd;
                    pq.add(new int[]{ni, dist[ni]});
                }
            }
        }

        int ans = 0;
        for(int d : dist){
            if(d == INF)
                continue;
            ans = Math.max(ans, d);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        board =new char[N][N];
        for(int i= 0; i<N; i++){
            char[] tt = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                board[i][j] = tt[j];
            }
        }
        g = new ArrayList[N*N];
        for(int i = 0; i<N*N; i++){
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                int s = i*N + j;
                for(int d = 0; d<4; d++){
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if(ni < 0 || nj < 0 || ni >=N || nj >= N)
                        continue;
                    int e = ni * N + nj;
                    if(board[i][j] != board[ni][nj]){
                        g[s].add(new int[]{e,B});
                        g[e].add(new int[]{s,B});
                    }
                    else{
                        g[s].add(new int[]{e,A});
                        g[e].add(new int[]{s,A});
                    }
                }
            }
        }

        int ans = 0;
        for(int s = 0; s<N*N; s++){
            ans = Math.max(ans, dijkstra(s));
        }
        System.out.print(ans);
    }
}