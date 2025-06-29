import java.io.*;
import java.util.*;

class Planet{
    int x;
    int y;
    int z;
    int idx;

    public Planet(int x, int y, int z, int idx){
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}

class Info implements Comparable<Info>{
    int planet;
    int cost;

    public Info(int p, int cost){
        this.planet = p;
        this.cost = cost;
    }

    @Override
    public int compareTo(Info o){
        return this.cost - o.cost;
    }
}

public class Main {
    private static int N;
    private static Planet[] planets;
    private static ArrayList<Info>[] board;
    private static int calcCost(Planet p1, Planet p2){
        return Math.min(Math.min(Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y)), Math.abs(p1.z - p2.z));
    }
    private static int prim(){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        pq.add(new Info(1, 0));

        int ans = 0;
        while(!pq.isEmpty()){
            Info cur = pq.poll();

            if(!visited[cur.planet]){
                visited[cur.planet] = true;
                ans += cur.cost;

                for(Info i : board[cur.planet]){
                    pq.add(i);
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        planets = new Planet[N+1];
        Map<Integer, Planet> m = new HashMap<>();
        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(x,y,z,i);
            m.put(i, new Planet(x,y,z,i));
        }

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        Arrays.sort(planets, 1, N+1, Comparator.comparingInt(o->o.x));
        for(int i = 1; i<N; i++){
            Planet p1 = planets[i];
            Planet p2 = planets[i+1];
            int c = Math.abs(p1.x - p2.x);
            board[p1.idx].add(new Info(p2.idx, c));
            board[p2.idx].add(new Info(p1.idx, c));
        }
        Arrays.sort(planets, 1, N+1, Comparator.comparingInt(o->o.y));
        for(int i = 1; i<N; i++){
            Planet p1 = planets[i];
            Planet p2 = planets[i+1];
            int c = Math.abs(p1.y - p2.y);
            board[p1.idx].add(new Info(p2.idx, c));
            board[p2.idx].add(new Info(p1.idx, c));
        }
        Arrays.sort(planets, 1, N+1, Comparator.comparingInt(o->o.z));
        for(int i = 1; i<N; i++){
            Planet p1 = planets[i];
            Planet p2 = planets[i+1];
            int c = Math.abs(p1.z - p2.z);
            board[p1.idx].add(new Info(p2.idx, c));
            board[p2.idx].add(new Info(p1.idx, c));
        }

        bw.write(String.valueOf(prim()));
        bw.flush();
        bw.close();
        br.close();
    }
}
