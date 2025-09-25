/*
1-based coord

처리할 손님 리스트 하나 마련하기
입력할때 받음

1) 현재 위치에서 손님들 위치까지 bfs로 dist 계산하고
dist가 가장 작고 그중 j 작고 그중 i 작은 손님부터
그렇게 손님 하나 픽했으면 그 dist가 현재 연료량보다 크면 return -1
아니면 return 현재 연료량 - dist 값
또한 손님들 위치에서 dist가 갱신되지 않는게 있다면 어차피 못 처리하는 손님이라 return -1

이후에
택시 현재 연료 = bfs1(처음 택시 위치)
택시 현재 위치 = 픽한 손님 위치로 갱신

2) 현재 위치에서 Info 리스트 확인해서 현재 손님의 목적지 위치 구하고
현재 손님의 목적지 위치까지 최단경로 계산 후
만약 그 dist가 현재 연료량보다 크면 return -1
아니면 return 현재 연료량 + dist;
택시 현재 연료 = bfs2(손님 태운 위치)
택시 현재 위치 = 픽한 손님 목적지 위치로 갱신
처리할 손님 리스트에서 remove
 */
import java.awt.Point;
import java.io.*;
import java.util.*;
public class Main {
    static int N, M, startFuel;
    static int[][] board;
    static Point taxiStart;
    static int[] di = {0,0,1,-1};
    static int[] dj = {1,-1,0,0};
    static ArrayList<Info> customers;
    // 손님정보
    static class Info {
        int si;
        int sj;
        int ei;
        int ej;
        Info(int si, int sj, int ei, int ej){
            this.si = si;
            this.sj = sj;
            this.ei = ei;
            this.ej = ej;
        }
    }
    // ni,nj가 손님 위치면
    // Bfs1Info list에 최단거리 dist 기록하고 add
    static class Bfs1Info implements Comparable<Bfs1Info>{
        int dist;
        int i;
        int j;
        Bfs1Info(int dist, int i, int j){
            this.dist = dist;
            this.i = i;
            this.j = j;
        }
        @Override
        public int compareTo(Bfs1Info o){
            if(this.dist == o.dist){
                if(this.i == o.i){
                    return this.j - o.j;
                }
                else
                    return this.i - o.i;
            }
            else
                return this.dist - o.dist;
        }
    }
    static int bfs1(Point taxiCoord){
        Queue<Point> q = new ArrayDeque<>();
        int[][] dist = new int[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[taxiCoord.x][taxiCoord.y] = 0;
        q.add(taxiCoord);

        // 모든 손님의 위치에 대해 최단경로 계산하기
        while(!q.isEmpty()){
            Point cur = q.poll();
            int ci = cur.x;
            int cj = cur.y;

            for(int d = 0; d<4; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(ni < 0 || nj < 0 || ni >= N || nj >= N || board[ni][nj] == 1)
                    continue;
                if(dist[ni][nj] > dist[ci][cj]+1){
                    dist[ni][nj] = dist[ci][cj]+1;
                    q.add(new Point(ni,nj));
                }
            }
        }

        // 손님 고르기
        PriorityQueue<Bfs1Info> pq = new PriorityQueue<>();
        for(Info o : customers){
            int d = dist[o.si][o.sj];
            if(d == Integer.MAX_VALUE)
                continue;
            pq.add(new Bfs1Info(d,o.si,o.sj));
        }
        Bfs1Info picked = null;
        if(!pq.isEmpty()){
            picked = pq.poll();
        }
        if(picked == null)
            return -1;

        // 손님 위치까지 이동하기
        if(picked.dist > startFuel)
            return -1;
        else {
            taxiStart = new Point(picked.i, picked.j);
            return startFuel - picked.dist;
        }

    }

    static int bfs2(Point taxiCoord){
        // 목표지점 i,j
        int tti = -1;
        int ttj = -1;
        for(Info o : customers){
            if(o.si == taxiCoord.x && o.sj == taxiCoord.y){
                tti = o.ei;
                ttj = o.ej;
                break;
            }
        }
        if(tti == -1 && ttj == -1)
            return -1;

        int shortDist = -1;
        // 버그?
        if(tti == taxiCoord.x && ttj == taxiCoord.y)
            shortDist = 0;
        else{
            Queue<Point> q = new ArrayDeque<>();
            int[][] dist = new int[N][N];
            for(int i = 0; i<N; i++){
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[taxiCoord.x][taxiCoord.y] = 0;
            q.add(taxiCoord);

            while(!q.isEmpty()){
                Point cur = q.poll();
                int ci = cur.x;
                int cj = cur.y;

                for(int d = 0; d<4; d++){
                    int ni = ci + di[d];
                    int nj = cj + dj[d];

                    if(ni < 0 || nj < 0 || ni >= N || nj >= N || board[ni][nj] == 1)
                        continue;
                    if(dist[ni][nj] > dist[ci][cj]+1){
                        dist[ni][nj] = dist[ci][cj]+1;
                        q.add(new Point(ni,nj));
                    }
                }
            }
            shortDist = dist[tti][ttj];
        }
        if(startFuel == 0)
            return -1;

        if(shortDist == Integer.MAX_VALUE)
            return -1;
        if(shortDist > startFuel)
            return -1;
        else{
            for(Iterator<Info> it = customers.iterator(); it.hasNext();){
                Info o = it.next();
                // 버그 : 목적지 같을 수 있음!
                if(o.si == taxiCoord.x && o.sj == taxiCoord.y){
                    it.remove();
                    break;
                }
            }
            taxiStart = new Point(tti, ttj);
            return startFuel + shortDist;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startFuel = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        customers = new ArrayList<>();

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int ti = Integer.parseInt(st.nextToken()) -1;
        int tj = Integer.parseInt(st.nextToken()) -1;
        taxiStart = new Point(ti,tj);

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken())-1;
            int sj = Integer.parseInt(st.nextToken())-1;
            int ei = Integer.parseInt(st.nextToken())-1;
            int ej = Integer.parseInt(st.nextToken())-1;
            customers.add(new Info(si,sj,ei,ej));
        }

        int m = 0;
        boolean failed = false;
        for(;m<M; m++){
            int tempFuel = bfs1(taxiStart);
            if(tempFuel == -1){
                failed = true;
                break;
            }
            startFuel = tempFuel;

            tempFuel = bfs2(taxiStart);
            if(tempFuel == -1){
                failed = true;
                break;
            }
            startFuel = tempFuel;
        }

        if(failed)
            startFuel = -1;
        else{
            if(m<M)
                startFuel = -1;
        }
        System.out.println(startFuel);
    }
}
