/*
* 상하좌우 움직이는거라 그거 고려해야함
* 2번 예제가 위 오른쪽이 아니라 상하좌우 다쓰면 19나옴
* dp가 아니라 다익스트라
* */
import java.util.*;
import java.io.*;

public class Main {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N;
    static int[][] board;
    static int dijkstra(){
        int INF = 10 * N*N;
        int[][] dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = board[0][0];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,board[0][0]});

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            if(dist[ci][cj] != cd)
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                int nc = dist[ci][cj] + board[ni][nj];
                if(dist[ni][nj] > nc){
                    dist[ni][nj] = nc;
                    q.add(new int[]{ni,nj,nc});
                }
            }
        }

        return dist[N-1][N-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;
            board = new int[N][N];

            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j<N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ");
            sb.append(count++);
            sb.append(": ");
            sb.append(dijkstra());
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
