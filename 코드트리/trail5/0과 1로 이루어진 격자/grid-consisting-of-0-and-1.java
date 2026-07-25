import java.util.*;
import java.io.*;

/*
i,j 칸을 누르면 0,0 ~ i,j 칸에 해당하는 모든 칸이 반전됨.
뒤에서부터 보면서 flips 실제로 시뮬하지말고 그걸로 현재 상태 판단하기??

5
01011
11010
01010
10010
00111
*/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                int press = arr[i][j]
                        ^ arr[i + 1][j]
                        ^ arr[i][j + 1]
                        ^ arr[i + 1][j + 1];
                ans += press;
            }
        }
        System.out.println(ans);
    }
}