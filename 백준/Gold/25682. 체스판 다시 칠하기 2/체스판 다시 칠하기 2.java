import java.util.*;
import java.io.*;
/*
왼쪽 위를 검은색으로 잡았을때, 흰색으로 잡았을때
어긋나있는 타일 전체 수 세서 적은 버전으로 전체 보드 고르고
거기서 k*k 0,0에서 시작하는 크기의 어긋난 타일 수 세서 초기 잡아놓고
N-k, M-K만큼 이동해서 다시 칠해야하는 최솟값 갱신?
count [2][N][M] = 왼쪽 위를 검은색, 흰색으로 각자 고정시켰을때 0,0 ~ i,j까지의 보드에서 다시 칠해야 하는 타일 수

w b w b w b w b w b
b w b w b w b w b w
w b w b w b w b w b
-> (i+j)%2 == 0 에 현재 기준색이어야함
 */

public class Main {
    static int N,M,K;
    // 왼쪽위 기준으로 각각 나눔
    static int[][] black;
    static int[][] white;
    static char[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = t[j];
            }
        }
        black = new int[N+1][M+1];
        white = new int[N+1][M+1];

        // 흰색은 검은색의 반대임
        // 검은색먼저 하고 반대로
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                char cur = board[i-1][j-1];

                char correct;
                if((i+j)%2 == 0)
                    correct = 'B';
                else
                    correct = 'W';

                int paint = (cur == correct) ? 0 : 1;
                black[i][j] = black[i-1][j] + black[i][j-1] - black[i-1][j-1] + paint;
            }
        }

        // 모든 가능한 k*k 하면서
        // bSum, wSum
        int ans = N*M+1;

        for(int i = K; i<=N; i++){
            for(int j = K; j<=M; j++){
                int bSum = black[i][j] - black[i-K][j] - black[i][j-K] + black[i-K][j-K];
                int wSum = K*K - bSum;
                ans = Math.min(ans, Math.min(bSum, wSum));
            }
        }
        System.out.print(ans);
    }
}