import java.io.*;
import java.util.*;
/*
* 거울은 양면반사됨
* 최소의 거울 놓을수 있는 자리를 통과하는 최단경로
* 시작과 끝 정점은 문 위치고
* 각 거울 놓을수 있는 위치는 정점들임
*
* 다만 정점과 정점이 연결되는 조건은 위치상 동서남북 직선거리에 장애물에 가로막히지 않고
* 연결되어 있어야 함.
* */

public class Main {
    static int N;
    static char[][] board;
    // [count][2]
    // 2차원 좌표 -> 좌표 번호
    static int[][] vertices;
    static ArrayList<Integer>[] g;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        int count = 0;
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                board[i][j] = t[j];
                if(board[i][j] != '*' && board[i][j] != '.')
                    count++;
            }
        }

        g = new ArrayList[count];
        for(int i = 0; i<count; i++)
            g[i] = new ArrayList<>();

        vertices = new int[N][N];
        count = 0;
        int start = -1;
        int end = 0;
        // 그래프 만들기 전에 정점부터
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] == '#' || board[i][j] == '!'){
                    vertices[i][j] = count;
                    if(board[i][j] == '#'){
                        if(start == -1)
                            start = count;
                        else
                            end = count;
                    }
                    count++;
                }
            }
        }

        // 북동남서 장애물에 가로막히기 전까지 정점 연결시켜서 그래프만들기
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(board[i][j] == '#' || board[i][j] == '!'){
                    int si = i;
                    int sj = j;
                    int snum = vertices[si][sj];

                    for(int d = 0; d<4; d++){
                        int ci = si;
                        int cj = sj;
                        while(true){
                            ci += di[d];
                            cj += dj[d];
                            if(ci < 0 || cj < 0 || ci >= N || cj >= N)
                                break;
                            if(board[ci][cj] == '*')
                                break;
                            if(board[ci][cj] == '#' || board[ci][cj] == '!')
                                g[snum].add(vertices[ci][cj]);
                        }
                    }
                }
            }
        }

        // cur, dist
        Deque<int[]> q = new ArrayDeque<>();
        int[] dist = new int[g.length];
        Arrays.fill(dist, N*N+1);
        dist[start] = 0;
        q.add(new int[]{start, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(dist[ci] != cd)
                continue;

            for(int ni : g[ci]){
                int nd = cd + 1;
                if(dist[ni] > nd){
                    dist[ni] = nd;
                    q.add(new int[]{ni, nd});
                }
            }
        }
        // 마지막에 end도 정점으로 방문하므로 -1해야함
        System.out.print(dist[end]-1);
    }
}
