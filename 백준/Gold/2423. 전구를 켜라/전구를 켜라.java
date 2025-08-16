/*
9:13

꼭지점 그래프라고 생각해야 함
i,j 칸에서
\ 이라면 i,j -> i+1, j+1 의 비용이 0 => /로 바꾸고 싶으면 비용이 1
/ 이라면 i+1, j -> i, j+1 의 비용이 0 => \로 바꾸고 싶으면 비용이 1

Info에는 목적지, cost 저장하고
꼭지점끼리는 양방향 간선이며 ArrayList<Info>[][] 입력

0-1 bfs (0은 addFirst, 1은 addLast)
 */
import java.io.*;
import java.util.*;
public class Main {
    static class Info{
        int[] pos = new int[2];
        int cost;

        public Info(int i, int j, int c){
            this.pos[0] = i;
            this.pos[1] = j;
            this.cost = c;
        }
    }
    static ArrayList<Info>[][] board;
    static int N, M;
    static final int MAX = 500 * 500 + 1;
    static int[][] dist;
    static void bfs(){
        Deque<Info> dq = new ArrayDeque<>();
        dq.addLast(new Info(0,0,0));
        dist[0][0] = 0;

        while(!dq.isEmpty()){
            Info cur = dq.poll();
            int ci = cur.pos[0];
            int cj = cur.pos[1];

            for(Info info : board[ci][cj]){
                int ni = info.pos[0];
                int nj = info.pos[1];
                int nc = info.cost;

                if(dist[ni][nj] > dist[ci][cj]+nc){
                    dist[ni][nj] = dist[ci][cj]+nc;
                    if(nc == 0)
                        dq.addFirst(info);
                    else
                        dq.addLast(info);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList[N+1][M+1];
        for(int i = 0; i<=N; i++){
            for(int j =0; j<=M; j++){
                board[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i<N; i++){
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j<M; j++){
                if(tmp[j] == '/'){
                    board[i+1][j].add(new Info(i, j+1, 0));
                    board[i][j+1].add(new Info(i+1, j, 0));
                    board[i][j].add(new Info(i+1, j+1, 1));
                    board[i+1][j+1].add(new Info(i,j,1));
                }
                else{
                    board[i+1][j].add(new Info(i, j+1, 1));
                    board[i][j+1].add(new Info(i+1, j, 1));
                    board[i][j].add(new Info(i+1, j+1, 0));
                    board[i+1][j+1].add(new Info(i,j,0));
                }
            }
        }

        dist = new int[N+1][M+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=M; j++){
                dist[i][j] = MAX;
            }
        }
        bfs();

        if(dist[N][M] == MAX)
            bw.write("NO SOLUTION");
        else
            bw.write(String.valueOf(dist[N][M]));
        bw.flush();
    }
}
