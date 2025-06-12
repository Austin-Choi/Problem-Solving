
import java.io.*;
import java.util.*;

class Info{
    public int end;
    public int cost;

    public Info(int end, int cost){
        this.end = end;
        this.cost = cost;
    }
}

public class Main {
    private static int V;
    private static int E;
    private static int K;
    private static ArrayList<Info>[] board;
    private static int[] dijkstra(int start){
        PriorityQueue<Info> pq = new PriorityQueue<Info>((Comparator.comparingInt(o -> o.cost)));
        int[] dist = new int[V+1];
        Arrays.fill(dist, MAX_LIMIT);
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int curNode = cur.end;
            int curCost = cur.cost;


            for(Info next : board[curNode]){
                if(dist[next.end] > dist[curNode] + next.cost){
                    dist[next.end] = dist[curNode]+ next.cost;
                    pq.add(new Info(next.end, dist[next.end]));
                }
            }
        }
        return dist;
    }
    private static final int MAX_LIMIT = 3000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(br.readLine());
        board = new ArrayList[V+1];
        for(int v = 1; v<=V; v++){
            board[v] = new ArrayList<Info>();
        }

        for(int e = 0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            board[start].add(new Info(end, cost));
        }

        int[] ans = dijkstra(s);
        for(int i = 1; i<=V; i++){
            if(ans[i] < MAX_LIMIT)
                sb.append(ans[i]);
            else
                sb.append("INF");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
