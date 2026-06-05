import java.util.*;
import java.io.*;

/*
height[j] = i,j를 바닥으로 했을 때 위로 연속된 양수의 갯수
-> 각 행마다 히스토그램 최대 직사각형 구하기
*/

public class Main {
    static StreamTokenizer sst =
        new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static int[][] board;

    static int find(int[] height) {
        Deque<Integer> s = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= M; i++) {
            int curHeight = (i == M ? 0 : height[i]);
            while (!s.isEmpty() && height[s.peekFirst()] > curHeight) {
                int h = height[s.pollFirst()];
                int w;
                if (s.isEmpty())
                    w = i;
                else 
                    w = i - s.peekFirst() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            if (i < M)
                s.addFirst(i);
        }
        return maxArea;
    }

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = read();
            }
        }

        int[] height = new int[M];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) 
                    height[j]++;
                else
                    height[j] = 0;
            }
            ans = Math.max(ans, find(height));
        }
        System.out.println(ans == 0 ? -1 : ans);
    }
}