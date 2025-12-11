/*
불이 켜진 도로만 쓰는데 서로 왕래할수 있어야하고
사용하는 길의 갯수를 최소한으로 하면서 그 비용을 최소한으로 하면
N-1개의 간선을 사용해서 이으면 되고
이는 결국 MST를 구하는 것과 같음
-> 답 = 전체 간선비용 - MST 비용합
 */
import java.util.*;
import java.io.*;
public class Main {
    static int M, N;
    static ArrayList<int[]>[] board;
    static final int INF = Integer.MAX_VALUE;
    static int prim(){
        boolean[] visited = new boolean[M];
        int[] dist = new int[M];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        q.add(new int[]{0,0});

        int rst = 0;
        // mst 간선갯수 = 정점수 -1
        int connected = 0;

        while(!q.isEmpty() && connected < M){
            int[] cur = q.poll();
            int cd = cur[0];
            int cc = cur[1];

            if(visited[cd])
                continue;

            visited[cd] = true;
            rst += cc;
            connected++;

            for(int[] n : board[cd]){
                if(!visited[n[0]] && n[1] < dist[n[0]]){
                    dist[n[0]] = n[1];
                    q.add(new int[]{n[0], n[1]});
                }
            }
        }
        return rst;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if(M == 0 && N == 0)
                break;

            board = new ArrayList[M];
            for(int i = 0; i<M; i++){
                board[i] = new ArrayList<>();
            }

            int total = 0;
            while(N-->0){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c= Integer.parseInt(st.nextToken());
                board[s].add(new int[]{e,c});
                board[e].add(new int[]{s,c});
                total+=c;
            }
            sb.append(total - prim()).append("\n");
        }
        System.out.println(sb);
    }
}
