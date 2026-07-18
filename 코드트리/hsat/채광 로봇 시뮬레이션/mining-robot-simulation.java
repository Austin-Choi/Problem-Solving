import java.util.*;
import java.io.*;

/*
dp[i][j][t] = 현재 i,j 칸까지 왔고 시간 역행 장치를 t번 사용했을때 로봇이 얻을 수 있는 최대 이득
T초 지나야 시간역행장치 쓸 수 있고 최대 1번임
-> dfs로 dp 채우는데 t초전 상태로 백트래킹 하라는건가
-> 최근 T초 동안의 이익을 계산해야 함

경로는 오른쪽으로 가기나 아래쪽으로 가기 2가지밖에 없음(0,1)
dp[i][j][used][mask] = 현재 위치가 i,j이고 시간 역행 사용 여부가 used이고
 최근 t초 동안의 경로가 mask일때 얻을 수 잇는 최대 이득
-> 이대로 하면 터지니까 roling dp.. 이렇게 못 푸나 

-----------------------------------
각 칸에 대해 한번의 시간 역행으로 얻을 수 있는 최대 이익을 미리 계산하고
최종 목적지에 도달할 때까지 이를 화룡ㅇ해 최대 수익을 도출 
*/
import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static final int INF = -1000000001; 

    static int N, T;
    static int[][] A;
    // mx[i][j] = i,j에서 T초 되돌아갔을때 최대 이익 
    static int[][] mx;
    // dp[i][j][t] : t=0(미사용), t=1(사용)
    static int[][][] dp;   

    // (i,j)에서 정확히 T번 이동했을 때 최대 이익 계산
    static void dfs(int i, int j, int x, int y, int time, int profit) {
        if (time == T) {
            mx[i][j] = Math.max(mx[i][j], profit);
            return;
        }
        if (x + 1 <= N) 
            dfs(i, j, x + 1, y, time + 1, profit + A[x + 1][y]);
        
        if (y + 1 <= N) 
            dfs(i, j, x, y + 1, time + 1, profit + A[x][y + 1]);
        
    }

    public static void main(String[] args) throws IOException {
        N = read();
        T = read();

        A = new int[N + 1][N + 1];
        mx = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1][2];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                A[i][j] = read();
            }
        }

        for (int i = 1; i <= N; i++) {
            Arrays.fill(mx[i], INF);
        }

        // 각 위치에서 T번 이동했을 때 최대 이익 미리 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dfs(i, j, i, j, 0, A[i][j]);
            }
        }

        // dp 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[1][1][0] = A[1][1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 현재 위치에서 장치 사용
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j][0] + mx[i][j]);

                // 아래로 이동
                if (i + 1 <= N) {
                    dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j][0] + A[i + 1][j]);
                    dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][1] + A[i + 1][j]);
                }

                // 오른쪽으로 이동
                if (j + 1 <= N) {
                    dp[i][j + 1][0] = Math.max(dp[i][j + 1][0], dp[i][j][0] + A[i][j + 1]);
                    dp[i][j + 1][1] = Math.max(dp[i][j + 1][1], dp[i][j][1] + A[i][j + 1]);
                }
            }
        }

        int answer = Math.max(dp[N][N][0], dp[N][N][1]);
        System.out.println(answer);
    }
}