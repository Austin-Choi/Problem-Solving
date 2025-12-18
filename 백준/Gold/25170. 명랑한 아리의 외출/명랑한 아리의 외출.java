/*
dp 정의: i,j,에 도착했고 총시간 t를 사용했을때 최대 일의 수
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,T;
    static int[][][] board;
    static int[] di = {1,0,1};
    static int[] dj = {0,1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M][2];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j][1] = Integer.parseInt(st.nextToken());
            }
        }


        int[][][] dp = new int[N][M][T+1];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = 0;

        // 단방향 이동 + 최소 횟수로 이동해야하니까
        // i+j가 최대 이동거리고 그 안에서 모든 경로 증가하는 방향으로 단조성띔
        int maxS = N - 1 + M - 1;
        for (int s = 0; s < maxS; s++) {
            for (int i = Math.max(0, s - (M - 1)); i <= Math.min(s, N - 1); i++) {
                int j = s - i;
                if (j < 0 || j >= M) continue;

                for (int t = 0; t <= T; t++) {
                    if (dp[i][j][t] == -1)
                        continue;

                    for (int d = 0; d < 3; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (ni >= N || nj >= M)
                            continue;

                        int nt = t+1;
                        if (nt > T)
                            continue;

                        // 일안함
                        dp[ni][nj][nt] = Math.max(dp[ni][nj][nt], dp[i][j][t]);

                        // 일함
                        int wt = nt + board[ni][nj][1];
                        if (wt <= T) {
                            int worked = dp[i][j][t] + board[ni][nj][0];
                            dp[ni][nj][wt] = Math.max(dp[ni][nj][wt], worked);
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int t = 0; t <= T; t++) {
            if (dp[N - 1][M - 1][t] != -1) {
                ans = Math.max(ans, dp[N - 1][M - 1][t]);
            }
        }
        System.out.println(ans);
    }
}
