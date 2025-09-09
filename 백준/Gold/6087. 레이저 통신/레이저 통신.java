
/*
방향 회전 +1 +3 %4
0-1 bfs : 거울 쓰면 1, 안쓰고 직진하면 0
queue : cood i, cood j, cur direction
direction : 북 동 남 서 (0, 1, 2, 3)
. -> 갈수있음
* -> 못감
다른 C만나면 종료

dist를 i, j, 방향으로 3차원으로 두고
bfs는 1번만 실행 -> 네 방향을 다 넣어준다.
그리고 ni nj nd > ci cj cd + 1 조건에서만 addLast

C와 C가 주어지는데 리스트로 넣고 [0]이 시작, [1]이 끝
하나의 C를 잡고 각 방향으로 한번씩 다익스트라 돌려서
다른 C가 있는 곳 dist가 가장 작은거 출력하기
 */

import java.awt.*;
import java.util.*;
import java.io.*;
public class Main {
    static int W, H;
    static ArrayList<Point> cl = new ArrayList<>();
    static char[][] board;
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};
    static boolean isValid(int i, int j){
        if(i >= 0 && j >= 0 && i < H && j < W && board[i][j] != '*')
            return true;
        return false;
    }

    static int[][][] dist;
    static int ans = Integer.MAX_VALUE;
    static void bfs(){
        int si = cl.get(0).x;
        int sj = cl.get(0).y;
        int ei = cl.get(1).x;
        int ej = cl.get(1).y;
        Deque<int[]> dq = new ArrayDeque<>();
        for(int i = 0; i<4; i++){
            dq.add(new int[]{si,sj,i});
        }

        dist = new int[H][W][4];
        for(int i = 0; i<H; i++){
            for(int j = 0; j<W; j++){
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }
        Arrays.fill(dist[si][sj], 0);

        while(!dq.isEmpty()){
            int[] cur = dq.pollFirst();
            int ci = cur[0];
            int cj = cur[1];
            int cd = cur[2];

            int ni = ci + di[cd];
            int nj = cj + dj[cd];
            if(isValid(ni, nj)){
                if(dist[ni][nj][cd] > dist[ci][cj][cd]) {
                    dist[ni][nj][cd] = dist[ci][cj][cd];
                    dq.addFirst(new int[]{ni, nj, cd});
                }
            }

            int nd = (cd + 1) %4;
            ni = ci + di[nd];
            nj = cj + dj[nd];
            if(isValid(ni, nj)){
                if(dist[ni][nj][nd] > dist[ci][cj][cd]+1){
                    dist[ni][nj][nd] = dist[ci][cj][cd] + 1;
                    dq.addLast(new int[]{ni, nj, nd});
                }
            }

            nd = (cd + 3) %4;
            ni = ci + di[nd];
            nj = cj + dj[nd];
            if(isValid(ni, nj)){
                if(dist[ni][nj][nd] > dist[ci][cj][cd]+1){
                    dist[ni][nj][nd] = dist[ci][cj][cd] + 1;
                    dq.addLast(new int[]{ni, nj, nd});
                }
            }
        }

        for(int i = 0; i<4; i++){
            ans = Math.min(ans, dist[ei][ej][i]);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new char[H][W];
        for(int i = 0; i<H; i++){
            board[i] = br.readLine().toCharArray();
        }
        for(int i = 0; i<H; i++){
            for(int j = 0; j<W; j++){
                if(board[i][j] == 'C')
                    cl.add(new Point(i,j));
            }
        }

        bfs();
        System.out.println(ans);
    }
}