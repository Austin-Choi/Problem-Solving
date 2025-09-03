/*
배열로 간선정보 받고
문제 : 출발점 -> 후보 목적지 까지의 경로가 G-H 간선을 지나냐?
distS[] s부터 모든 정점까지의 최단거리
distG[] g부터 모든 정점까지의 최단거리
distH[] h부터 모든 정점까지의 최단거리
S[x] == S[G] + board gh + H[X] or
S[X] == S[H] + bpard gh + G[X]
면 만족하는 것
 */
import java.util.*;
import java.io.*;
public class Main {
    static int TT;
    static int N, M, T;
    static int S, G, H;
    static int[][] board;
    static ArrayList<Integer> ans;

    static int[] dijkstra(int startNode){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{startNode, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int cd = cur[0];
            int cc = cur[1];

            if(dist[cd] < cc)
                continue;

            for(int i = 1; i<=N; i++){
                if(board[cd][i] == 0)
                    continue;

                if(dist[cd] + board[cd][i] < dist[i]){
                    dist[i] = dist[cd] + board[cd][i];
                    pq.add(new int[]{i, dist[i]});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TT = Integer.parseInt(br.readLine());
        for(int tt = 0; tt<TT; tt++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            board = new int[N+1][N+1];
            ans = new ArrayList<>();

            for(int m = 0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                board[a][b] = d;
                board[b][a] = d;
            }

            int[] distS = dijkstra(S);
            int[] distG = dijkstra(G);
            int[] distH = dijkstra(H);

            for(int t = 0; t<T; t++){
                int x = Integer.parseInt(br.readLine());
                if(distS[x] == distS[G] + board[G][H] + distH[x]
                    || distS[x] == distS[H] + board[H][G] + distG[x]){
                    ans.add(x);
                }
            }
            Collections.sort(ans);
            for(int i : ans){
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
