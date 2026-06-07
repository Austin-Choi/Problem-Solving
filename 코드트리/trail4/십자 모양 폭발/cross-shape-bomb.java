import java.util.*;
import java.io.*;

/*
위아래로는 val-1씩
양옆은 j 위치 한개만 제거하는데 val-1만큼 몇줄이나 remove 해야할지 정해서 해야함
터지면 board val = 0됨

------------------------------------------------
1) 일단 동서남북으로 터뜨림
2) 
*/

public class Main {
    static StreamTokenizer sst =
            new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static void bomb(int si, int sj) {
        int range = board[si][sj];

        board[si][sj] = 0;

        for (int d = 0; d < 4; d++) {
            for (int k = 1; k < range; k++) {
                int ni = si + di[d] * k;
                int nj = sj + dj[d] * k;

                if (ni < 0 || ni >= N || nj < 0 || nj >= N)
                    break;

                board[ni][nj] = 0;
            }
        }
    }

    static void gravity() {
        for (int j = 0; j < N; j++) {
            int[] temp = new int[N];
            int idx = N - 1;

            for (int i = N - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    temp[idx--] = board[i][j];
                }
            }

            for (int i = 0; i < N; i++) {
                board[i][j] = temp[i];
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        N = read();

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = read();
            }
        }

        int r = read() - 1;
        int c = read() - 1;

        bomb(r, c);
        gravity();

        print();
    }
}