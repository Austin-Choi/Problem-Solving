/*
dist 1에서부터 시작하고
밤에만 부술수 있고
dist N*M*K
dist % 2 == 1 -> 낮, 아니면 밤?
판단해서
dist는 낮이면 그대로 다음거 +1하고 밤이면 +2하기
isday는 낮이엇으면 isday+1%2 아니면 isDay 그대로(한번 기다리는거니까)
----------------------------------------------------------------
밤 낮도 dist에 추가해야함 [2]

 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static char[][] board;
    static int[][][][] dist;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        // i, j, wall, day : 0 | night : 1
        q.add(new int[]{0,0,0,0});
        dist = new int[N][M][K+1][2];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                for(int k = 0; k<=K; k++){
                    Arrays.fill(dist[i][j][k], Integer.MAX_VALUE);
                }
            }
        }
        dist[0][0][0][0] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cw = cur[2];
            int isDay = cur[3];
            boolean flag = cw < K;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;
                if(board[ni][nj] == '0'){
                    if(dist[ni][nj][cw][(isDay+1)%2] > dist[ci][cj][cw][isDay] + 1) {
                        dist[ni][nj][cw][(isDay+1)%2] = dist[ci][cj][cw][isDay] + 1;
                        q.add(new int[]{ni, nj, cw, (isDay + 1) % 2});
                    }
                }
                else{
                    if(flag){
                        if(isDay == 0){
                            if(dist[ni][nj][cw+1][(isDay+1)%2] > dist[ci][cj][cw][isDay] + 1){
                                dist[ni][nj][cw+1][(isDay+1)%2] = dist[ci][cj][cw][isDay] + 1;
                                q.add(new int[]{ni,nj,cw+1,(isDay+1)%2});
                            }
                        }
                        else{
                            if(dist[ni][nj][cw+1][isDay] > dist[ci][cj][cw][isDay]+2){
                                dist[ni][nj][cw+1][isDay] = dist[ci][cj][cw][isDay] + 2;
                                q.add(new int[]{ni,nj,cw+1,isDay});
                            }
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int[] i2 : dist[N-1][M-1]){
            for(int i = 0; i<2; i++)
                ans = Math.min(ans, i2[i]);
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i<N; i++){
            board[i] = br.readLine().toCharArray();
        }
        int ans = bfs();
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);
    }
}