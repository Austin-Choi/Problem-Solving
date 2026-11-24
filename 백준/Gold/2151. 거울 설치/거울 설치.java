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
    // 그래프 생성 전처리 하고 일반 bfs로 이제까지 지나친 거울 최단경로 구하기
    // O(N^3), 다익스트라로 하면 logN까지 
    static int oldBfs(int start, int end){
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
        return dist[end]-1;
    }
    /*
    * 0-1bfs
    * 직진하면 비용 0, 거울 설치하면 비용 1
    * O(N^2)
    * */
    static int bfs(int si, int sj, int ei, int ej){
        int[][][] dist = new int[N][N][4];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        // 시작점에서 4방향 가능
        for(int d = 0; d<4; d++){
            dist[si][sj][d] = 0;
            q.addLast(new int[]{si,sj,d,0});
        }

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];
            int cost = cur[3];
            if(dist[ci][cj][cd] != cost)
                continue;
            if(ci == ei && cj == ej)
                return cost;

            for(int nd = 0; nd<4; nd++){
                if(nd == (cd + 2)%4)
                    continue;
                // 거울 설치 위치가 아니면 직진만 가능
                if(nd != cd && board[ci][cj] != '!')
                    continue;

                int ni = ci + di[nd];
                int nj = cj + dj[nd];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(board[ni][nj] == '*')
                    continue;

                // 직진, 거울설치 비용따라 분기
                int w = (nd == cd) ? 0 : 1;
                int nc = cost + w;

                if(dist[ni][nj][nd] <= nc)
                    continue;
                dist[ni][nj][nd] = nc;
                if(w == 0)
                    q.addFirst(new int[]{ni,nj,nd,nc});
                else
                    q.addLast(new int[]{ni,nj,nd,nc});

            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        int si = -1, sj = -1, ei = -1, ej = -1;
        for(int i = 0; i<N; i++){
            board[i] = br.readLine().toCharArray();
            for(int j = 0; j<N; j++){
                if(board[i][j] == '#'){
                    if(si == -1){
                        si = i;
                        sj = j;
                    }
                    else{
                        ei = i;
                        ej = j;
                    }
                }
            }
        }
        System.out.println(bfs(si,sj,ei,ej));
//        int count = 0;
//        for(int i = 0; i<N; i++){
//            char[] t = br.readLine().toCharArray();
//            for(int j = 0; j<N; j++){
//                board[i][j] = t[j];
//                if(board[i][j] != '*' && board[i][j] != '.')
//                    count++;
//            }
//        }

//        g = new ArrayList[count];
//        for(int i = 0; i<count; i++)
//            g[i] = new ArrayList<>();
//
//        vertices = new int[N][N];
//        count = 0;
//        int start = -1;
//        int end = 0;
//        // 그래프 만들기 전에 정점부터
//        for(int i = 0; i<N; i++){
//            for(int j = 0; j<N; j++){
//                if(board[i][j] == '#' || board[i][j] == '!'){
//                    vertices[i][j] = count;
//                    if(board[i][j] == '#'){
//                        if(start == -1)
//                            start = count;
//                        else
//                            end = count;
//                    }
//                    count++;
//                }
//            }
//        }
//
//        // 북동남서 장애물에 가로막히기 전까지 정점 연결시켜서 그래프만들기
//        for(int i = 0; i<N; i++){
//            for(int j = 0; j<N; j++){
//                if(board[i][j] == '#' || board[i][j] == '!'){
//                    int si = i;
//                    int sj = j;
//                    int snum = vertices[si][sj];
//
//                    for(int d = 0; d<4; d++){
//                        int ci = si;
//                        int cj = sj;
//                        while(true){
//                            ci += di[d];
//                            cj += dj[d];
//                            if(ci < 0 || cj < 0 || ci >= N || cj >= N)
//                                break;
//                            if(board[ci][cj] == '*')
//                                break;
//                            if(board[ci][cj] == '#' || board[ci][cj] == '!')
//                                g[snum].add(vertices[ci][cj]);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.print(oldBfs(start, end));
    }
}
