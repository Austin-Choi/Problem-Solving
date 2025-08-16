/*
0,0 과 N,M을 갈라놓아야 하므로
ㄱ 에서 시작해서
ㄴ 의 dist 값중 최소값 구하면 됨

dq에서 dist 초기값을 ㄱ의 board값을 보고 정함
board 1이면 cost 0
board 0이면 cost 1
8방향 탐색
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int[][] board;
    static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] dist;
    static final int INF = 2000 * 2000 + 1;

    static void bfs(){
        Deque<int[]> dq = new ArrayDeque<>();
        // ㄱ 중 ㅡ
        for (int j = 1; j < M; j++) {
            int cost = board[0][j] == 1 ? 0 : 1;
            dist[0][j] = cost;
            if (cost == 0)
                dq.addFirst(new int[]{0, 0, j});
            else
                dq.addLast(new int[]{0, 0, j});
        }

        // ㄱ 중 ㅣ
        for (int i = 1; i < N - 1; i++) {
            int cost = board[i][M - 1] == 1 ? 0 : 1;
            dist[i][M - 1] = cost;
            if (cost == 0)
                dq.addFirst(new int[]{0, i, M - 1});
            else
                dq.addLast(new int[]{0, i, M - 1});
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int d = cur[0], ci = cur[1], cj = cur[2];
            if (dist[ci][cj] < d) continue;

            for (int dir = 0; dir < 8; dir++) {
                int ni = ci + di[dir];
                int nj = cj + dj[dir];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;

                int nc = board[ni][nj] == 1 ? 0 : 1;
                if (dist[ni][nj] > dist[ci][cj] + nc) {
                    dist[ni][nj] = dist[ci][cj] + nc;
                    if (nc == 0)
                        dq.addFirst(new int[]{dist[ni][nj], ni, nj});
                    else
                        dq.addLast(new int[]{dist[ni][nj], ni, nj});
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int answer = 2;
        // ㄴ 중 ㅣ: (1~N-1,0)
        for (int i = 1; i < N; i++)
            answer = Math.min(answer, dist[i][0]);
        // ㄴ 중 ㅡ: (N-1,1~M-2)
        for (int j = 1; j < M - 1; j++)
            answer = Math.min(answer, dist[N - 1][j]);

        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
