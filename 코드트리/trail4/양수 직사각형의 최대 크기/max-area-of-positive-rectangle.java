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

    static int largestRectangle(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= M; i++) {
            int curHeight = (i == M ? 0 : height[i]);
            while (!stack.isEmpty() && height[stack.peek()] > curHeight) {
                int h = height[stack.pop()];
                int w;
                if (stack.isEmpty())
                    w = i;
                else 
                    w = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            if (i < M)
                stack.push(i);
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
            ans = Math.max(ans, largestRectangle(height));
        }
        System.out.println(ans == 0 ? -1 : ans);
    }
}