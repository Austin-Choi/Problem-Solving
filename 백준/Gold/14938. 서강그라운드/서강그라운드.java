import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
    int dest;
    int cost;

    public Info(int dest, int cost){
        this.dest = dest;
        this.cost = cost;
    }

    @Override
    public int compareTo(Info o){
        return this.cost - o.cost;
    }
}

public class Main {
    private static int N;
    private static int M;
    private static int R;
    private static ArrayList<Info>[] board;
    private static int[] items;
    private static int[] dist;
    private static final int INF = 1501;
    private static void dijkstra(int start){
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start,0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int curNode = cur.dest;

            for(Info next : board[curNode]){
                if(dist[next.dest] > dist[curNode] + next.cost){
                    dist[next.dest] = dist[curNode] + next.cost;
                    pq.add(new Info(next.dest, dist[next.dest]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++){
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int r = 0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[s].add(new Info(d,c));
            board[d].add(new Info(s,c));
        }

        int ans = 0;
        for(int i = 1; i<=N; i++){
            dijkstra(i);
            int sum = items[i];
            for(int j = 1; j<=N; j++){
                if(j==i)
                    continue;
                if(dist[j] <= M){
                    sum += items[j];
                }
            }
            ans = Math.max(sum, ans);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
