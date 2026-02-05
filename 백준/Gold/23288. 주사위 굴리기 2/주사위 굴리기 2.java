/*
  1
3 0 2
  4
  5
 */
import java.util.*;
import java.io.*;
public class Main {
    //  1보다 크거나 같고, 6보다 작거나 같은 정수가 하나씩 있다!!!!!!
    static int[] d = {1,2,3,4,5,6};
    // 동남서북
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};
    static void roll(int dir){
        int t = d[0];
        if(dir == 0){
            d[0] = d[3];
            d[3] = d[5];
            d[5] = d[2];
            d[2] = t;
        }
        else if(dir == 1){
            d[0] = d[1];
            d[1] = d[5];
            d[5] = d[4];
            d[4] = t;
        }
        else if(dir == 2){
            d[0] = d[2];
            d[2] = d[5];
            d[5] = d[3];
            d[3] = t;
        }
        else{
            d[0] = d[4];
            d[4] = d[5];
            d[5] = d[1];
            d[1] = t;
        }
    }
    static int[][] board;
    // 현재 이동방향
    static int cur = 0;
    static int N,M,K;
    // board 점수 전처리
    /*
    동서남북 방향으로 현재 값 연속 칸 수 * 현재 값
     */
    static int bfs(int si, int sj, int val){
        int rst = 1;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj});
        visited[si][sj] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for(int dd =0; dd<4; dd++){
                int ni = ci + di[dd];
                int nj = cj + dj[dd];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(board[ni][nj] != val)
                    continue;
                if(!visited[ni][nj]){
                    visited[ni][nj] = true;
                    q.add(new int[]{ni,nj});
                    rst++;
                }
            }
        }
        return rst*val;
    }
    static void init(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                int cur = board[i][j];
                score[i][j] = bfs(i,j,cur);
            }
        }
    }
    static int[][] score;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        score = new int[N][M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        int si = 0;
        int sj = 0;
        int ans = 0;
        while(K-->0){
            int ni = si + di[cur];
            int nj = sj + dj[cur];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M){
                cur = (cur+2)%4;
                ni = si + di[cur];
                nj = sj + dj[cur];
            }
            roll(cur);
            int b = board[ni][nj];
            ans += score[ni][nj];
            int a = d[5];
            if(a>b)
                cur = (cur+1)%4;
            else if(a<b)
                cur = (cur+3)%4;
            si = ni;
            sj = nj;
        }
        System.out.print(ans);
    }
}
