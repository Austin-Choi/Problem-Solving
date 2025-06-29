import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point{
    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}

class Info implements Comparable<Info>{

    int starNum;
    double cost;

    public Info(int starNum, double cost){
        this.starNum = starNum;
        this.cost = cost;
    }

    @Override
    public int compareTo(Info o){
        if(this.cost > o.cost)
            return 1;
        else if(this.cost < o.cost)
            return -1;
        else
            return 0;
    }
}

public class Main {
    private static int N;
    private static Point[] stars;
    private static ArrayList<Info>[] board;
    private static double calcDist(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
    }
    private static boolean[] visited;
    private static double prim(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        visited = new boolean[N+1];
        pq.add(new Info(1, 0));

        double dist = 0;
        while(!pq.isEmpty()){
            Info cur = pq.poll();

            if(!visited[cur.starNum]){
                visited[cur.starNum] = true;
                dist += cur.cost;

                for(Info i : board[cur.starNum]){
                    pq.add(i);
                }
            }
        }

        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        stars = new Point[N+1];
        for(int n = 1; n<=N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[n] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i == j)
                    continue;
                board[i].add(new Info(j, calcDist(stars[i].x, stars[i].y, stars[j].x, stars[j].y)));
            }
        }

        bw.write(String.valueOf(prim()));
        bw.flush();
        bw.close();
        br.close();
    }
}