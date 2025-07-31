/*
어떤 영역 안에서 주사위가 어떻게 굴러가면서 
면상태가 바뀌는지를 연결된 칸을 모두 DFS로 방문

주사위가 굴러가면서 각 면의 값이 어떻게 바뀌는지를 시뮬
방향별로 주사위 면이 어떻게 이동하는지 함수로 구현

DFS 탐색중 이동 방향이 정해질 때마다 turn(k)를 호출해 주사위 상태 갱신

재귀적 탐색 + 상태 변화 추적 
-> 복잡한 움직임과 상태 변화 처리할 때 직관적
DFS로 영역 구분
 */
import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[18][6];
    static int[] dice = new int[6];
    static int[][] visit = new int[18][6];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int s;
    static StringBuilder sb = new StringBuilder();

    public static void turn(int k) {
        int temp;
        if (k == 0) {
            temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[0];
            dice[0] = temp;
        } else if (k == 1) {
            temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[4];
            dice[4] = temp;
        } else if (k == 2) {
            temp = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[2];
            dice[2] = temp;
        } else if (k == 3) {
            temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[5];
            dice[5] = temp;
        }
        dice[0] = 1;
    }

    public static void dfs(int x, int y) {
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 6 * s && nx < 6 * (s + 1) && ny >= 0 && ny < 6
                    && visit[nx][ny] == 0 && board[nx][ny] == 1) {
                visit[nx][ny] = 1;
                turn(k);
                dfs(nx, ny);
                turn((k + 2) % 4);
            }
        }
    }

    public static void check() {
        for (int x : dice) {
            if (x == 0) {
                sb.append("no\n");
                return;
            }
        }
        sb.append("yes\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 18; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (s = 0; s < 3; s++) {
            for (int i = 6 * s; i < 6 * (s + 1); i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == 1 && visit[i][j] == 0) {
                        visit[i][j] = 1;
                        Arrays.fill(dice, 0);
                        dice[0] = 1;
                        dfs(i, j);
                    }
                }
            }
            check();
            Arrays.fill(dice, 0);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}