/*
한번에 두번째 최단경로를 구할수는 없음
1) 최단 경로를 일단 구하고 (다익스트라, 1차원 dist)
2) 백트래킹으로 도착점에서부터 최단 경로에 해당하는 간선을 제거하고
3) 다시 최단 경로를 구하면 2번째 최단 경로가 구해짐

다익스트라 팁
1) cc가 dist[cd] 보다 길면 continue
2) 비교할땐 dist i.dest > dist cd + i.cost

백트래킹 팁
도착지에서부터 시작해야 경로가 1가지 뿐
0) 입력에서 역방향 그래프 받고 그걸로 돌림
1) dist[u] + cost(u.v) == dist[v] 이면
cost(u,v) 가 지워져야 할 간선임
Info 객체에 removed 기록하기
2) 지울때 정방향 그래프에서도 removed 갱신하기

 */

import java.util.*;
import java.io.*;
public class Main {
    static final long LIMIT = 500L * 10000 * 1000 + 1;
    static long[] dist;
    static class Info implements Comparable<Info>{
        int dest;
        long cost;
        boolean removed;
        Info(int d, long c){
            this.dest = d;
            this.cost = c;
            this.removed = false;
        }

        @Override
        public int compareTo(Info o){
            return Long.compare(this.cost, o.cost);
        }
    }
    static int N, M, S, D;
    static ArrayList<Info>[] board;
    static ArrayList<Info>[] reverseBoard;
    static boolean[][] removed;

    static void dijkstra(){
        dist = new long[N];
        Arrays.fill(dist, LIMIT);
        dist[S] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(S, 0));

        while(!pq.isEmpty()){
            Info cur = pq.poll();
            int cd = cur.dest;
            long cc = cur.cost;

            if(dist[cd] < cc)
                continue;

            for(Info i : board[cd]){
                if(i.removed)
                    continue;
                if(dist[i.dest] > dist[cd]+i.cost){
                    dist[i.dest] = dist[cd] + i.cost;
                    pq.add(new Info(i.dest, dist[i.dest]));
                }
            }
        }
    }

    // 역방향 그래프에서 제거된 간선 check
    static void bt(int dest){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(dest);

        while(!q.isEmpty()){
            int v = q.poll();

            for(Info i : reverseBoard[v]){
                int u = i.dest;
                long cost = i.cost;

                if(dist[u] + cost == dist[v] && !i.removed){
                    i.removed = true;

                    // 정방향에서도 제거해야함
                    for(Info f : board[u]){
                        if(f.dest == v && f.cost == cost && !f.removed){
                            f.removed = true;
                            break;
                        }
                    }
                    q.add(u);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0){
                bw.flush();
                break;
            }
            board = new ArrayList[N];
            reverseBoard = new ArrayList[N];
            removed = new boolean[N][N];
            for(int i = 0; i<N; i++){
                reverseBoard[i] = new ArrayList<>();
                board[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for(int m = 0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long p = Long.parseLong(st.nextToken());
                board[u].add(new Info(v,p));
                reverseBoard[v].add(new Info(u,p));
            }

            dijkstra();
            bt(D);
            dijkstra();
            long ans = dist[D];
            if(ans == LIMIT)
                ans = -1;
            bw.write(String.valueOf(ans));
            bw.newLine();
        }
    }
}
