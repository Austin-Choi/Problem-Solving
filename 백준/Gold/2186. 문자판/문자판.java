import java.util.*;
import java.io.*;
/*
* [n][m] 가로세로 K칸 머무르기 없음
* 결과는 long이라 직접세기는 불가능함
* 주어진 문자열 정방향으로 따라가지 말고
* 역방향으로 해서 sum up? -> dp
* dp[d][i][j] = i,j에서 시작해서 A[d]부터 문자열의 마지막의 문자까지 완성할 수 있는 경로의 갯수
* */

public class Main {
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static int N,M,K;
    static char[][] board;
    static char[] A;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            System.arraycopy(br.readLine().toCharArray(), 0, board[i], 0, M);
        }
        A = br.readLine().toCharArray();
        long[][][] dp = new long[A.length][N][M];
        // 맨 뒷자리는 1로 초기화
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] == A[A.length-1])
                    dp[A.length-1][i][j] = 1;
            }
        }
        // 현재 위치에서 목적지까지의 경로 수를 DP상태로 정의했기 때문에 역방향으로 계산.
        // DAG에서의 topological order 기반 DP 생각하면 됨.
        // -> 문자열 인덱스가 위상 순서를 보장
        for(int d = A.length-2; d>=0; d--){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(board[i][j] != A[d])
                        continue;
                    for(int dir = 0; dir<4; dir++){
                        for(int k = 1; k<=K; k++){
                            int ni = i + di[dir]*k;
                            int nj = j + dj[dir]*k;
                            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                                break;
                            dp[d][i][j] += dp[d+1][ni][nj];
                        }
                    }
                }
            }
        }

        long ans = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                ans += dp[0][i][j];
            }
        }
        System.out.print(ans);
    }
}
