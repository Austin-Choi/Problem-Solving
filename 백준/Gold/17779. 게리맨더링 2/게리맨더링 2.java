/*
경계선에 해당하는 좌표들을 미리 bool로 채워놓고
양 사이드에서 시작해서 해당 영역의 x,y 경계값을 만나면 더이상 더해지지 않도록
행, 열 순서대로 2중 for문 돌면서 영역 계산하고
1~4 영역까지만 계산하고 5는 total에서 빼기
그렇게 최대 최소값 계산하고 갱신
 */
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] board;

    static int total = 0;
    static boolean[][] border;
    static void setBorder(int si, int sj, int d1, int d2){
        border = new boolean[N][N];
        
        for (int i = 0; i <= d1; i++) {
            border[si + i][sj - i] = true;
            border[si + d2 + i][sj + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            border[si + i][sj + i] = true;
            border[si + d1 + i][sj - d1 + i] = true;
        }
    }
    static void calc(int si, int sj, int d1, int d2) {
        int[] areas = new int[5];

        for (int i = 0; i < si + d1; i++) {
            for (int j = 0; j <= sj; j++) {
                if (border[i][j])
                    break;
                areas[0] += board[i][j];
            }
        }

        for (int i = 0; i <= si + d2; i++) {
            for (int j = N - 1; j > sj; j--) {
                if (border[i][j])
                    break;
                areas[1] += board[i][j];
            }
        }

        for (int i = si + d1; i < N; i++) {
            for (int j = 0; j < sj - d1 + d2; j++) {
                if (border[i][j])
                    break;
                areas[2] += board[i][j];
            }
        }

        for (int i = si + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= sj - d1 + d2; j--) {
                if (border[i][j]) break;
                areas[3] += board[i][j];
            }
        }

        areas[4] = total;
        for (int i = 0; i < 4; i++)
            areas[4] -= areas[i];

        Arrays.sort(areas);
        ans = Math.min(ans, areas[4] - areas[0]);
    }

    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        total = 0;
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                total += board[i][j];
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                for(int d1 = 1; d1<N; d1++){
                    for(int d2 = 1; d2<N; d2++){
                        // 선거구 규칙 1번
                        if(i + d1 + d2 >= N)
                            continue;
                        if(j - d1< 0)
                            continue;
                        if(j + d2 >= N)
                            continue;
                        for(int a = 1; a<=4; a++){
                            setBorder(i,j,d1,d2);
                            calc(i,j,d1,d2);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
