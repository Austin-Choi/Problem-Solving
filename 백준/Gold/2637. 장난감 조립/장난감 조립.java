/*
indegree 0인거 하나씩 topologysort 돌리고
경로에서 각각의 cost를 곱해주고
각 경로의 cost의 곱을 합산하면
기본 부품당 얼마 쓰이는지 알게됨

dist를 2차원 배열로 하고 
부품 i를 만들기 위해 기본 부품 j가 몇개 필요한지를 저장

위상정렬에서
원본 진입차수 배열은 출력할때 필요해서 
복사본을 씀
초기 큐에 넣을때 i,i를 1로 초기화함

그리고 board에서 하나씩 꺼낼때 
cd = cur dest
basic(1~N) 으로
cd, basic 이 0이상이면 (초기화 되었거나 전파된 상태)
new dest,basic += cd, basic * new cost
로 전파시키고

--indeg가 0이면 큐에 다음 전파 후보로 넣는다.

출력은 i의 진입차수가 0이고 N, i가 0이상이면 
각각 i와 dist N,i 를 출력함
 */

import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static class Info{
        int dest;
        int cost;
        Info(int d, int c){
            this.dest = d;
            this.cost = c;
        }
    }
    static ArrayList<Info>[] board;
    static int[] indegree;
    // 부품 i를 만들기 위해 기본 부품 j가 몇개 필요한지
    static int[][] dist;

    static void topologySort(){
        Queue<Integer> q = new ArrayDeque<>();
        // 원본 진입차수 배열은 출력때 필요함
        int[] indeg = Arrays.copyOf(indegree, N+1);
        for(int i = 1; i <= N; i++){
            if(indeg[i] == 0){
                q.add(i);
                // 곱해줘야해서 항등원 1
                dist[i][i] = 1; 
            }
        }

        while(!q.isEmpty()){
            int cd = q.poll();

            for(Info i : board[cd]){
                int nd = i.dest;
                int nc = i.cost;
                
                // 기본 부품 정보를 nd에 전파
                for(int basic = 1; basic <= N; basic++){
                    if(dist[cd][basic] > 0)
                        dist[nd][basic] += dist[cd][basic] * nc;
                }
                
                if(--indeg[nd] == 0){
                    q.add(nd);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        indegree = new int[N+1];
        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        for(int m = 0; m<M; m++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            indegree[x]++;
            board[y].add(new Info(x,k));
        }

        dist = new int[N+1][N+1];
        topologySort();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=N; i++){
            if(indegree[i] == 0 && dist[N][i] > 0){
                sb.append(i).append(" ").append(dist[N][i]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
