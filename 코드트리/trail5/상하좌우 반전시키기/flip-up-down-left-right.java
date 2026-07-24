import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst =
            new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] arr;

    static void press(int r, int c) {
        arr[r][c] ^= 1;

        if (r > 0)
            arr[r - 1][c] ^= 1;

        if (r + 1 < N)
            arr[r + 1][c] ^= 1;

        if (c > 0)
            arr[r][c - 1] ^= 1;

        if (c + 1 < N)
            arr[r][c + 1] ^= 1;
    }

    public static void main(String[] args) throws IOException {
        N = read();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = read();
            }
        }

        int answer = 0;

        // 첫 번째 행은 누를 수 없으므로
        // 위쪽 칸을 보고 아래 칸을 누름
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i - 1][j] == 0) {
                    press(i, j);
                    answer++;
                }
            }
        }

        // 마지막 행까지 처리한 뒤 모든 칸이 1인지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }
}