/*
입력 : M,N->보드->신호등(없을수도있음) -> 반복 -> 0 0 (끝)
지금 시각이 어느 방향 신호등이 켜졌는지 알아야함
-> next가 활성화되려면 신호등이 켜져있어야하고 해당 방향이 안 켜져있다면 활성화될때까지가 추가 cost
->
A = start, B = end, # = 길, . = 못감
 */
import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N,M;
    static Point start, end;
    static char[][] board;
    static int[] di = {0,0,-1,1};
    static int[] dj = {1,-1,0,0};
    //신호등
    static class TL{
        boolean isHorizontalStart;
        int horizontalTime;
        int nonHorizontalTime;
        TL(boolean start, int ht, int nht){
            this.isHorizontalStart = start;
            this.horizontalTime = ht;
            this.nonHorizontalTime = nht;
        }
    }
    static TL[] timetable;

    static boolean calcDir(int d){
        if(d <= 1)
            return true;
        return false;
    }
    // wantDir -> 수평 true, 수직, false;
    // aㅁㄴㅇㄻㄴㅇㄹ
    static int calcDirectionCost(int curtime, int tlIdx, boolean wantDir){
        TL tl = timetable[tlIdx];
        int a = tl.horizontalTime;
        int b = tl.nonHorizontalTime;
        int L = a+b;
        int t = ((curtime%L) +L) % L;

        boolean green;
        if(tl.isHorizontalStart)
            green = (t<a);
        else
            green = (t>=b);

        if(wantDir == green)
            return 0;

        // 수평가고싶음
        if(wantDir){
            if(tl.isHorizontalStart)
                return (t<a) ? 0 : (L-t);
            else
                return (t>=b) ? 0 : (b-t);
        }
        //수직가고싶음
        else{
            if(tl.isHorizontalStart)
                return (t>=a) ? 0 : (a-t);
            else
                return (t<b) ? 0 : (L-t);
        }
    }
    static int[][] dist;
    // dist i j = i,j 까지 도달 최소 시간
    static void bfs(){
        dist = new int[N][M];
        for(int i =0; i<N; i++){
            for(int j = 0; j<M; j++){
                dist[i][j] = INF;
            }
        }
        dist[start.x][start.y] = 0;
        // i,j,cost
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        q.add(new int[]{start.x, start.y, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];
            int cc = cur[2];

            if(dist[ci][cj] != cc)
                continue;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                int nc = 1;
                if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                    continue;

                if(board[ni][nj]=='.')
                    continue;

                int cost = 0;
                if(Character.isDigit(board[ni][nj])){
                    int idx = board[ni][nj] - '0';
                    cost += calcDirectionCost(dist[ci][cj], idx, calcDir(d));
                }
                nc += cost;
                if(dist[ni][nj] > dist[ci][cj] + nc){
                    dist[ni][nj] = dist[ci][cj] + nc;
                    q.add(new int[]{ni,nj,dist[ci][cj] + nc});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                break;

            board = new char[N][M];
            int K = -1;
            for(int i = 0; i<N; i++){
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j<M; j++){
                    board[i][j] = temp[j];
                    if(temp[j] == 'A'){
                        start = new Point(i,j);
                    }
                    else if(temp[j]=='B'){
                        end = new Point(i,j);
                    }
                    else if(Character.isDigit(temp[j])){
                        K = Math.max(K, temp[j]-'0');
                    }
                }
            }

            if(K!=-1){
                timetable = new TL[10];
                for(int k = 0; k<=K; k++){
                    st = new StringTokenizer(br.readLine());
                    int idx = Integer.parseInt(st.nextToken());
                    boolean s = st.nextToken().equals("-");
                    int ht = Integer.parseInt(st.nextToken());
                    int nht = Integer.parseInt(st.nextToken());
                    timetable[idx] = new TL(s,ht,nht);
                }
            }

            bfs();
            int ans = dist[end.x][end.y];
            if(ans == INF)
                sb.append("impossible\n");
            else
                sb.append(ans).append("\n");
            br.readLine();
        }
        System.out.println(sb);
    }
}
