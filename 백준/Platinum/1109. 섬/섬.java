/*
지도 입력받고 현재 정점을 T = 0이라고 하면
board에서 x 동서남북+대각선으로 방문해서 숫자를 매기고
아직 숫자가 안 매겨진 정점인데 x인거에서 T 증가시킴 - 반복

DAG 그래프 만들어서 dfs로 재귀적으로 높이 계산함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static char[][] input;
    static int[][] board;
    static int[][] wid;
    static int[] di = {-1,-1,0,1,1,1,0,-1};
    static int[] dj = {0,1,1,1,0,-1,-1,-1};
    static int[] wi = {0,0,1,-1};
    static int[] wj = {1,-1,0,0};

    static int dfs(int v, List<Integer>[] graph, int[] dp) {
        if (dp[v] != -1) return dp[v];

        int h = 0;
        for (int next : graph[v]) {
            h = Math.max(h, dfs(next, graph, dp));
        }
        return dp[v] = h + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new char[N][M];
        board = new int[N][M];
        wid = new int[N][M];
        for(int i =0 ; i<N; i++){
            Arrays.fill(board[i], -1);
            Arrays.fill(wid[i], -1);
            input[i] = br.readLine().toCharArray();
        }

        int T = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(input[i][j] == 'x' && board[i][j] == -1){
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i,j});
                    board[i][j] = T;

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int ci = cur[0];
                        int cj = cur[1];

                        for(int d = 0; d<8; d++){
                            int ni = ci + di[d];
                            int nj = cj + dj[d];
                            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                                continue;
                            if(input[ni][nj] != 'x')
                                continue;
                            if(board[ni][nj] != -1)
                                continue;
                            q.add(new int[]{ni,nj});
                            board[ni][nj] = T;
                        }
                    }
                    T++;
                }
            }
        }

        if (T == 0) {
            System.out.println(-1);
            return;
        }

        // 바깥과 연결된 물 표시
        boolean[][] dead = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
                    if (input[i][j] == '.' && !dead[i][j]) {
                        dead[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + wi[d];
                int nj = cur[1] + wj[d];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if (input[ni][nj] != '.' || dead[ni][nj])
                    continue;
                dead[ni][nj] = true;
                q.add(new int[]{ni, nj});
            }
        }

        // DAG 생성
        List<Integer>[] graph = new List[T];
        for (int i = 0; i < T; i++)
            graph[i] = new ArrayList<>();
        // 물 방문 표시
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 물 건너뜀
                if (board[i][j] == -1)
                    continue;

                int island = board[i][j];

                for (int d = 0; d < 4; d++) {
                    int ni = i + wi[d];
                    int nj = j + wj[d];

                    if (ni < 0 || nj < 0 || ni >= N || nj >= M)
                        continue;
                    if (input[ni][nj] != '.' || dead[ni][nj])
                        continue;
                    if (visited[ni][nj])
                        continue;

                    // 현재 섬 내부에 닫힌 물 영역 탐색
                    // 그 물 영영과 맞닿아 있는 안족 섬들을 자식으로 추가
                    Queue<int[]> wq = new ArrayDeque<>();
                    wq.add(new int[]{ni, nj});
                    visited[ni][nj] = true;

                    Set<Integer> innerIslands = new HashSet<>();

                    while (!wq.isEmpty()) {
                        int[] cur = wq.poll();

                        for (int k = 0; k < 4; k++) {
                            int xi = cur[0] + wi[k];
                            int xj = cur[1] + wj[k];

                            if (xi < 0 || xj < 0 || xi >= N || xj >= M)
                                continue;

                            if (input[xi][xj] == '.' && !dead[xi][xj] && !visited[xi][xj]) {
                                visited[xi][xj] = true;
                                wq.add(new int[]{xi, xj});
                            }

                            if (board[xi][xj] != -1 && board[xi][xj] != island) {
                                innerIslands.add(board[xi][xj]);
                            }
                        }
                    }

                    for (int next : innerIslands) {
                        graph[island].add(next);
                    }
                }
            }
        }

        // dfs로 높이 계산
        int[] dp = new int[T];
        Arrays.fill(dp, -1);

        int maxH = 0;
        for (int i = 0; i < T; i++) {
            maxH = Math.max(maxH, dfs(i, graph, dp));
        }

        int[] cnt = new int[maxH + 1];
        for (int i = 0; i < T; i++) {
            cnt[dp[i]]++;
        }

        for (int i = 1; i <= maxH; i++) {
            System.out.print(cnt[i] + " ");
        }
    }
}
