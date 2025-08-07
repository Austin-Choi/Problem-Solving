import java.util.*;
import java.io.*;
public class Main {
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static int N,M,K;
    static char[][] board;
    static boolean[][][] visited;
    static final int maxLimit = 1000*1000 + 1;
    static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        // i, j, 부순 벽의 수, 최단거리
        q.add(new int[]{0,0,0,1});
        visited = new boolean[N][M][K+1];

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cw = cur[2];
            int cd = cur[3];

            if(ci == N-1 && cj == M-1)
                return cd;

            for(int d = 0; d<4; d++){
                int ni = ci+di[d];
                int nj = cj+dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(board[ni][nj] == '1'){
                    if(cw+1 <= K && !visited[ni][nj][cw+1]){
                        visited[ni][nj][cw+1] = true;
                        q.add(new int[]{ni,nj,cw+1, cd+1});
                    }
                }
                else{
                    if(!visited[ni][nj][cw]){
                        visited[ni][nj][cw] = true;
                        q.add(new int[]{ni,nj,cw, cd + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for(int i = 0; i<N;i++){
            board[i] = br.readLine().toCharArray();
        }

        int ans = bfs();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}