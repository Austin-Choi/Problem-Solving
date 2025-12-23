/*
0,0에서 0,0으로 돌아와야함
-> bfs, 동서남북으로, 높이 차이가 T 이하
cost
curH >= nextH : 1
curH < nextH : Math.abs(cur-next) ^2
-> 방향에 따라 cost 다름

다익으로 각 칸에 대해 정방향, 역방향 한번씩 다익스트라로
각 칸까지 시간을 비용으로 최단시간 계산함 -> dist[i][j]
정방향 dist + 역방향 dist 값 합산해서 D 이하면
해당 지점을 찍고 호텔로 올수 있다는 뜻이니까
그때 높이 ans로 최대로 갱신
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,T,D;
    static int[][] board;

    static int parser(char c){
        if(c >= 'a')
            return c - 'a' + 26;
        return c - 'A';
    }

    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    static final int INF = Integer.MAX_VALUE / 4;
    static int[][] dijkstra(){
        int[][] dist = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));

        dist[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int ct = cur[2];

            if(ct > dist[ci][cj])
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(Math.abs(board[ni][nj]-board[ci][cj]) > T)
                    continue;

                int nc;
                if(board[ni][nj] <= board[ci][cj])
                    nc = 1;
                else{
                    int diff = board[ni][nj] - board[ci][cj];
                    nc = diff * diff;
                }

                if(dist[ni][nj] > dist[ci][cj] + nc){
                    dist[ni][nj] = dist[ci][cj] + nc;
                    q.add(new int[]{ni,nj,dist[ni][nj]});
                }
            }
        }
        return dist;
    }

    static int[][] dijkstraReverse(){
        int[][] dist = new int[N][M];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a->a[2]));

        dist[0][0] = 0;
        q.add(new int[]{0,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int ct = cur[2];

            if(ct > dist[ci][cj])
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(Math.abs(board[ci][cj]-board[ni][nj]) > T)
                    continue;

                int nc;
                if(board[ni][nj] >= board[ci][cj])
                    nc = 1;
                else{
                    int diff = board[ci][cj] - board[ni][nj];
                    nc = diff * diff;
                }

                if(dist[ni][nj] > dist[ci][cj] + nc){
                    dist[ni][nj] = dist[ci][cj] + nc;
                    q.add(new int[]{ni,nj,dist[ni][nj]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = parser(temp[j]);
            }
        }

        int[][] dist1 = dijkstra();
        int[][] dist2 = dijkstraReverse();

        int ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(dist1[i][j] == INF || dist2[i][j] == INF)
                    continue;
                if(dist1[i][j] + dist2[i][j] <= D)
                    ans = Math.max(ans, board[i][j]);
            }
        }

        System.out.println(ans);
    }
}

