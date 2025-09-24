/*
BFS+copyBoard로 하니까 여러개의 혼합된 높이의 웅덩이가 있으면 못채움
BFS+pq를 사용해서 0,0부터 싹 도는데 테두리는 어차피 못 채우니까 vistited 체킹만

영역 상관 없이 항상 가장 낮은 높이 기준으로 전파되기 때문에 기존 버그 해결 가능
ci,cj를 벽으로 보고 낮은 칸으로 전파하면서 낮은 칸을 현재 높이로 채우기
 */
import java.io.*;
import java.util.*;
public class Main {
    static int N,M;
    static boolean[][] visited;
    static int[][] board;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for(int i = 0; i<N; i++){
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                board[i][j] = s[j] - '0';
            }
        }

        int ans = 0;
        visited = new boolean[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for(int i = 0; i<N; i++){
            pq.add(new int[]{board[i][0], i, 0});
            pq.add(new int[]{board[i][M-1], i, M-1});
            visited[i][0] = true;
            visited[i][M-1] = true;
        }
        for(int j = 0; j<M; j++){
            pq.add(new int[]{board[0][j], 0, j});
            pq.add(new int[]{board[N-1][j], N-1, j});
            visited[0][j] = true;
            visited[N-1][j] = true;
        }

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            for(int d = 0; d<4; d++){
                int ni = cur[1] + di[d];
                int nj = cur[2] + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(visited[ni][nj])
                    continue;
                visited[ni][nj] = true;

                if(board[ni][nj] < cur[0]){
                    ans += cur[0] - board[ni][nj];
                    board[ni][nj] = cur[0];
                }

                pq.add(new int[]{board[ni][nj], ni, nj});
            }
        }

        System.out.println(ans);
    }
}