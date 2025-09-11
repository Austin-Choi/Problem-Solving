
/*
다익스트라를 변형해서
K개의 값만 저장하는 우선순위 큐로 dist를 저장함
-> 최단경로 길이만 필요하므로

PriorityQueue<Integer>[] dist (최대힙)
에다가 경로 발견될때마다 dist[v]에 넣기
dist ni . size < K 때는 그냥 추가하고 queue 갱신
dist ni .size >= K 때는 제일 긴 경로 빼고 발견된 값 추가하고 q 갱신

peek 한게 정답
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N,M,K;
    static ArrayList<int[]>[] board;
    static PriorityQueue<Integer>[] dist;
    static void dijkstra(){
        dist = new PriorityQueue[N+1];
        for(int i = 1; i<=N; i++){
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        dist[1].add(0);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        pq.add(new int[]{1,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int v = cur[0];
            int cc = cur[1];

            for(int[] n : board[v]){
                int ni = n[0];
                int nc = n[1];

                if (dist[ni].size() < K) {
                    dist[ni].add(cc + nc);
                    pq.add(new int[]{ni, cc + nc});
                } else if(dist[ni].peek() > cc + nc){
                    dist[ni].poll();
                    dist[ni].add(cc + nc);
                    pq.add(new int[]{ni, cc + nc});
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[s].add(new int[]{e,c});
        }

        dijkstra();

        for(int i = 1; i<=N; i++){
            if(dist[i].size() < K)
                System.out.println(-1);
            else
                System.out.println(dist[i].peek());
        }
    }
}
