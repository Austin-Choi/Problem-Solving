/*
그래프는 장애물 양쪽 정점끼리만 해서 연결해두면 공유하니까 놔두고
장애물 그래프 완성한거에 백성들 시작 지점도 같은 방식으로 그래프 연결해주고
백성들 속도랑 스타트 고려해서 다익스트라 돌려주기

장애물 양 끝점에서 해당 장애물꺼 끝점 제외하고
모든 끝점을 연결하는데 다른 장애물을 교차하는 간선이 있으면 안됨.. ccw?
-> 가시그래프

다익스트라 할때 start만 임시적으로 추가해서 돌리기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3){
        long product = (long) (x2-x1)*(y3-y1) - (long) (y2-y1)*(x3-x1);
        return Long.compare(product, 0);
    }

    static boolean intersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
        long abc = ccw(x1, y1, x2, y2, x3, y3);
        long abd = ccw(x1, y1, x2, y2, x4, y4);
        long cda = ccw(x3,y3,x4,y4,x1,y1);
        long cdb = ccw(x3,y3,x4,y4,x2,y2);

        return abc * abd < 0 && cda * cdb < 0;
    }

    static double getCost(int x1, int y1, int x2, int y2){
        int j = y2-y1;
        int i = x2-x1;
        return Math.sqrt(j*j + i*i);
    }

    static int N,M;
    static class Person{
        int si;
        int sj;
        int speed;
        Person(int i, int j, int s){
            this.si = i;
            this.sj = j;
            this.speed = s;
        }
    }
    static class Line{
        int x1;
        int y1;
        int x2;
        int y2;
        Line(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    static class Edge{
        int to;
        double cost;
        Edge(int to, double c){
            this.to = to;
            this.cost = c;
        }
    }
    static Person[] p;
    static Line[] l;
    static int[][] vs;
    static ArrayList<Edge>[] board;
    static String parser(String in){
        return in.replaceAll("\\D+", " ");
    }

    static double[] dist;
    static int GROUND;
    static class State implements Comparable<State>{
        int node;
        double dist;
        State(int n, double d){
            this.node = n;
            this.dist = d;
        }

        public int compareTo(State o){
            return Double.compare(this.dist, o.dist);
        }
    }

    static boolean canGo(int x1, int y1, int x2, int y2){
        for(Line ll : l)
            if(intersect(x1,y1,x2,y2,ll.x1,ll.y1,ll.x2,ll.y2))
                return false;
        return true;
    }
    static double dijkstra(int sx, int sy, int speed){
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        PriorityQueue<State> pq = new PriorityQueue<>();

        // 백성 -> 보이는 모든 꼭짓점 , 바로 y = 0
        for(int i = 0; i<vs.length; i++){
            if(canGo(sx,sy,vs[i][0],vs[i][1])){
                double d = getCost(sx,sy,vs[i][0],vs[i][1]) / speed;
                dist[i] = d;
                pq.add(new State(i,d));
            }
        }

        if(canGoStraight(sx,sy)){
            double cost = sy / (double) speed;
            if(cost < dist[GROUND]){
                dist[GROUND] = cost;
                pq.add(new State(GROUND, cost));
            }
        }

        while(!pq.isEmpty()){
            State cur = pq.poll();
            if(cur.dist > dist[cur.node])
                continue;

            if(cur.node == GROUND)
                break;

            for(Edge e : board[cur.node]){
                double nd = cur.dist + e.cost/speed;
                if(nd < dist[e.to]){
                    dist[e.to] = nd;
                    pq.add(new State(e.to, nd));
                }
            }
        }

        return dist[GROUND];
    }

    static boolean canGoStraight(int x, int y){
        for(Line ll : l) {
            // 국경과 장애물 끝점이 겹치는 경우 허용
            if ((ll.x1 == x && ll.y1 == 0) || (ll.x2 == x && ll.y2 == 0))
                continue;

            if (intersect(x, y, x, 0, ll.x1, ll.y1, ll.x2, ll.y2))
                return false;
        }
        return true;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new Person[N];
        l = new Line[M];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(parser(br.readLine()));
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            p[i] = new Person(a,b,s);
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(parser(br.readLine()));
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            l[i] = new Line(a,b,c,d);
        }

        // vs i,j에서 i가 정점번호로
        vs = new int[2*M][2];
        for(int i = 0; i<M; i++){
            vs[2*i][0] = l[i].x1;
            vs[2*i][1] = l[i].y1;
            vs[2*i+1][0] = l[i].x2;
            vs[2*i+1][1] = l[i].y2;
        }

        board = new ArrayList[2*M+N+1];
        GROUND = board.length-1;
        for(int i = 0; i<2*M; i++){
            board[i] = new ArrayList<>();
        }

        for(int i = 0; i<2*M; i++){
            for(int j = 0; j<2*M; j++){
                boolean valid = true;

                int x1 = vs[i][0];
                int y1 = vs[i][1];
                int x2 = vs[j][0];
                int y2 = vs[j][1];

                for(Line ll : l){
                    if ((x1 == ll.x1 && y1 == ll.y1 && x2 == ll.x2 && y2 == ll.y2) ||
                        (x1 == ll.x2 && y1 == ll.y2 && x2 == ll.x1 && y2 == ll.y1))
                        continue;

                    if(intersect(x1,y1,x2,y2,ll.x1,ll.y1,ll.x2,ll.y2))
                        valid = false;
                }
                if(valid){
                    double cc = getCost(x1,y1,x2,y2);
                    board[i].add(new Edge(j,cc));
                    board[j].add(new Edge(i,cc));
                }
            }
        }

        for(int i = 0; i<vs.length; i++){
            if(canGoStraight(vs[i][0], vs[i][1])){
                double cost = vs[i][1];
                board[i].add(new Edge(GROUND, cost));
            }
        }

        dist = new double[board.length];
        double ans = 0.0;
        for(int i = 0; i<N; i++){
            double t = dijkstra(p[i].si, p[i].sj,p[i].speed);
            ans = Math.max(ans, t);
        }
        System.out.printf("%.1f", ans);
    }
}
