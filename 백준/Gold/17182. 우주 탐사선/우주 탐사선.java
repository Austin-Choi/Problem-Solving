import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    static int[][] board;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;
    // 현재 위치, 방문 개수, 누적 거리
    static void bt(int cur, int depth, int dist){
        if(depth == N){
            ans = Math.min(ans, dist);
            return;
        }
        // 현재 위치에서 이동할 수 있는 모든 행성 후보를 전부 시도
        for(int next = 0; next < N; next++){
            if(!visited[next]){
                visited[next] = true;
                bt(next, depth+1, dist + board[cur][next]);
                visited[next] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k<N; k++){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        bt(K, 1, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
    }
}
