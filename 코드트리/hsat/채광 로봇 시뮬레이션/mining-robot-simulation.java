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

    static int N, T;
    static int[][] board;
    static long[][] suffix;
    static int maxMask;
    static final long INF = Long.MIN_VALUE / 2;

    public static void main(String[] args) throws IOException{
        N = read();
        T = read();
        maxMask = 1<<T;

        board = new int[N+1][N+1];
        for(int i = 1; i<=N; i++){
            for(int j =1 ; j<=N; j++){
                board[i][j] = read();
            }
        }

        // suffix[i][j] = i,j 에서 N,N까지 최대 경로 합
        suffix = new long[N+1][N+1];
        for(int i = N; i>=1; i--){
            for(int j = N; j>=1; j--){
                if(i == N && j == N)
                    suffix[i][j] = board[i][j];
                else if(i ==N)
                    suffix[i][j] = board[i][j] + suffix[i][j+1];
                else if(j == N)
                    suffix[i][j] = board[i][j] + suffix[i+1][j];
                else
                    suffix[i][j] = board[i][j] + Math.max(suffix[i][j+1], suffix[i+1][j]);
            }
        }

        // 대각선 방향 rolling dp
        long[][] dpPrev = new long[N+1][maxMask];
        long[][] dpCur = new long[N+1][maxMask];

        for(int i = 1; i<=N; i++){
            Arrays.fill(dpPrev[i], INF);
        }
        dpPrev[1][0] = board[1][1];

        long ans = INF;
        int maxS = 2 * (N-1);

        // s= 맨해튼 거리, 아래 오른쪽으로 움직이므로
        for(int s = 0; s<maxS; s++){
            //dpCur 초기화
            for(int i =1 ;i<=N; i++){
                Arrays.fill(dpCur[i], INF);
            }

            int minI = Math.max(1, s+2-N);
            int maxI = Math.min(N, s+1);

            for(int i = minI; i<=maxI; i++){
                // 대각선 정보로 j 유추가능
                int j = s+2 - i;
                if(j < 1 || j > N)
                    continue;
                for(int m = 0; m<maxMask; m++){
                    if(dpPrev[i][m] == INF)
                        continue;
                    //오른쪽으로 가기
                    if(j < N){
                        int ni = i;
                        int nj = j+1;
                        // 새 움직임 기록 위해서 왼쪽으로 밀고
                        // 길이 제한
                        int nm = ((m<<1) | 0) & (maxMask -1);
                        long ns = dpPrev[i][m] + board[ni][nj];
                        dpCur[ni][nm] = Math.max(dpCur[ni][nm], ns);
                    }

                    //아래로 가기
                    if(i < N){
                        int ni = i+1;
                        int nj = j;
                        int nm = ((m<<1) | 1) & (maxMask -1);
                        long ns = dpPrev[i][m] + board[ni][nj];
                        dpCur[ni][nm] = Math.max(dpCur[ni][nm], ns);
                    }
                }
            }

            int nextS = s+1;
            // T초 이상부터 장치 사용 가능
            if(nextS >= T){
                int nMinI = Math.max(1, nextS + 2 - N);
                int nMaxI = Math.min(N, nextS + 1);
                for(int i = nMinI; i<= nMaxI; i++){
                    int j = nextS + 2 - i;
                    if(j < 1 || j > N)
                        continue;
                    for(int m = 0; m< maxMask; m++){
                        if(dpCur[i][m] == INF)
                            continue;
                        // mask로 최근 T개 도착 셀 합 복원
                        long bonus = 0;
                        int ci = i;
                        int cj = j;
                        int cm = m;
                        for(int k = 0; k<T; k++){
                            // 가장 최근 이동을 알아내기 위해 &1로 최하위 비트 뽑아냄
                            int recent = cm & 1;
                            if(recent == 0)
                                cj--;
                            else
                                ci--;
                            cm >>= 1;
                        }

                        long used = dpCur[i][m] + suffix[ci][cj];
                        if(ans < used)
                            ans = used;
                    }
                }
            }

            // 그냥 dpPrev = dpCur 하면 
            // 같은 배열 가리키게 돼서 이전 층 데이터가 날아감
            long[][] t = dpPrev;
            dpPrev = dpCur;
            dpCur = t;
        }

        // 장치 사용하지 않은채로 NN에 도달한 경우
        for(int m = 0; m<maxMask; m++){
            if(dpPrev[N][m] == INF)
                continue;
            if(dpPrev[N][m] > ans)
                ans = dpPrev[N][m];
        }

        System.out.print(ans);
    }
}