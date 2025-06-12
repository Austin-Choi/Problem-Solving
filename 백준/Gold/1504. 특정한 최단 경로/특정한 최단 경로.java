import java.util.*;
import java.io.*;

class Info{
    public int node;
    public int cost;

    public Info(int node, int cost){
        this.node = node;
        this.cost = cost;
    }
}

public class Main {
    private static int N;
    private static int E;
    private static ArrayList<Info>[] board;
    private static int[] dist;
    private static final int MAX_LIMIT = 200000000;
    private static int dijkstra(int start, int target){
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        dist = new int[N+1];
        Arrays.fill(dist, MAX_LIMIT);
        dist[start] = 0;
        pq.add(new Info(start, 0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int curNode = cur.node;

            if(dist[curNode] < cur.cost)
                continue;

            for(int i = 0; i<board[curNode].size(); i++){
                Info next = board[curNode].get(i);
                int nextNode = next.node;
                if(dist[nextNode] > dist[curNode]+next.cost){
                    dist[nextNode] = dist[curNode] + next.cost;
                    pq.add(new Info(nextNode, dist[nextNode]));
                }
            }
        }
        return dist[target];
    }

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int e = 0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            board[start].add(new Info(end, cost));
            board[end].add(new Info(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int startToU = dijkstra(1,u);
        int startToV = dijkstra(1,v);

        int vToN = dijkstra(v,N);
        int vTou = dijkstra(v,u);

        int uToN = dijkstra(u,N);
        int uToV = dijkstra(u,v);

        int se = startToU + uToV + vToN;
        int es = startToV + vTou + uToN;

        if(Math.min(se, es) >= MAX_LIMIT)
            sb.append(-1);
        else
            sb.append(Math.min(se, es));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
