
/*
i,M+i,2M+i-> i번째줄이 가능한 모든 시작시간
0분 시작 1분끝 1 <-> 2
1분 시작 2분끝 3 <-> 4
2분 시작 3분끝 1 <-> 3
3분 시작 4분 끝 4 <-> 1
4분 시작 5분끝 2 <-> 3
주기라서 반복해서 열림
-> 각 노드를 지금 시간 기준(dist값) 언제 액세스할수있는지가 cost
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int start = 1;
    // 정점 u에 정점 v가 주기에서 몇번째로 열리는지 i
    static ArrayList<int[]>[] board;
    static long dijkstra(){
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        // 이전 노드, dist 값
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o->o[1]));
        pq.add(new long[]{start,0});

        while(!pq.isEmpty()){
            long[] cur = pq.poll();
            int ci = (int) cur[0];
            long cc = cur[1];

            if(dist[ci] != cc)
                continue;

            for(int[] next : board[ci]){
                // 현재시간 + 주기
                // 현재 주기가 어느건지 보고 가려는 노드 주기까지 얼마나 기다려야하는지 + 1
                long nc = 1+ ((long)next[1] - (dist[ci]%M) +M)%M;
                int ni = next[0];
                if(dist[ni] > dist[ci]+nc){
                    dist[ni] = dist[ci]+nc;
                    pq.add(new long[]{(long) ni, dist[ni]});
                }
            }
        }

        return dist[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            board[i] = new ArrayList<>();
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u].add(new int[]{v,i});
            board[v].add(new int[]{u,i});
        }
        System.out.print(dijkstra());
    }
}