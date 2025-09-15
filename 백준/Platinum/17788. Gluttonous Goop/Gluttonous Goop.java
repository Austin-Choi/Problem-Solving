/*
1) Max*Max Max/3부터 중앙
2) k=1부터 시작해서 BFS 시뮬레이션
3) 매 단계마다 total[k]값을 저장 (goop의 총 면적)
4) k>=3 부터는 연속된 두 (두 항의 차이) 의 차이를 계산 가능
-> d1 : 두 항의 차이
d2 : d1과 d1의 차이
d2가 상수값으로 안정화가 시작되면 BFS 루프를 종료하고
주어진 K 값에서 현재 k값을 빼고 증가량만큼 계산해서 정답 구하기
------------------------------------------------------
stableK를 동적으로 구해봤는데 이게 불안정함
d2가 다음 d2랑 같은 걸로는 충분하지 않나봄
 */
import java.util.*;
import java.io.*;

public class Main {
    static int R, C, K;
    static int[] di = {-1,-1,-1,0,0,1,1,1};
    static int[] dj = {-1,0,1,-1,1,-1,0,1};
    static final int MAX_LIMIT = 200;
    static char[][] currentBoard = new char[MAX_LIMIT][MAX_LIMIT];
    static long[] total;
    static int stableK = 21;

    static void bfs() {
        long ctotal = 0;
        for (int i = 0; i < MAX_LIMIT; i++) {
            for (int j = 0; j < MAX_LIMIT; j++) {
                if (currentBoard[i][j] == '#') {
                    ctotal++;
                }
            }
        }
        total[0] = ctotal;

        for (int k = 1; k <= stableK; k++) {
            char[][] nextBoard = new char[MAX_LIMIT][MAX_LIMIT];
            for (int i = 0; i < MAX_LIMIT; i++) {
                Arrays.fill(nextBoard[i], '.');
            }

            for (int r = 0; r < MAX_LIMIT; r++) {
                for (int c = 0; c < MAX_LIMIT; c++) {
                    if (currentBoard[r][c] == '#') {
                        nextBoard[r][c] = '#';
                        for (int d = 0; d < di.length; d++) {
                            int nr = r + di[d];
                            int nc = c + dj[d];

                            if (nr >= 0 && nr < MAX_LIMIT && nc >= 0 && nc < MAX_LIMIT) {
                                nextBoard[nr][nc] = '#';
                            }
                        }
                    }
                }
            }

            for(int i = 0; i<MAX_LIMIT; i++){
                for(int j = 0; j<MAX_LIMIT; j++){
                    currentBoard[i][j] = nextBoard[i][j];
                }
            }

            ctotal = 0;
            for (int i = 0; i < MAX_LIMIT; i++) {
                for (int j = 0; j < MAX_LIMIT; j++) {
                    if (currentBoard[i][j] == '#') {
                        ctotal++;
                    }
                }
            }
            total[k] = ctotal;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        char[][] tb = new char[R][C];
        for(int r = 0; r<R; r++){
            tb[r] = br.readLine().toCharArray();
        }

        for(int i = 0; i<MAX_LIMIT; i++){
            Arrays.fill(currentBoard[i], '.');
        }

        for(int i = 0; i<R; i++){
            for(int j = 0; j<C; j++){
                if(tb[i][j] == '#') {
                    currentBoard[i + MAX_LIMIT/3][j + MAX_LIMIT/3] = '#';
                }
            }
        }

        total = new long[Math.max(K+1, stableK+1)];

        bfs();

        long ans;

        if (K <= stableK) {
            ans = total[K];
        } else {
            long diff = (total[stableK] - total[stableK-1]) - (total[stableK-1] - total[stableK-2]);
            for (int i = stableK + 1; i <= K; i++) {
                total[i] = total[i-1] + (total[i-1] - total[i-2]) + diff;
            }
            ans = total[K];
        }

        System.out.println(ans);
    }
}